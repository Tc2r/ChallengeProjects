package com.tc2r.binarydecimal;
/**
 * Created by Tc2r on 1/02/2016.
 *
 * Develop a converter to convert a decimal number to binary
 * or a binary number to its decimal equivalent.
 *
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


	private final static String TAG = MainActivity.class.getSimpleName();
	private EditText etDec, etBin;
	private Button convert;
	private TextView results;
	private RadioButton rbToBinary, rbToDecimal;
	private RadioGroup rgConversion;
	private int[] binArray = new int[20];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etBin = (EditText) findViewById(R.id.et_enterbinary);
		etDec = (EditText) findViewById(R.id.et_enterdecimal);
		convert = (Button) findViewById(R.id.btn_bindecconvert);
		results = (TextView) findViewById(R.id.tv_binResults);
		rbToBinary = (RadioButton) findViewById(R.id.rb_Binary);
		rbToDecimal = (RadioButton) findViewById(R.id.rb_Decimal);
		rgConversion = (RadioGroup) findViewById(R.id.rg_Binary);


		convert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int RBID = rgConversion.getCheckedRadioButtonId();
				double newDec = Double.parseDouble(etDec.getText().toString());
				String biNumber = etBin.getText().toString();

				// switch for conversion
				if (RBID == rbToBinary.getId()) {
					ConvertToBinary(newDec);
				} else {
					int FastRoute = Integer.parseInt(biNumber, 2);
					results.setText(FastRoute + "\n is the  Decimal for " + biNumber);
				}
			}
		});
	}


	private void ConvertToBinary(double dec) {
		double newDec = dec;
		String FastRoute = Integer.toBinaryString((int) newDec);
		Log.w(TAG, FastRoute);
		int count = 0;

		results.setText("The Binary for " + String.valueOf(newDec) + " is: \n");
		for (int i = 0; i < binArray.length; i++) {
			if (newDec > 0) {
				binArray[i] = (int) (newDec % 2);
				newDec = (int) (newDec / 2);

			} else {
				count = (i - 1);

				break;
			}
		}
		Log.w(TAG, String.valueOf(count));
		for (int j = count; j >= 0; j--) {
			results.append(String.valueOf(binArray[j]));
		}

	}
}
