package com.tc2r.pitothenth;
/**
 * Created by Tc2r on 1/07/2016.
 *
 * Find PI to the Nth Digit - Enter a number and have the program generate PI
 * up to that many decimal places. Keep a limit to how far the program will go!
 **/
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private Button goButton;
	private EditText digit;
	private TextView result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		goButton = (Button)findViewById(R.id.go_btn);
		digit = (EditText) findViewById(R.id.et_digit);
		result = (TextView) findViewById(R.id.tv_result);

		goButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int userDigit = Integer.parseInt(digit.getText().toString());
				callFindPie(userDigit);
			}
		});
	}

	public void callFindPie(int dig){
		int n = dig;

		// Ways to get PI

		// double pi = Math.PI;
		double pi = ((16 * Math.atan(1.0/5.0)) - (4*Math.atan(1.0/239.0)));


		//Getting the Decimal Points Only
		double piDigits = Math.floor((pi % 1.0) * (Math.pow(10.0,n)));
		// Adding the 3 back
		pi= 3.0+(piDigits / Math.pow(10.0,n));

		//printing the results
		Log.w(TAG, String.valueOf(3+(Math.floor((Math.PI % 1.0) * (Math.pow(10.0,n))) / (Math.pow(10.0,n)))));
		result.setText(String.valueOf(pi));

	}

}
