package hu.cai.ait.minesweeper;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import hu.cai.ait.minesweeper.View.GameView;

public class MainActivity extends AppCompatActivity {

    private Button btnFlag;
    private Button btnRestart;
    private static TextView tvBombsLeft;
    private static Chronometer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GameView gameView = (GameView) findViewById(R.id.gameView);
        btnFlag = (Button) findViewById(R.id.btnFlag);
        btnFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MineModel.getInstance().changeFlag();
                if(MineModel.flag==true) {
                    btnFlag.setText(R.string.DontFlagTxt);
                }else{
                    btnFlag.setText(R.string.flagTxt);
                }
            }
        });

        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MineModel.getInstance().resetModel();
                gameView.invalidate();
            }
        });

        tvBombsLeft = (TextView) findViewById(R.id.tvBombsLeft);
        tvBombsLeft.setText("Bombs Left: " + Integer.toString(Cell.getBombsNotFound()));

        timer = (Chronometer) findViewById(R.id.timer);
        timer.start();
    }

    public static void resetClock(){
        timer.stop();
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    public static void updateCounter(){
        tvBombsLeft.setText("Bombs Left: " + Integer.toString(Cell.getBombsNotFound()));
    }

    public static void stopTimer(){
        timer.stop();
    }


}
