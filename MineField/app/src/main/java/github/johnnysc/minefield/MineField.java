package github.johnnysc.minefield;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Hovhannes Asatryan on 16.04.16.
 */

public class MineField extends GameThread {
    public static Bitmap character;
    public static Bitmap cellz;
    public static Bitmap death;
    public static Bitmap aim;
    public static int level=1;
    private float characterX=-200;
    private float characterY=-200;
    private float[][] positionX = new float[6][6];
    private float[][] positionY = new float[6][6];
    private float cellsX= -1000;
    private float cellsY= -1000;
    private float deathX= -1000;
    private float deathY= -1000;
    private float aimX=-200;
    private float aimY=-200;

    public MineField(GameView gameView){
        super(gameView);
    }

    @Override
    public void setupBeginning() {
        for (int i = 0; i < positionX.length; i++) {
            for (int j = 0; j < positionX.length; j++) {
                positionX[i][j] = character.getWidth() / 2 + i * character.getWidth();

            }
        }
        cellsX = 0;
        cellsY = mCanvasHeight / 2 - mCanvasWidth / 2;

        for (int i = 0; i < positionX.length; i++) {
            for (int j = 0; j < positionX.length; j++) {
                positionX[i][j] = j * mCanvasWidth / 6;
                positionY[i][j] = mCanvasHeight / 2 - mCanvasWidth / 2 + i * mCanvasWidth / 6;
            }
        }

        switch (level){
            case 1:
                setAimXandY(0,5);
                setCharacterXandY(5,0);
                break;
            case 2:
                setAimXandY(5,3);
                setCharacterXandY(5,0);
                break;
            case 3:
                setAimXandY(4,0);
                setCharacterXandY(2,2);
                break;
            case 4:
                setAimXandY(0,4);
                setCharacterXandY(2,3);
                break;
            case 5:
                setAimXandY(2,3);
                setCharacterXandY(5,5);
                break;
        }

    }

    private void setAimXandY(int x,int y){
        aimX = positionX[x][y];
        aimY = positionY[x][y];
    }
    private void setCharacterXandY(int x,int y){
        characterX = positionX[x][y];
        characterY = positionY[x][y];
    }

    @Override
    protected void doDraw(Canvas canvas) {
        if(canvas == null) return;
        super.doDraw(canvas);

        canvas.drawBitmap(character, characterX, characterY, null);
        canvas.drawBitmap(cellz,cellsX,cellsY,null);
        canvas.drawBitmap(death, deathX, deathY,null);
        canvas.drawBitmap(aim, aimX, aimY, null);
    }

    @Override
    protected void actionOnTouch(float x, float y) {
        if (y >= mCanvasHeight / 2 + mCanvasWidth / 2) {
            if (x > 0 && x < mCanvasWidth / 4)
                doStepLeft();
            else if (x>mCanvasWidth/4 && x<mCanvasWidth/2)
                doStepRight();
            else if (x>mCanvasWidth/2 && x<3*mCanvasWidth/4)
                doStepDown();
            else if(x>3*mCanvasWidth/4 && x<mCanvasWidth)
                doStepUp();
            checkIsAlive();
        }
    }

    public void doStepLeft(){
        if(characterX>=mCanvasWidth/6)
            characterX-=mCanvasWidth/6;
    }

    public void doStepRight(){
        if(characterX<5*mCanvasWidth/6)
            characterX+=mCanvasWidth/6;

    }
    public void doStepDown(){
        if(characterY<mCanvasHeight/2+mCanvasWidth/3)
            characterY+=mCanvasWidth/6;
    }
    public void doStepUp(){
        if(characterY>=mCanvasHeight/2-mCanvasWidth/3)
            characterY-=mCanvasWidth/6;
    }

    public void checkIsAlive() {
        if(level==1) {
                if (isEqualChXYandPosXY(0,1) ||
                    isEqualChXYandPosXY(0,2) ||
                    isEqualChXYandPosXY(0,3) ||
                    isEqualChXYandPosXY(0,4) ||
                    isEqualChXYandPosXY(1,0) ||
                    isEqualChXYandPosXY(1,4) ||
                    isEqualChXYandPosXY(2,2)||
                    isEqualChXYandPosXY(2,4) ||
                    isEqualChXYandPosXY(3,1) ||
                    isEqualChXYandPosXY(3,4) ||
                    isEqualChXYandPosXY(4,1) ||
                    isEqualChXYandPosXY(4,3) ||
                    isEqualChXYandPosXY(5,1) ||
                    isEqualChXYandPosXY(5,5))
                gameOver();
            else
                    winCase();
        }else if(level==2){
                 if (isEqualChXYandPosXY(0,4) ||
                     isEqualChXYandPosXY(1,5) ||
                     isEqualChXYandPosXY(1,1) ||
                     isEqualChXYandPosXY(1,2) ||
                     isEqualChXYandPosXY(2,3) ||
                     isEqualChXYandPosXY(3,0) ||
                     isEqualChXYandPosXY(3,1) ||
                     isEqualChXYandPosXY(3,3) ||
                     isEqualChXYandPosXY(4,0) ||
                     isEqualChXYandPosXY(3,4) ||
                     isEqualChXYandPosXY(5,2) ||
                     isEqualChXYandPosXY(4,3) ||
                     isEqualChXYandPosXY(5,5))
                     gameOver();
                 else
                     winCase();
        }else if(level==3){
                if (isEqualChXYandPosXY(5,3) ||
                    isEqualChXYandPosXY(1,2) ||
                    isEqualChXYandPosXY(0,5) ||
                    isEqualChXYandPosXY(0,4) ||
                    isEqualChXYandPosXY(1,1) ||
                    isEqualChXYandPosXY(1,5) ||
                    isEqualChXYandPosXY(2,3) ||
                    isEqualChXYandPosXY(2,5) ||
                    isEqualChXYandPosXY(3,1) ||
                    isEqualChXYandPosXY(3,2) ||
                    isEqualChXYandPosXY(3,0) ||
                    isEqualChXYandPosXY(3,5) ||
                    isEqualChXYandPosXY(4,1) ||
                    isEqualChXYandPosXY(4,4))
                gameOver();
            else
                    winCase();
        }else if(level==4){
                if (isEqualChXYandPosXY(0,0) ||
                    isEqualChXYandPosXY(0,2) ||
                    isEqualChXYandPosXY(0,5) ||
                    isEqualChXYandPosXY(0,1) ||
                    isEqualChXYandPosXY(0,3) ||
                    isEqualChXYandPosXY(1,3) ||
                    isEqualChXYandPosXY(2,4) ||
                    isEqualChXYandPosXY(2,1) ||
                    isEqualChXYandPosXY(4,0) ||
                    isEqualChXYandPosXY(3,3) ||
                    isEqualChXYandPosXY(3,2) ||
                    isEqualChXYandPosXY(4,2) ||
                    isEqualChXYandPosXY(4,5) ||
                    isEqualChXYandPosXY(5,0) ||
                    isEqualChXYandPosXY(5,4))
                gameOver();
            else
                    winCase();
        }else if(level==5){
               if ( isEqualChXYandPosXY(0,0) ||
                    isEqualChXYandPosXY(2,0) ||
                    isEqualChXYandPosXY(1,0) ||
                    isEqualChXYandPosXY(1,2) ||
                    isEqualChXYandPosXY(1,3) ||
                    isEqualChXYandPosXY(1,4) ||
                    isEqualChXYandPosXY(2,0) ||
                    isEqualChXYandPosXY(2,2) ||
                    isEqualChXYandPosXY(3,3) ||
                    isEqualChXYandPosXY(3,2) ||
                    isEqualChXYandPosXY(3,4) ||
                    isEqualChXYandPosXY(3,5) ||
                    isEqualChXYandPosXY(4,1) ||
                    isEqualChXYandPosXY(5,3) ||
                    isEqualChXYandPosXY(4,5))
                gameOver();
            else
                   winCase();
        }
    }
    private void winCase(){
        if (isEuqualChXYandPosXY(aimX,aimY))
            win();
        else
            updateScore(1);
    }

    private boolean isEqualChXYandPosXY(int x, int y){
        return (characterX == positionX[x][y] && characterY== positionY[x][y]);
    }

    private boolean isEuqualChXYandPosXY(float x, float y){
        return (characterX == x && characterY== y);
    }

    public void gameOver(){
        deathX=characterX;
        deathY=characterY;
        characterX=-200;
        setState(GameThread.STATE_LOSE);
    }

    public void win(){
        setState(GameThread.STATE_WIN);
        deathX=-200;
        if(level<5) {
            level++;
            setState(GameThread.STATE_READY);
        }
        else
            setState(GameThread.STATE_WIN);
    }
}
