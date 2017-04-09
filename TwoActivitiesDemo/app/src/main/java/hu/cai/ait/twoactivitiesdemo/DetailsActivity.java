package hu.cai.ait.twoactivitiesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView tvData = (TextView) findViewById(R.id.tvData);

        if (getIntent().getExtras().containsKey(MainActivity.KEY_DATA)){
            tvData.setText(getIntent().getStringExtra(MainActivity.KEY_DATA));
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("whatev", "details activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("whatev", "details activity resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("whatev", "details activity destroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("whatev", "details activity stopped");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("whatev", "details activity paused");
    }
}
