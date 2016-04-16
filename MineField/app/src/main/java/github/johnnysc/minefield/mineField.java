package github.johnnysc.minefield;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Hovhannes Asatryan on 16.04.16.
 */

public class mineField extends GameThread {
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

    public mineField(GameView gameView){
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
        if(level==5) {
            characterX = positionX[5][5];
            characterY = positionY[5][5];
        }else if(level==4) {
            characterX = positionX[2][3];
            characterY = positionY[2][3];
        }else if(level==3) {
            characterX = positionX[2][2];
            characterY = positionY[2][2];
        }else{
            characterX = positionX[5][0];
            characterY = positionY[5][0];
        }

        if(level==1){
            aimX = positionX[0][5];
            aimY = positionY[0][5];
        }else if(level==2){
            aimX = positionX[5][3];
            aimY = positionY[5][3];
        }else if(level==3){
            aimX = positionX[4][0];
            aimY = positionY[4][0];
        }else if(level==4){
            aimX = positionX[0][4];
            aimY = positionY[0][4];
        }else if(level==5){
            aimX = positionX[2][3];
            aimY = positionY[2][3];
        }

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
            if ((characterX == positionX[0][1] && characterY == positionY[0][1]) ||
                    (characterX == positionX[0][2] && characterY == positionY[0][2]) ||
                    (characterX == positionX[0][3] && characterY == positionY[0][3]) ||
                    (characterX == positionX[0][4] && characterY == positionY[0][4]) ||
                    (characterX == positionX[1][0] && characterY == positionY[1][0]) ||
                    (characterX == positionX[1][4] && characterY == positionY[1][4]) ||
                    (characterX == positionX[2][2] && characterY == positionY[2][2]) ||
                    (characterX == positionX[2][4] && characterY == positionY[2][4]) ||
                    (characterX == positionX[3][1] && characterY == positionY[3][1]) ||
                    (characterX == positionX[3][4] && characterY == positionY[3][4]) ||
                    (characterX == positionX[4][1] && characterY == positionY[4][1]) ||
                    (characterX == positionX[4][3] && characterY == positionY[4][3]) ||
                    (characterX == positionX[5][1] && characterY == positionY[5][1]) ||
                    (characterX == positionX[5][5] && characterY == positionY[5][5]))
                gameOver();
            else if ((characterX == aimX && characterY == aimY))
                win();
            else
                updateScore(1);
        }else if(level==2){
                 if ((characterX == positionX[0][4] && characterY == positionY[0][4]) ||
                    (characterX == positionX[1][5] && characterY == positionY[1][5]) ||
                    (characterX == positionX[1][1] && characterY == positionY[1][1]) ||
                    (characterX == positionX[1][2] && characterY == positionY[1][2]) ||
                    (characterX == positionX[2][3] && characterY == positionY[2][3]) ||
                    (characterX == positionX[3][0] && characterY == positionY[3][0]) ||
                    (characterX == positionX[3][1] && characterY == positionY[3][1]) ||
                    (characterX == positionX[3][3] && characterY == positionY[3][3]) ||
                    (characterX == positionX[4][0] && characterY == positionY[4][0]) ||
                    (characterX == positionX[3][4] && characterY == positionY[3][4]) ||
                    (characterX == positionX[5][2] && characterY == positionY[5][2]) ||
                    (characterX == positionX[4][3] && characterY == positionY[4][3]) ||
                    (characterX == positionX[5][5] && characterY == positionY[5][5]))
                     gameOver();
                 else if ((characterX == aimX && characterY == aimY))
                win();
            else
                updateScore(1);
        }else if(level==3){
            if ((characterX == positionX[5][3] && characterY == positionY[5][3]) ||
                    (characterX == positionX[1][2] && characterY == positionY[1][2]) ||
                    (characterX == positionX[0][5] && characterY == positionY[0][5]) ||
                    (characterX == positionX[0][4] && characterY == positionY[0][4]) ||
                    (characterX == positionX[1][1] && characterY == positionY[1][1]) ||
                    (characterX == positionX[1][5] && characterY == positionY[1][5]) ||
                    (characterX == positionX[2][3] && characterY == positionY[2][3]) ||
                    (characterX == positionX[2][5] && characterY == positionY[2][5]) ||
                    (characterX == positionX[3][1] && characterY == positionY[3][1]) ||
                    (characterX == positionX[3][2] && characterY == positionY[3][2]) ||
                    (characterX == positionX[3][0] && characterY == positionY[3][0]) ||
                    (characterX == positionX[3][5] && characterY == positionY[3][5]) ||
                    (characterX == positionX[4][1] && characterY == positionY[4][1]) ||
                    (characterX == positionX[4][4] && characterY == positionY[4][4]))
                gameOver();
            else if ((characterX == aimX && characterY == aimY))
                win();
            else
                updateScore(1);
        }else if(level==4){
            if ((characterX == positionX[0][0] && characterY == positionY[0][0]) ||
                    (characterX == positionX[0][2] && characterY == positionY[0][2]) ||
                    (characterX == positionX[0][5] && characterY == positionY[0][5]) ||
                    (characterX == positionX[0][1] && characterY == positionY[0][1]) ||
                    (characterX == positionX[0][3] && characterY == positionY[0][3]) ||
                    (characterX == positionX[1][3] && characterY == positionY[1][3]) ||
                    (characterX == positionX[2][4] && characterY == positionY[2][4]) ||
                    (characterX == positionX[2][1] && characterY == positionY[2][1]) ||
                    (characterX == positionX[4][0] && characterY == positionY[4][0]) ||
                    (characterX == positionX[3][3] && characterY == positionY[3][3]) ||
                    (characterX == positionX[3][2] && characterY == positionY[3][2]) ||
                    (characterX == positionX[4][2] && characterY == positionY[4][2]) ||
                    (characterX == positionX[4][5] && characterY == positionY[4][5]) ||
                    (characterX == positionX[5][0] && characterY == positionY[5][0]) ||
                    (characterX == positionX[5][4] && characterY == positionY[5][4]))
                gameOver();
            else if ((characterX == aimX && characterY == aimY))
                win();
            else
                updateScore(1);
        }else if(level==5){
            if ((characterX == positionX[0][0] && characterY == positionY[0][0]) ||
                    (characterX == positionX[2][0] && characterY == positionY[2][0]) ||
                    (characterX == positionX[1][0] && characterY == positionY[1][0]) ||
                    (characterX == positionX[1][2] && characterY == positionY[1][2]) ||
                    (characterX == positionX[1][3] && characterY == positionY[1][3]) ||
                    (characterX == positionX[1][4] && characterY == positionY[1][4]) ||
                    (characterX == positionX[2][0] && characterY == positionY[2][0]) ||
                    (characterX == positionX[2][2] && characterY == positionY[2][2]) ||
                    (characterX == positionX[3][3] && characterY == positionY[3][3]) ||
                    (characterX == positionX[3][2] && characterY == positionY[3][2]) ||
                    (characterX == positionX[3][4] && characterY == positionY[3][4]) ||
                    (characterX == positionX[3][5] && characterY == positionY[3][5]) ||
                    (characterX == positionX[4][1] && characterY == positionY[4][1]) ||
                    (characterX == positionX[5][3] && characterY == positionY[5][3]) ||
                    (characterX == positionX[4][5] && characterY == positionY[4][5]))
                gameOver();
            else if ((characterX == aimX && characterY == aimY))
                win();
            else
                updateScore(1);
        }
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
