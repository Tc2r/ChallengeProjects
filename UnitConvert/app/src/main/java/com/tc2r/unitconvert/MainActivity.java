package com.tc2r.unitconvert;

/*
* Created By Tc2r on 01/09/2016
*
* Unit Converter (temp, currency, volume, mass and more)
* Converts various units between one another.
* The user enters the type of unit being entered,
* the type of unit they want to convert to and then the value.
* The program will then make the conversion.
* */


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private EditText fromInput;
	private TextView intoOutput;
	private Spinner convertFrom, convertInto, converttype;
	private ArrayAdapter<String> adapterFrom, adapterInto;
	private String[] UnitTypes = new String[]{"Mass", "Volume", "Currency", "Temperature"};

	private String[] massItems = new String[]{"g", "oz", "lb", "kg"};
	private Double[] massMeasurements = new Double[]{453.59, 16., 1., 0.45359};

	private String[] volumeItems = new String[]{"c", "tsp", "tbsp", "q", "p", "gal", "oz", "l", "ml"};
	private Double[] volumeMeasurements = new Double[]{1., 48., 16., .25, .5, .0625, 8., 236.588};

	private String[] moneyItems = new String[]{"USD ($)", "CHF (Fr)", "EUR (€)", "JPY (¥)", "GBP (£)", "CAD ($)", "AUS", "CNY (¥)", "SEK (kr)", "MXN ($)", "SGD ($)", "HKD ($)", "NOK (kr)", "KRW (₩)", "INR (₹)", "BRL (R$)"};
	private Double[] moneyMeasurements = new Double[]{1., 1.01, 1.13, 114.53, .82, 1.31, 1.33, 6.9, 8.91, 21.48, 1.43, 7.75, 8.5, 1175.07, 68.12, 3.22};

	private String[] tempItems = new String[]{"C", "F", "K"};


	private Double measurement;
	private String typeName;
	private double inputMod = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		convertFrom = (Spinner) findViewById(R.id.spin_from);
		convertInto = (Spinner) findViewById(R.id.spin_into);
		converttype = (Spinner) findViewById(R.id.spin_UnitType);
		fromInput = (EditText) findViewById(R.id.et_FromInput);
		intoOutput = (TextView) findViewById(R.id.tv_IntoResult);
		adapterInto = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, UnitTypes);
		converttype.setAdapter(adapterInto);


		converttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (parent.getItemAtPosition(position).toString()) {
					case ("Mass"):

						adapterFrom = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, massItems);
						adapterInto = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, massItems);

						// Set variable for type name
						typeName = "Mass";
						break;

					case ("Volume"):

						adapterFrom = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, volumeItems);
						adapterInto = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, volumeItems);
						typeName = "Volume";

						break;
					case ("Currency"):
						adapterFrom = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, moneyItems);
						adapterInto = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, moneyItems);
						typeName = "Currency";

						break;
					case ("Temperature"):
						adapterFrom = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, tempItems);
						adapterInto = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, tempItems);
						typeName = "Temperature";

						break;

				}

				convertFrom.setAdapter(adapterFrom);
				convertInto.setAdapter(adapterFrom);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		convertFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (typeName == "Mass") {
					inputMod = massMeasurements[position];
				}
				if (typeName == "Volume") {
					inputMod = volumeMeasurements[position];
				}
				if (typeName == "Currency") {
					inputMod = moneyMeasurements[position];
				}
				convertInto.setAdapter(adapterInto);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		convertInto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Calculate(typeName);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		fromInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					Calculate(typeName);
				}
			}
		});

		fromInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					Context context = getApplicationContext();

					Calculate(typeName);
					return true;
				}
				return false;
			}
		});


	}

	public void Calculate(String type) {
		double inputNumber;
		double answer;
		try {
			inputNumber = Double.parseDouble(fromInput.getText().toString());
		} catch (NumberFormatException ee) {
			Toast.makeText(getApplicationContext(), "Please Input An Amount To Convert", Toast.LENGTH_LONG).show();
			fromInput.setText("1");
			return;
		}
		switch (type) {
			case "Mass":
				// MASS!
				measurement = massMeasurements[convertInto.getSelectedItemPosition()];
				// Convert to pounds
				answer = (inputNumber / inputMod);
				// Convert to output
				answer = (answer * measurement);
				//answer = (inputNumber / inputMod) * measurement;
				intoOutput.setText(String.valueOf(answer));
				break;

			case "Volume":
				// VOLUME!
				measurement = volumeMeasurements[convertInto.getSelectedItemPosition()];
				// Convert to cups
				answer = (inputNumber / inputMod);
				// Convert to output
				answer = (answer * measurement);
				intoOutput.setText(String.valueOf(answer));
				break;
			case "Currency":
				// VOLUME!
				measurement = moneyMeasurements[convertInto.getSelectedItemPosition()];
				// Convert to USD
				answer = (inputNumber / inputMod);
				// Convert to output
				answer = (answer * measurement);
				answer = Math.round(answer * 100000);
				answer = answer / 100000;
				intoOutput.setText(String.valueOf(answer));
				break;
			case "Temperature":
				// TEMPERATURE
				String inputType = convertFrom.getSelectedItem().toString();
				String outputType = convertInto.getSelectedItem().toString();
				answer = 1.0;

				// Convert to Celsius!
				switch (inputType) {
					case "K":

						if (outputType == "K") {
							answer = inputNumber;
						} else if (outputType == "F") {
							answer = (inputNumber - 273.15) * (9. / 5.) + 32.;
						} else if (outputType == "C") {
							answer = inputNumber - 273.15;
						}
						break;
					case "C":

						if (outputType == "K") {
							answer = inputNumber + 273.15;
						} else if (outputType == "F") {
							answer = (inputNumber * (9. / 5.)) + 32.;
						} else if (outputType == "C") {
							answer = inputNumber;
						}

						break;
					case "F":

						if (outputType == "K") {
							answer = ((inputNumber - 32.) * (5. / 9.) + 275.15);
						} else if (outputType == "F") {
							answer = inputNumber;
						} else if (outputType == "C") {
							answer = (inputNumber - 32.) * (5. / 9.);
						}
						break;
				}
				answer = Math.round(answer * 100);
				answer = answer / 100;
				intoOutput.setText(String.valueOf(answer));
				break;
		}
	}
}

