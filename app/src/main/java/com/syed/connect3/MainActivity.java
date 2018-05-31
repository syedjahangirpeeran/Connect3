package com.syed.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningStates = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {
            counter.setTranslationY(-1000f);
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
                activePlayer = 0;
            }
            for (int[] winningState : winningStates) {
                if (gameState[winningState[0]] == gameState[winningState[1]] &&
                        gameState[winningState[1]] == gameState[winningState[2]] &&
                        gameState[winningState[0]] != 2) {
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText("Player "+activePlayer+" Won!");
                    LinearLayout Layout = findViewById(R.id.playAgainLayout);
                    Layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        LinearLayout Layout = findViewById(R.id.playAgainLayout);
        Layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i=0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i=0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
