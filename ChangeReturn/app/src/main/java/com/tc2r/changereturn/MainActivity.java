package com.tc2r.changereturn;

/**
 * Created by Tc2r on 1/01/2016.
 *
 * The user enters a cost and then the amount of money given.
 * The program will figure out the change and the number of
 * quarters, dimes, nickels, pennies needed for the change.
 *
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private EditText etCost, etChange;
	private double price, payment, change;
	private Button btnChange;
	private TextView results;
	private final static double quarter = 0.25;
	private final static double dime = 0.10;
	private final static double nickel = 0.05;
	private final static double pennies = 0.01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etChange = (EditText) findViewById(R.id.et_enterChange);
		etCost = (EditText) findViewById(R.id.et_enterCost);
		btnChange = (Button) findViewById(R.id.btn_bindecconvert);
		results = (TextView) findViewById(R.id.tv_results);

		btnChange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					price = Double.parseDouble(etCost.getText().toString());
				} catch (NumberFormatException ee) {
					price = 0;
					Toast.makeText(v.getContext(), "Please Enter Cost", Toast.LENGTH_SHORT).show();
				}
				try {
					payment = Double.parseDouble(etChange.getText().toString());
				} catch (NumberFormatException ee) {
					payment = 0;
					Toast.makeText(v.getContext(), "Please Enter Cash Given", Toast.LENGTH_SHORT).show();
				}
				if (payment > price) {
					change = payment - price;
					btnChange.setText("$ " + String.format("%.2f", change));
					int qChange = (int) (change / quarter);
					change = change % quarter;

					int dChange = (int) (change / dime);
					change = change % dime;

					int nChange = (int) (change / nickel);
					change = (Math.round(change % nickel * 100.0) / 100.00);

					// Pennies have to be rounded up because there is no smaller currency
					int pChange = (int) (change / pennies);

					results.setSingleLine(false);
					results.setText(qChange + " Quarters \n");
					results.append(dChange + " Dimes \n");
					results.append(nChange + " Nickles \n");
					results.append(pChange + " Pennies \n");


				} else {
					Toast.makeText(v.getContext(), "You Did Not Give Enough", Toast.LENGTH_SHORT).show();
					results.setText("Add More Change!");
				}
			}
		});
	}
}

