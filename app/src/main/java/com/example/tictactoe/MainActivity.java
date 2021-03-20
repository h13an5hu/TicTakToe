package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity<winPositions> extends AppCompatActivity {
    TextView textView;
    Button btn;
    // WIN POSITION
    int[][] winPositions ={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    String winnerStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.button10);
    }

    public void disablingButton(View view)  {
        ((Button)findViewById(R.id.button1)).setEnabled(false);
        ((Button)findViewById(R.id.button2)).setEnabled(false);
        ((Button)findViewById(R.id.button3)).setEnabled(false);
        ((Button)findViewById(R.id.button4)).setEnabled(false);
        ((Button)findViewById(R.id.button5)).setEnabled(false);
        ((Button)findViewById(R.id.button6)).setEnabled(false);
        ((Button)findViewById(R.id.button7)).setEnabled(false);
        ((Button)findViewById(R.id.button8)).setEnabled(false);
        ((Button)findViewById(R.id.button9)).setEnabled(false);
    }
    public void enablingButton(View view) {
        ((Button)findViewById(R.id.button1)).setEnabled(true);
        ((Button)findViewById(R.id.button2)).setEnabled(true);
        ((Button)findViewById(R.id.button3)).setEnabled(true);
        ((Button)findViewById(R.id.button4)).setEnabled(true);
        ((Button)findViewById(R.id.button5)).setEnabled(true);
        ((Button)findViewById(R.id.button6)).setEnabled(true);
        ((Button)findViewById(R.id.button7)).setEnabled(true);
        ((Button)findViewById(R.id.button8)).setEnabled(true);
        ((Button)findViewById(R.id.button9)).setEnabled(true);
    }


    int active_player = 1;
    int tappedcount = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State Meaning :
    // 0 : O
    // 1 : X
    // 2 : Null

    public void gameReset(View view) {
            tappedcount=0;
            gameActive = true;
            active_player = 1;
            for(int i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }
            Log.d("reset gameActive", gameActive+"");
            ((Button)findViewById(R.id.button1)).setText("");
            ((Button)findViewById(R.id.button2)).setText("");
            ((Button)findViewById(R.id.button3)).setText("");
            ((Button)findViewById(R.id.button4)).setText("");
            ((Button)findViewById(R.id.button5)).setText("");
            ((Button)findViewById(R.id.button6)).setText("");
            ((Button)findViewById(R.id.button7)).setText("");
            ((Button)findViewById(R.id.button8)).setText("");
            ((Button)findViewById(R.id.button9)).setText("");

            //Enabling Buttons
            enablingButton(view);

            TextView textView = findViewById(R.id.textView);
            textView.setText("X's Turn");
           // Log.d("tappedcount", tappedcount+"");

        }

    public void keyPressed(View view) {
        btn.setVisibility(View.VISIBLE);
        Button btn = (Button) view;
        btn.setEnabled(false);
        int tappedBtn = Integer.parseInt(btn.getTag().toString() );
//        Log.d("btn_value", tappedBtn+"");
        if(!gameActive){
            gameReset(view);
            Log.d("!GameActive", gameActive+"");
        }
        if(gameState[tappedBtn] == 2) {
            Log.d("=gameActive", gameActive+"");
            gameState[tappedBtn] = active_player;
            btn.setTranslationY(-100f);
            if(active_player == 0) {
              //  Log.d("Active in IF ", active_player+"");
                btn.setText("O");
                active_player = 1;
                textView.setText("'X' ur turn");
                Toast.makeText(this, "X's Turn" , Toast.LENGTH_LONG);
            }
            else {
              //  Log.d("Active in ELSE ", active_player+"");
                btn.setText("X");
                active_player = 0;
                Toast.makeText(this, "O's Turn" , Toast.LENGTH_LONG);
                textView.setText("'O' ur turn");
            }
            btn.animate().translationYBy(100f).setDuration(100);
        }
        btn.setBackgroundColor(Color.BLACK);
        btn.setEnabled(false);
        tappedcount++;

        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]  !=2  ){

                // Somebody has won! - Find out who!

                gameActive = false;
                Log.d("winingpos gameState", gameState+" ");
                if(gameState[winPosition[0]] == 1){
                    winnerStr = "X has won";
                    disablingButton(view);
                    //Toast.makeText(this, "X has Won!" , Toast.LENGTH_LONG);
                   // Log.d("winX",gameState[winPosition[0]] +" ");
                }
                else{
                    winnerStr = "O has won";
                    disablingButton(view);
                    //Toast.makeText(this, "O has Won!" , Toast.LENGTH_LONG);
                }
                // Update the status bar for winner announcement
                TextView textView = findViewById(R.id.textView);
                textView.setText(winnerStr);
                Log.d("InLoopLasts", winnerStr+" "+ gameState);
            }
            if(tappedcount==9) {
                textView.setText("MATCH DRAWED");

              //  gameReset(view);
            }
        }

    }

}