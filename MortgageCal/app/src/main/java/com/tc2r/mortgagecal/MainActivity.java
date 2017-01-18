package com.tc2r.mortgagecal;

/*
* Created By Tc2r 01/04/2017
*
* Mortgage Calculator - Calculate the monthly payments
* of a fixed term mortgage over given Nth terms
* at a given interest rate. Also figure out how long
* it will take the user to pay back the loan.
* */
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private double loan, iRate, mPayment, balance, mPay, monthsToPay = 0;
	private EditText etLoan, etiRate, etTerm,etmPay;
	private TextView tvMPayment, tvBalance;
	private Button calc;
	private Context mine;
	int term = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mine = getApplicationContext();


		etLoan = (EditText) findViewById(R.id.et_loanAmount);
		etiRate = (EditText) findViewById(R.id.et_irate);
		etTerm = (EditText) findViewById(R.id.et_term);
		etmPay = (EditText) findViewById(R.id.et_pAmount);
		tvBalance = (TextView) findViewById(R.id.tv_balance);
		tvMPayment = (TextView) findViewById(R.id.tv_mpayment);
		calc = (Button) findViewById(R.id.btn_findPayment);


		calc.setOnClickListener(CalculateData);


	}

	/**
	 * Calculate the monthly payment of a loan.
	 *
	 * @param loan         Amount borrowed
	 * @param interestRate Interest rate on the loan
	 * @param term         Repayment term in years
	 * @return The monthly payment of a loan given interest rate, amount and term
	 */
	public static double getMonthlyPayment(double loan, double interestRate, double term) {
		double result;

		double rate = (interestRate / 100) / 12;

		double base = (1 + rate);

		// Take years for loan, divide into months.
		double nper = term * 12;

		// Formula for calculating monthly payments:
		result = loan * ((rate * Math.pow(base, nper)) / (Math.pow(base, nper) - 1));
		return result;

	}
	public double PayBack(double loan, double interestRate, double mPayment) {
		double purePayments = loan / mPayment;
		double interestPayments = purePayments * (interestRate/100);
		double totalmPayments = Math.ceil(purePayments) + Math.ceil(interestPayments);

		return totalmPayments;
	}

	public static double getBalance(double monthly, double term) {
		double result = 0;
		result = (monthly * (term * 12));
		return result;
	}


	private View.OnClickListener CalculateData = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Context context = v.getContext();

			try {
				loan = Double.parseDouble(etLoan.getText().toString());
			} catch (NumberFormatException ee) {
				loan = 0;
			}
			try {
				iRate = Double.parseDouble(etiRate.getText().toString());
			} catch (NumberFormatException ee) {
				iRate = 0;
			}
			try {
				term = Integer.parseInt(etTerm.getText().toString());
			} catch (NumberFormatException ee) {
				term = 0;
			}
			try {
				mPay = Double.parseDouble(etmPay.getText().toString());
			} catch (NumberFormatException ee) {

				mPay = 0;
			}




			if(mPay < 1) {
				mPayment = getMonthlyPayment(loan, iRate, term);
				balance = getBalance(mPayment, term);

				// String.format to keep decimal places within 2 digits
				tvBalance.setText("Amount owed on loan: $" + String.format("%.2f", balance));
				tvMPayment.setText(String.format("Min. Monthly Payment: $ %.2f", +mPayment));


			}else if(mPay > 0){
				monthsToPay = (PayBack(loan, iRate, mPay));
				double yearsToPay = Math.round((monthsToPay/12)*100.0)/100.0;
				Toast.makeText(context, " "+ yearsToPay, Toast.LENGTH_LONG).show();



				//for(int i = 0 )
				// A = P(1 + rt)
				balance = loan*(1+((iRate/100) * yearsToPay));

				double totalInterest = (balance - loan);

				// String.format to keep decimal places within 2 digits
				tvBalance.setText("Total Interest Amount: $" + String.format("%.2f", totalInterest));
				tvMPayment.setText("It will take " + monthsToPay + " more payments or "+ yearsToPay + " years to pay off the remaining balance");
			}
		}
	};

}
