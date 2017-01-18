package com.tc2r.nextprimenum;
/**
 * Created by Tc2r on 1/05/2016.
 *
 * Next Prime Number - Have the program find prime numbers until
 * the user chooses to stop asking for the next one
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private final String TAG = getClass().getSimpleName();
	private TextView resultTV;
	private Button nextBtn;
	private int num, nextNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultTV = (TextView)findViewById(R.id.tv_PNresult);
		nextBtn =  (Button) findViewById(R.id.PNnext_btn);
		num = 2;
		nextNum = 2;

		nextBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int num = nextNum;
				resultTV.setText(findPrime(num));

			}
		});
	}

	private String findPrime(int lastNum){
		int num = lastNum + 1;
		while(!isPrime(num)){
			Log.w(TAG, num + " Is not prime");
			num++;
		}
		nextNum = num;
		return String.valueOf(nextNum);
	}

	private static boolean isPrime(int num) {
		// Default True
		boolean bool = true;
		// Check each number from 1 til inputnumber for even division into inputnum
		// if num can be evenly divided, it is not prime.
		for (int i = 2; i < num && bool; i++) {
			if (num % i == 0)
				bool = false;
		}
		return bool;

	}
}
