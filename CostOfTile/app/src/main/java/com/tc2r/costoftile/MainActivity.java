package com.tc2r.costoftile;
/*
* Created By Tc2r 01/08/2016
*
* Calculate the total cost of tile it would take to cover
* a floor plan of width and height, using a cost entered
* by the user.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	// Variables for Objects
	private EditText et_wFeet, et_lFeet, et_wInch, et_lInch, et_Cost;
	private EditText et_wFeet2, et_lFeet2, et_wInch2, et_lInch2;
	private EditText et_wFeet3, et_lFeet3, et_wInch3, et_lInch3;
	private Button btn_Calc;
	private TextView tv_Results;
	private View.OnClickListener calcBtnListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			int wF, wF2, wF3, lF, lF2, lF3;
			double wI, wI2, wI3, lI, lI2,lI3, cost;
			try {
				cost = parseDouble(et_Cost.getText().toString());
			}catch (NumberFormatException ex){
				Toast.makeText(context, "Please Enter Cost of Tile!", Toast.LENGTH_LONG).show();

				return;
			}
			try {
				wF = parseInt(et_wFeet.getText().toString());
			}catch (NumberFormatException ex){
				wF = 0;
			}
			try {
				wI = parseDouble(et_wInch.getText().toString());
			}catch (NumberFormatException ex){
				wI = 0;
			}
			try {
				lF = parseInt(et_lFeet.getText().toString());
			}catch (NumberFormatException ex){
				lF = 0;
			}
			try {
				lI = parseDouble(et_lInch.getText().toString());
			}catch (NumberFormatException ex){
				lI = 0;
			}
			try {
				wF2 = parseInt(et_wFeet2.getText().toString());
			}catch (NumberFormatException ex){
				wF2 = 0;
			}
			try {
				wI2 = parseDouble(et_wInch2.getText().toString());
			}catch (NumberFormatException ex){
				wI2 = 0;
			}
			try {
				lF2 = parseInt(et_lFeet2.getText().toString());
			}catch (NumberFormatException ex){
				lF2 = 0;
			}
			try {
				lI2 = parseDouble(et_lInch2.getText().toString());
			}catch (NumberFormatException ex){
				lI2 = 0;
			}

			try {
				wF3 = parseInt(et_wFeet3.getText().toString());
			}catch (NumberFormatException ex){
				wF3 = 0;
			}
			try {
				wI3 = parseDouble(et_wInch3.getText().toString());
			}catch (NumberFormatException ex){
				wI3 = 0;
			}
			try {
				lF3 = parseInt(et_lFeet3.getText().toString());
			}catch (NumberFormatException ex){
				lF3 = 0;
			}
			try {
				lI3 = parseDouble(et_lInch3.getText().toString());
			}catch (NumberFormatException ex){
				lI3 = 0;
			}

			sqftTotal = calculateSqFt(wF,wI,lF,lI,wF2,wI2,lF2,lI2,wF3,wI3,lF3,lI3);
			Log.w(TAG, " cost " + cost);
			finalCost = calcCost(cost, sqftTotal);

			tv_Results.setText("The Cost of tile for " + sqftTotal + " is: $" + finalCost);
		}
	};
	// Variables to Calculate
	private double cost, sqftTotal, finalCost;

	public double calculateSqFt(int wFeet, double wInches, int lFeet, double lInches,
															int wFeet2, double wInches2, int lFeet2, double lInches2,
															int wFeet3, double wInches3, int lFeet3, double lInches3){
		double total1, total2, total3;
		if(wFeet == 0 && wInches == 0 || lFeet == 0 && lInches == 0) {
			total1 = 0;
		}else{
			total1 = (wFeet *12 + wInches) * (lFeet * 12 + lInches);
		}
		if(wFeet2 == 0 && wInches2 == 0 || lFeet2 == 0 && lInches2 == 0) {
			total2 = 0;
		}else{
			total2 = (wFeet2 *12 + wInches2) * (lFeet2 * 12 + lInches2);
		}
		if(wFeet3 == 0 && wInches3 == 0 || lFeet3 == 0 && lInches3 == 0) {
			total3 = 0;
		}else{
			total3 = (wFeet3 *12 + wInches3) * (lFeet3 * 12 + lInches3);
		}


		double sTotal = Math.ceil((total1 + total2 + total3)/144);
		Log.w(TAG, String.valueOf(sTotal));
		return sTotal;
	}

	public double calcCost(double cost, double feet){
		double extra = feet *.10;
		double price = (cost * feet) + extra;

		return price;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_wFeet = (EditText)findViewById(R.id.et_wFeet);
		et_lFeet = (EditText)findViewById(R.id.et_lFeet);
		et_lInch = (EditText)findViewById(R.id.et_lInch);
		et_wInch = (EditText)findViewById(R.id.et_wInch);
		et_wFeet2 = (EditText)findViewById(R.id.et_wFeet2);
		et_lFeet2 = (EditText)findViewById(R.id.et_lFeet2);
		et_lInch2 = (EditText)findViewById(R.id.et_lInch2);
		et_wInch2 = (EditText)findViewById(R.id.et_wInch2);
		et_wFeet3 = (EditText)findViewById(R.id.et_wFeet3);
		et_lFeet3 = (EditText)findViewById(R.id.et_lFeet3);
		et_lInch3 = (EditText)findViewById(R.id.et_lInch3);
		et_wInch3 = (EditText)findViewById(R.id.et_wInch3);
		btn_Calc = (Button) findViewById(R.id.btn_Calc);
		et_Cost = (EditText)findViewById(R.id.et_Cost);
		tv_Results = (TextView)findViewById(R.id.tv_Results);

		btn_Calc.setOnClickListener(calcBtnListener);



	}

}
