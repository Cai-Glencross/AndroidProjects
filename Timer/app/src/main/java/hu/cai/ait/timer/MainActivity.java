package hu.cai.ait.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public TextView tvTimer;
    public Button btnStart;
    public Button btnStop;
    public Button btnMark;
    public LinearLayout layout;

    public class MyTimerTask extends TimerTask {


        private final long startTime;
        public MyTimerTask() {
            startTime= System.currentTimeMillis();
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int ellapsedTime = (int)(
                            System.currentTimeMillis()-startTime)/1000;
                    tvTimer.setText(""+ellapsedTime);
                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = (TextView) findViewById(R.id.tvTimer);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnMark = (Button) findViewById(R.id.btnMark);
        layout = (LinearLayout) findViewById(R.id.activity_main);

        final Timer timer = new Timer();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    timer.schedule(new MyTimerTask(), 0, 1000);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });

        btnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewTodo = getLayoutInflater().inflate(R.layout.marked_time, null);

                TextView tvTodo = (TextView) viewTodo.findViewById(R.id.tvTodo);
                tvTodo.setText(tvTimer.getText());

                layout.addView(viewTodo);
            }
        });


    }
}
