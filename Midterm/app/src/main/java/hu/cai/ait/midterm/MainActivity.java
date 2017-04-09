package hu.cai.ait.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAmt;
    private Button btnSave;
    private ToggleButton btnIncome;
    private Button btnClear;
    private LinearLayout scrollLayout;
    private TextView tvBalance;
    private float balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAmt = (EditText) findViewById(R.id.etAmt);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnIncome = (ToggleButton) findViewById(R.id.btnIncome);
        scrollLayout = (LinearLayout)  findViewById(R.id.scrollLayout);
        btnClear = (Button) findViewById(R.id.btnClear);
        tvBalance = (TextView) findViewById(R.id.tvBalance);
        balance = 0;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etName.getText().toString().equals("") &&
                        (!etAmt.getText().toString().equals("") && !etAmt.getText().toString().equals(getString(R.string.amount)))) {

                    final View item = getLayoutInflater().inflate(R.layout.layout_money, null);

                    TextView tvName = (TextView) item.findViewById(R.id.tvName);
                    tvName.setText(etName.getText());

                    TextView tvAmt = (TextView) item.findViewById(R.id.tvAmt);
                    tvAmt.setText(getString(R.string.dollaSign) + etAmt.getText());

                    ImageView imgIcon = (ImageView) item.findViewById(R.id.imgIcon);
                    if (btnIncome.isChecked()) {
                        imgIcon.setImageResource(R.drawable.expense_icon);
                        balance -= Float.parseFloat(etAmt.getText().toString());
                        tvBalance.setText(getString(R.string.balancePrecursor)+ Float.toString(balance));

                    } else {
                        imgIcon.setImageResource(R.drawable.income_icon);
                        balance += Float.parseFloat(etAmt.getText().toString());
                        tvBalance.setText(getString(R.string.balancePrecursor)+ Float.toString(balance));

                    }

                    scrollLayout.addView(item);

                    etAmt.setText("");
                    etName.setText("");
                }else{
                    if (etName.getText().toString().equals("")){
                        etName.setError(getString(R.string.nameError));
                    }
                    if (etAmt.getText().toString().equals("")||etAmt.getText().toString().equals(getString(R.string.amount))){
                        etAmt.setError(getString(R.string.AmtError));
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollLayout.removeAllViews();
                balance = 0;
                tvBalance.setText(getString(R.string.balancePrecursor)+ Float.toString(balance));

            }
        });

    }
}
