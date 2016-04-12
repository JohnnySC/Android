package github.johnnysc.rcanoed;

/**
 * Created by JohnnySC on 11.04.16.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class rcanoedGame extends GameThread{

    private Bitmap mBall;
    private float mBallX = -100;
    private float mBallY = -100;
    private float mBallSpeedX = 0;
    private float mBallSpeedY = 0;
    private Bitmap mPaddle;
    private float mPaddleX = 0;
    private float mPaddleSpeedX = 0;
    private Bitmap mRedBrick;
    private Bitmap mYellowBrick;
    private Bitmap mBlueBrick;
    private Bitmap mSteelBrick;
    private float[] mBrickX = new float[32];
    private float[] mBrickY = new float[32];

    private float mMinDistanceBetweenBallAndPaddle = 0;
    private int[] countSteel;
    public static int optionInput = 0;
    public static int loseCounts;
    public static int level=1;
    public static int winCase = 0;


    public rcanoedGame(GameView gameView) {
        super(gameView);

        for(int i=0;i<mBrickX.length;i++){
            mBrickX[i]=-100;
            mBrickY[i]=-100;
        }

        mBall = BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.small_ball);
        mPaddle = BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.paddle);
        mRedBrick =  BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.brickred);

        mYellowBrick =  BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.brickyellow);

        mBlueBrick =  BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.brickblue);

        mSteelBrick =  BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.steelbrick);
    }

    @Override
    public void setupBeginning() {
        loseCounts=0;
        countSteel = level==2? new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} : new int[] {0,0,0,0,0,0,0,0};
        mBallSpeedX = mCanvasWidth / 3;
        mBallSpeedY = mCanvasHeight / 3;
        mBallX = mCanvasWidth / 2;
        mBallY = mCanvasHeight / 2;
        mPaddleX = mCanvasWidth / 2;

        putCells(level);

        mMinDistanceBetweenBallAndPaddle = (mPaddle.getWidth() / 2 + mBall.getWidth() / 2) * (mPaddle.getWidth() / 2 + mBall.getWidth() / 2);
    }

    public void putCells(int level){
        if(level==2){
            winCase=48;
            for(int i=0;i<4;i++) {
                mBrickX[8*i]=mCanvasWidth/2-7*mRedBrick.getWidth()/2;
                mBrickY[8*i]= (5-i)*mRedBrick.getHeight();
            }
            for(int i=1;i<8;i++){
                mBrickX[i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[i] = 5*mRedBrick.getHeight();
                mBrickX[8+i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[8+i] =4*mRedBrick.getHeight();
                mBrickX[16+i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[16+i] = 3*mRedBrick.getHeight();
                mBrickX[24+i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[24+i] =  2*mRedBrick.getHeight();
            }
        }else if (level==1){
            winCase=40;
            for(int i=0;i<4;i++) {
                mBrickX[8*i]=mCanvasWidth/2-7*mRedBrick.getWidth()/2;
                mBrickY[8*i]= (5-i)*mRedBrick.getHeight();
            }
            for(int i=1;i<8;i++){
                mBrickX[i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[i] = 5*mRedBrick.getHeight();
                mBrickX[8+i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[8+i] =4*mRedBrick.getHeight();
                mBrickX[16+i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[16+i] = 3*mRedBrick.getHeight();
                mBrickX[24+i] = mBrickX[i-1]+mRedBrick.getWidth();
                mBrickY[24+i] =  2*mRedBrick.getHeight();
            }
        }
    }

    @Override
    public void setupContinue(){
        mBallSpeedX = mCanvasWidth / 3;
        mBallSpeedY = mCanvasHeight / 3;
        mBallX = mCanvasWidth / 2;
        mBallY = mCanvasHeight / 2;
        mPaddleX = mCanvasWidth / 2;
    }

    @Override
    protected void doDraw(Canvas canvas) {
        if(canvas == null) return;
        super.doDraw(canvas);

        canvas.drawBitmap(mBall, mBallX - mBall.getWidth() / 2, mBallY - mBall.getHeight() / 2, null);
        canvas.drawBitmap(mPaddle, mPaddleX - mPaddle.getWidth() / 2, mCanvasHeight-mPaddle.getHeight()/2-100, null);
        drawLevel(level,canvas);

    }

    public void drawLevel(int level, Canvas canvas){
        if(level==1) {
            for (int i = 0; i < 8; i++)
            canvas.drawBitmap(mYellowBrick, mBrickX[i] - mYellowBrick.getWidth() / 2, mBrickY[i] - mYellowBrick.getHeight() / 2, null);
            for (int i = 8; i < 16; i++)
                canvas.drawBitmap(mBlueBrick, mBrickX[i] - mBlueBrick.getWidth() / 2, mBrickY[i] - mBlueBrick.getHeight() / 2, null);
            for (int i = 16; i < 24; i++)
                canvas.drawBitmap(mRedBrick, mBrickX[i] - mRedBrick.getWidth() / 2, mBrickY[i] - mRedBrick.getHeight() / 2, null);
            for (int i = 24; i < 32; i++)
                canvas.drawBitmap(mSteelBrick, mBrickX[i] - mSteelBrick.getWidth() / 2, mBrickY[i] - mSteelBrick.getHeight() / 2, null);
        }else if(level==2){
            for (int i = 0; i < 8; i++)
                canvas.drawBitmap(mSteelBrick, mBrickX[i] - mSteelBrick.getWidth() / 2, mBrickY[i] - mSteelBrick.getHeight() / 2, null);
            for (int i = 8; i < 16; i++)
                canvas.drawBitmap(mBlueBrick, mBrickX[i] - mBlueBrick.getWidth() / 2, mBrickY[i] - mBlueBrick.getHeight() / 2, null);
            for (int i = 16; i < 24; i++)
                canvas.drawBitmap(mRedBrick, mBrickX[i] - mRedBrick.getWidth() / 2, mBrickY[i] - mRedBrick.getHeight() / 2, null);
            for (int i = 24; i < 32; i++)
                canvas.drawBitmap(mSteelBrick, mBrickX[i] - mSteelBrick.getWidth() / 2, mBrickY[i] - mSteelBrick.getHeight() / 2, null);
        }
    }

    @Override
    protected void actionOnTouch(float x, float y) {
        if(optionInput>=0)
            mPaddleX=x;
        else {
            while (mPaddleX >= 0 && mPaddleX <= mCanvasWidth) {
                if (x < mCanvasWidth / 2 && mPaddleX != 0)
                    mPaddleX -= mCanvasWidth / 10;
                else if (x > mCanvasWidth / 2 && mPaddleX != mCanvasWidth)
                    mPaddleX += mCanvasWidth / 10;
                break;
            }
        }
    }

    @Override
    protected void updateGame(float secondsElapsed) {
        if (mBallSpeedY > 0)
            updateBallCollision(mPaddleX, mCanvasHeight - 100);

        mBallX = mBallX + secondsElapsed * mBallSpeedX;
        mBallY = mBallY + secondsElapsed * mBallSpeedY;

        mPaddleX = mPaddleX + secondsElapsed * mPaddleSpeedX;

        if ((mBallX <= mBall.getWidth() / 2 && mBallSpeedX < 0) ||
                (mBallX >= mCanvasWidth - mBall.getWidth() / 2 && mBallSpeedX > 0))
            mBallSpeedX = -mBallSpeedX;

       preFinishGame();

        if (mBallY <= mBall.getWidth() / 2 && mBallSpeedY < 0)
            mBallSpeedY = -mBallSpeedY;

        if(mBallY>=mCanvasHeight){
            if(loseCounts==2){
                setState(GameThread.STATE_LOSE);
            }else {
                loseCounts++;
                setState(GameThread.STATE_MIDLOSE);
            }
        }

    }

    public void preFinishGame(){
        if(level==1) {
            for (int i = 0; i < 24; i++) {
                if (updateBallCollision(mBrickX[i], mBrickY[i])) {
                    mBrickY[i] = -300;
                    updateScore(1);
                    finishGame();
                }
            }

            for (int i = 24; i < 32; i++) {
                if (updateBallCollision(mBrickX[i], mBrickY[i])) {
                    countSteel[i - 24]++;
                    if (countSteel[i - 24] == 2) {
                        mBrickY[i] = -300;
                        updateScore(2);
                        finishGame();
                    }
                }
            }
        }else if(level==2){
            for (int i = 8; i < 24; i++) {
                if (updateBallCollision(mBrickX[i], mBrickY[i])) {
                    mBrickY[i] = -300;
                    updateScore(1);
                    finishGame();
                }
            }

            for (int i = 24; i < 32; i++) {
                if (updateBallCollision(mBrickX[i], mBrickY[i])) {
                    countSteel[i-16]++;
                    if (countSteel[i - 16] == 2) {
                        mBrickY[i] = -300;
                        updateScore(2);
                        finishGame();
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                if (updateBallCollision(mBrickX[i], mBrickY[i])) {
                    countSteel[i]++;
                    if (countSteel[i] == 2) {
                        mBrickY[i] = -300;
                        updateScore(2);
                        finishGame();
                    }
                }
            }
        }
    }

    public void finishGame(){
        if (score == winCase) {
            setState(GameThread.STATE_WIN);
            level++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setState(GameThread.STATE_READY);
        }
    }


    private boolean updateBallCollision(float x, float y) {
        float distanceBetweenBallAndPaddle = (x - mBallX) * (x - mBallX) + (y - mBallY) *(y - mBallY);

        if(mMinDistanceBetweenBallAndPaddle >= distanceBetweenBallAndPaddle) {
            float speedOfBall = (float) Math.sqrt(mBallSpeedX*mBallSpeedX + mBallSpeedY*mBallSpeedY);

            mBallSpeedX = mBallX - x;
            mBallSpeedY = mBallY - y;

            float newSpeedOfBall = (float) Math.sqrt(mBallSpeedX*mBallSpeedX + mBallSpeedY*mBallSpeedY);

            mBallSpeedX = mBallSpeedX * speedOfBall / newSpeedOfBall;
            mBallSpeedY = mBallSpeedY * speedOfBall / newSpeedOfBall;

            return true;
        }

        return false;
    }
}