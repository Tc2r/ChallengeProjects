package com.tc2r.primefactor;
/**
 * Created by Tc2r on 1/07/2016.
 *
 * Prime Factorization - Have the user enter a number and find all Prime Factors
 * (if there are any) and display them.
 ***/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private Button goButton;
	private EditText inputNum;
	private TextView result;
	private int[] primeFactors = new int[8];

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		goButton = (Button) findViewById(R.id.go_btn);
		inputNum = (EditText) findViewById(R.id.et_inputNum);
		result = (TextView) findViewById(R.id.tv_result);

		goButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int userNum = Integer.parseInt(inputNum.getText().toString());
				primalFactoring(userNum);
			}
		});
	}

	public void primalFactoring(int input) {

		// Set local variable to passed input
		int n = input;

		// Reset Results and Int Array

		result.setText("");
		for (int i = 0; i < primeFactors.length; ++i) {
			primeFactors[i] = 0;
		}

		// First Prime Number is 2, count from 2 til userInput
		for (int i = 2; i <= n; i++) {

			// If i goes into the user number without a remainder and i is prime
			if (n % i == 0 && isPrime(i)) {
				// Check to see if Prime Number is already
				// added before adding it to Int Array in a blank spot.

				for (int j = 0; j < primeFactors.length; j++) {
//                    if already found, break for loop
//                    if(primeFactors[j] == i){
//                        Log.w(TAG,"Break, Already Found!");
//                        break;
//                    }

					// if a blank slot is found in the array (0), use it, break for loop)
					if (primeFactors[j] == 0) {
						primeFactors[j] = i;
						break;

					}
				}
			}
		}
		// Add int array slots to result.TextView
		for (int k = 0; k < primeFactors.length; k++) {
			if (primeFactors[k] == 0) {
				break;
			}
			result.append(String.valueOf(primeFactors[k]) + ", ");
			//Log.w(TAG, String.valueOf(primeFactors[k]));
		}
	}
}

