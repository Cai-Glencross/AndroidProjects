package hu.ait.lifecycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("LIFE_TAG", "onCreate called");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("LIFE_TAG", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("LIFE_TAG", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("LIFE_TAG", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("LIFE_TAG", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("LIFE_TAG", "onDestroy called");
    }

}
