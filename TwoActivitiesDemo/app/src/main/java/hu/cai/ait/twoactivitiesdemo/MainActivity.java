package hu.cai.ait.twoactivitiesdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etData = (EditText) findViewById(R.id.etData);
        Button btnOk = (Button) findViewById(R.id.nextButton);

        btnOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentShowDetails = new Intent();
                intentShowDetails.setClass(MainActivity.this, DetailsActivity.class);

                intentShowDetails.putExtra(KEY_DATA, etData.getText().toString());
                startActivity(intentShowDetails);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("whatev", "main activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("whatev", "main activity resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("whatev", "main activity destroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("whatev", "main activity stopped");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("whatev", "main activity paused");
    }
}
