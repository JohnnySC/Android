package github.johnnysc.tictactoegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static Cell[] cells = new Cell[9];
    public static int player = 2;
    public static boolean isTheEnd = false;
    public static int count=0;
    public static TextView textView;
    public static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<9;i++){
            cells[i]=new Cell();
        }

        textView = (TextView) findViewById(R.id.textView);
        cells[0].setImageButton((ImageButton) findViewById(R.id.cell0));
        cells[1].setImageButton((ImageButton) findViewById(R.id.cell1));
        cells[2].setImageButton((ImageButton) findViewById(R.id.cell2));
        cells[3].setImageButton((ImageButton) findViewById(R.id.cell3));
        cells[4].setImageButton((ImageButton) findViewById(R.id.cell4));
        cells[5].setImageButton((ImageButton) findViewById(R.id.cell5));
        cells[6].setImageButton((ImageButton) findViewById(R.id.cell6));
        cells[7].setImageButton((ImageButton) findViewById(R.id.cell7));
        cells[8].setImageButton((ImageButton) findViewById(R.id.cell8));
        Button button = (Button) findViewById(R.id.button);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.cell0:
                        doStep(cells[0]);
                        break;
                    case R.id.cell1:
                        doStep(cells[1]);
                        break;
                    case R.id.cell2:
                        doStep(cells[2]);
                        break;
                    case R.id.cell3:
                        doStep(cells[3]);
                        break;
                    case R.id.cell4:
                        doStep(cells[4]);
                        break;
                    case R.id.cell5:
                        doStep(cells[5]);
                        break;
                    case R.id.cell6:
                        doStep(cells[6]);
                        break;
                    case R.id.cell7:
                        doStep(cells[7]);
                        break;
                    case R.id.cell8:
                        doStep(cells[8]);
                        break;
                    case R.id.button:
                        startNewGame();
                        break;
                }
            }
        };

        for(int i=0;i<9;i++){
            cells[i].getImageButton().setOnClickListener(onClickListener);
        }
        button.setOnClickListener(onClickListener);

        changePlayer();
    }

    public void doStep(Cell currentCell){
        if(currentCell.isEmpty() && !isTheEnd) {
            if (player == 1) {
                currentCell.getImageButton().setImageResource(R.drawable.x);
                currentCell.setValue(1);
            } else {
                currentCell.getImageButton().setImageResource(R.drawable.o);
                currentCell.setValue(2);
            }
            currentCell.fill();
            count++;
            checkTheEnd();
        }
    }

    public void changePlayer(){
        if(player==1)
            player=2;
        else
            player=1;
        String s0 = "Player " + player;
        textView.setText(s0);
    }

    public void checkTheEnd(){
        for(int i=0;i<3;i++) {
            if(cells[3*i].getValue()==cells[3*i+1].getValue() && cells[3*i+1].getValue()==cells[3*i+2].getValue() && cells[3*i].getValue()>0)
            {
                isTheEnd = true;
                highlightCells(cells[3*i],cells[3*i+1],cells[3*i+2]);
            }
             else if (cells[i].getValue()==cells[i+3].getValue() && cells[i+3].getValue()==cells[i+6].getValue() && cells[i].getValue()>0)
            {
                isTheEnd=true;
                highlightCells(cells[i],cells[i+3],cells[i+6]);
            }
        }
        if(cells[0].getValue()==cells[4].getValue() && cells[4].getValue()==cells[8].getValue() && cells[8].getValue()>0 )
        {
            isTheEnd = true;
            highlightCells(cells[0],cells[4],cells[8]);
        }
        else if(cells[2].getValue()==cells[4].getValue() && cells[4].getValue()==cells[6].getValue() && cells[6].getValue()>0  ) {
           isTheEnd = true;
            highlightCells(cells[2],cells[4],cells[6]);
        }  showGameOver();
    }

    public void highlightCells(Cell cell1,Cell cell2, Cell cell3) {
        if (cell1.getValue() == 1) {
            cell1.getImageButton().setImageResource(R.drawable.x2);
            cell2.getImageButton().setImageResource(R.drawable.x2);
            cell3.getImageButton().setImageResource(R.drawable.x2);
        }
        else {
            cell1.getImageButton().setImageResource(R.drawable.o2);
            cell2.getImageButton().setImageResource(R.drawable.o2);
            cell3.getImageButton().setImageResource(R.drawable.o2);
        }

    }
   public void showGameOver(){
        String result1 = "Game Over! Player "+player+" won!";
        String result2 = "Game Over! Draw!";
        if(isTheEnd)
            textView.setText(result1);
        else if(count==9)
            textView.setText(result2);
        else
            changePlayer();
   }

    public void startNewGame(){
        for(Cell cell: cells) {
            cell.refreshCell();
            cell.getImageButton().setImageResource(R.drawable.empty);
        }
        player=1;
        isTheEnd=false;
        count=0;
        textView.setText("New Game! Player 1");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}