package hu.cai.ait.highlowgame;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String KEY_RAND = "KEY_RAND";
    private int random = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(KEY_RAND)) {
            random = savedInstanceState.getInt(KEY_RAND);
        } else {
            generateRandomNumber();
        }

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        Button btnGuess = (Button) findViewById(R.id.btnGuess);
        final TextView tvStatus = (TextView) findViewById(R.id.tvStatus);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                        int myGuess = Integer.parseInt(etGuess.getText().toString());

                        if (myGuess == random) {
                            tvStatus.setText("You Won!");

                            startActivity(new Intent(GameActivity.this, ResultActivity.class));
                        } else if (myGuess < random) {
                            tvStatus.setText("Your guess is too low!");
                        } else if (myGuess > random) {
                            tvStatus.setText("Your guess is too high");
                        }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void generateRandomNumber() {
        random = new Random(System.currentTimeMillis()).nextInt(3);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_RAND, random);
    }


}
