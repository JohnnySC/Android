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
    private int[] countSteel=new int[] {0,0,0,0,0,0,0,0};

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
        mBallSpeedX = mCanvasWidth / 3;
        mBallSpeedY = mCanvasHeight / 3;
        mBallX = mCanvasWidth / 2;
        mBallY = mCanvasHeight / 2;
        mPaddleX = mCanvasWidth / 2;

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

        mMinDistanceBetweenBallAndPaddle = (mPaddle.getWidth() / 2 + mBall.getWidth() / 2) * (mPaddle.getWidth() / 2 + mBall.getWidth() / 2);
    }

    @Override
    protected void doDraw(Canvas canvas) {
        if(canvas == null) return;
        super.doDraw(canvas);

        canvas.drawBitmap(mBall, mBallX - mBall.getWidth() / 2, mBallY - mBall.getHeight() / 2, null);
        canvas.drawBitmap(mPaddle, mPaddleX - mPaddle.getWidth() / 2, mCanvasHeight-mPaddle.getHeight()/2-100, null);
        for(int i = 0; i<8; i++)
            canvas.drawBitmap(mYellowBrick, mBrickX[i] - mYellowBrick.getWidth() / 2, mBrickY[i] - mYellowBrick.getHeight() / 2, null);
        for(int i=8;i<16;i++)
            canvas.drawBitmap(mBlueBrick, mBrickX[i] - mBlueBrick.getWidth() / 2, mBrickY[i] - mBlueBrick.getHeight() / 2, null);
        for(int i=16;i<24;i++)
            canvas.drawBitmap(mRedBrick, mBrickX[i] - mRedBrick.getWidth() / 2, mBrickY[i] - mRedBrick.getHeight() / 2, null);
        for(int i=24;i<32;i++)
            canvas.drawBitmap(mSteelBrick, mBrickX[i] - mSteelBrick.getWidth() / 2, mBrickY[i] - mSteelBrick.getHeight() / 2, null);
    }

    @Override
    protected void actionOnTouch(float x, float y) {
        mPaddleX = x;
    }

    @Override
    protected void updateGame(float secondsElapsed) {
        if(mBallSpeedY > 0)
            updateBallCollision(mPaddleX, mCanvasHeight - 100);

        mBallX = mBallX + secondsElapsed * mBallSpeedX;
        mBallY = mBallY + secondsElapsed * mBallSpeedY;

        mPaddleX = mPaddleX + secondsElapsed * mPaddleSpeedX;

        if((mBallX <= mBall.getWidth() / 2 && mBallSpeedX < 0) ||
            (mBallX >= mCanvasWidth - mBall.getWidth() / 2 && mBallSpeedX > 0) )
                        mBallSpeedX = -mBallSpeedX;

        for(int i = 0; i < 24; i++) {
            if(updateBallCollision(mBrickX[i], mBrickY[i])){
                mBrickY[i]=-300;
                updateScore(1);
                if(score==32)
                    setState(GameThread.STATE_WIN);
            }
        }

        for(int i=24;i<32;i++){
            if(updateBallCollision(mBrickX[i],mBrickY[i])){
                countSteel[i-24]++;
                if(countSteel[i-24]==2){
                    mBrickY[i]=-300;
                    updateScore(1);
                    if(score==32)
                        setState(GameThread.STATE_WIN);
                }
            }
        }

        if(mBallY <= mBall.getWidth() / 2 && mBallSpeedY < 0)
            mBallSpeedY = -mBallSpeedY;

        if(mBallY >= mCanvasHeight)
            setState(GameThread.STATE_LOSE);
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