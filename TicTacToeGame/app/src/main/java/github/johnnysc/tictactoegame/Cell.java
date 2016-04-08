package github.johnnysc.tictactoegame;

import android.widget.ImageButton;

/**
 * Created by johnny on 07.04.16.
 */
public class Cell {
    private ImageButton imageButton;
    private boolean Empty=true;
    private int value=0;

    public ImageButton getImageButton(){
        return imageButton;
    }
    public void setImageButton(ImageButton imgButton){
        this.imageButton = imgButton;
    }
    public void fill(){
        this.Empty=false;
    }
    public boolean isEmpty(){
        return Empty;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }

}
