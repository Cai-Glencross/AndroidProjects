package hu.ait.ait_time;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_TAG = "MAIN-TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTime = (Button) findViewById(R.id.btnTime);
        final EditText user = (EditText) findViewById(R.id.userName);
        final TextView timeTxt= (TextView) findViewById(R.id.tvStatus);
        final LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.activity_main);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(MAIN_TAG,"button pressed")
                String time="Hey " + user.getText().toString()+" the date is: "+new Date(System.currentTimeMillis()).toString();
                Toast.makeText(MainActivity.this,
                        time,
                        Toast.LENGTH_LONG
                ).show();
                timeTxt.setText(time);

                Snackbar.make(layoutRoot,time,Snackbar.LENGTH_LONG).show();

            }
        });




    }
}
