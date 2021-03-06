package github.johnnysc.rcanoed;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public abstract class GameThread extends Thread {
    public static final int STATE_LOSE = 1;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_READY = 3;
    public static final int STATE_RUNNING = 4;
    public static final int STATE_WIN = 5;
    public static final int STATE_MIDLOSE = 6;
    public static final int STATE_CONTINUE = 7;

    protected int mMode = 1;
    private boolean mRun = false;
    private SurfaceHolder mSurfaceHolder;
    private Handler mHandler;
    private Context mContext;
    public GameView mGameView;
    protected int mCanvasWidth = 1;
    protected int mCanvasHeight = 1;
    protected long mLastTime = 0;
    protected Bitmap mBackgroundImage;
    protected long score = 0;
    private long now;
    private float elapsed;
    static final Integer monitor = 1;

    public GameThread(GameView gameView) {
        mGameView = gameView;

        mSurfaceHolder = gameView.getHolder();
        mHandler = gameView.getmHandler();
        mContext = gameView.getContext();

        if(rcanoedGame.optionInput>=0){
            mBackgroundImage = BitmapFactory.decodeResource
                    (gameView.getContext().getResources(),
                            R.drawable.background);
        }else
            mBackgroundImage = BitmapFactory.decodeResource
                    (gameView.getContext().getResources(),
                            R.drawable.background_with_arrows);
    }

    public void cleanup() {
        this.mContext = null;
        this.mGameView = null;
        this.mHandler = null;
        this.mSurfaceHolder = null;
    }

    abstract public void setupBeginning();
    abstract public void setupContinue();

    public void doStart() {
        synchronized(monitor) {
            setupBeginning();
            mLastTime = System.currentTimeMillis() + 100;
            setState(STATE_RUNNING);
            setScore(0);
        }
    }

    public void doContinue(){
        synchronized (monitor){
            setupContinue();
            mLastTime = System.currentTimeMillis() + 100;
            setState(STATE_RUNNING);

        }
    }

    @Override
    public void run() {
        Canvas canvasRun;
        while (mRun) {
            canvasRun = null;
            try {
                canvasRun = mSurfaceHolder.lockCanvas(null);
                synchronized (monitor) {
                    if (mMode == STATE_RUNNING || mMode==STATE_CONTINUE)
                        updatePhysics();

                    doDraw(canvasRun);
                }
            }
            finally {
                if (canvasRun != null) {
                    if(mSurfaceHolder != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvasRun);
                }
            }
        }
    }

    public void setSurfaceSize(int width, int height) {
        synchronized (monitor) {
            mCanvasWidth = width;
            mCanvasHeight = height;
            mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, width, height, true);
        }
    }

    protected void doDraw(Canvas canvas) {
        if(canvas == null) return;

        if(mBackgroundImage != null) canvas.drawBitmap(mBackgroundImage, 0, 0, null);
    }

    private void updatePhysics() {
        now = System.currentTimeMillis();
        elapsed = (now - mLastTime) / 1000.0f;
        updateGame(elapsed);
        mLastTime = now;
    }

    abstract protected void updateGame(float secondsElapsed);

    public boolean onTouch(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_DOWN || e.getAction()==MotionEvent.ACTION_MOVE) {


            if (mMode == STATE_READY || mMode == STATE_LOSE /*|| mMode == STATE_WIN*/) {
                doStart();
                return true;
            }

            if (mMode == STATE_PAUSE) {
                unpause();
                return true;
            }

            if (mMode == STATE_MIDLOSE || mMode == STATE_CONTINUE) {
                doContinue();
                return true;
            }

            synchronized (monitor) {
                this.actionOnTouch(e.getRawX(), e.getRawY());
            }
        }
            return false;
    }

    protected void actionOnTouch(float x, float y) {
        //Override to do something
    }

    public void pause() {
        synchronized (monitor) {
            if (mMode == STATE_RUNNING) setState(STATE_PAUSE);
        }
    }

    public void unpause() {
        synchronized (monitor) {
            mLastTime = System.currentTimeMillis();
        }
        setState(STATE_RUNNING);
    }

    public void setState(int mode) {
        synchronized (monitor) {
            setState(mode, null);
        }
    }

    public void setState(int mode, CharSequence message) {
        synchronized (monitor) {
            mMode = mode;

            if (mMode == STATE_RUNNING) {
                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("text", "");
                b.putInt("viz", View.INVISIBLE);
                b.putBoolean("showAd", false);
                msg.setData(b);
                mHandler.sendMessage(msg);
            }
            else {
                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();

                Resources res = mContext.getResources();
                CharSequence str = "";
                if (mMode == STATE_READY || mMode==STATE_CONTINUE)
                    str = res.getText(R.string.mode_ready);
                else
                if (mMode == STATE_PAUSE)
                    str = res.getText(R.string.mode_pause);
                else
                if (mMode == STATE_LOSE){
                    str = res.getText(R.string.mode_lose);
                    setScore(0);
                }
                else
                if (mMode == STATE_WIN) {
                    str = res.getText(R.string.mode_win);
                }
                else if (mMode==STATE_MIDLOSE){
                    str = res.getText(R.string.mode_midlose);
                }


                if (message != null) {
                    str = message + "\n" + str;
                }

                b.putString("text", str.toString());
                b.putInt("viz", View.VISIBLE);

                msg.setData(b);
                mHandler.sendMessage(msg);
            }
        }
    }

    public void setSurfaceHolder(SurfaceHolder h) {
        mSurfaceHolder = h;
    }

    public boolean isRunning() {
        return mRun;
    }

    public void setRunning(boolean running) {
        mRun = running;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mMode) {
        this.mMode = mMode;
    }

    public void setScore(long score) {
        this.score = score;

        synchronized (monitor) {
            Message msg = mHandler.obtainMessage();
            Bundle b = new Bundle();
            b.putBoolean("score", true);
            b.putString("text", getScoreString().toString());
            msg.setData(b);
            mHandler.sendMessage(msg);
        }
    }

    public float getScore() {
        return score;
    }

    public void updateScore(long score) {
        this.setScore(this.score + score);
    }


    protected CharSequence getScoreString() {
        return Long.toString(Math.round(this.score));
    }
}