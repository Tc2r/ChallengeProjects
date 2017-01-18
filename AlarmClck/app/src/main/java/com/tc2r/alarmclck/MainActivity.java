package com.tc2r.alarmclck;

/*
* Created By Tc2r on 01/10/2016
*
* A simple clock where it plays a sound after X
* number of minutes/seconds or at a particular time.
*
* Expanded:
* Added ability to accept a list of ring tones
* and play ringtone + notification at set time.
* */


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


	private static final String TAG = MainActivity.class.getSimpleName();
	private TimePicker timePicker;
	private Calendar calendar;
	private TextView updateText;
	private Button alarmOn, alarmOff;
	private Context context;
	PendingIntent pendingIntent;
	AlarmManager alarmManager;
	Intent intent;
	private int alarmQuote, alarmHour, alarmMinute;
	private Spinner spinner;
	private ArrayAdapter arrayAdapter;
	private String format = "";
	private String timeString;
	private String aState = "unset";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;



		// Initializing Variables and Views
		timePicker = (TimePicker) findViewById(R.id.tp_ac);
		updateText = (TextView) findViewById(R.id.tv_ac_timeupdate);
		alarmOff = (Button) findViewById(R.id.btn_ac_unset);
		alarmOn = (Button) findViewById(R.id.btn_ac_set);
		calendar = Calendar.getInstance();
		spinner = (Spinner) findViewById(R.id.spin_ac_sounds);
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		arrayAdapter = ArrayAdapter.createFromResource(context, R.array.ringtone_array,
						android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);

		// Initialize Intent to the Alarm receiver class
		intent = new Intent(MainActivity.this, AlarmReceiver.class);

		// On Click Listeners for buttons.
		aState = "unset";
		alarmOn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// Get Timepicker variables:
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					// set calendar
					calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
					calendar.set(Calendar.MINUTE, timePicker.getMinute());
					alarmHour = calendar.get(Calendar.HOUR_OF_DAY);
					alarmMinute = calendar.get(Calendar.MINUTE);

				}else{
					calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
					calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
					alarmHour = calendar.get(Calendar.HOUR_OF_DAY);
					alarmMinute = calendar.get(Calendar.MINUTE);
				}
				// Get the string variables of the time.

				timeString = setTimeString(alarmHour, alarmMinute);
				setAlarmText(timeString);

				// add extra string into intent
				aState = "on";
				intent.putExtra("alarmState", aState);
				intent.putExtra("tone", alarmQuote);

				// create pending intent that delays the intent
				// until the specified calendar time.

				pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

				// set the alarm manager
				// RTC_WakeUP wakes device when alarm goes off
				alarmManager.set(AlarmManager.RTC_WAKEUP,
								calendar.getTimeInMillis(), pendingIntent);

			}
		});
		alarmOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Cancel the pending Intent
				alarmManager.cancel(pendingIntent);

				if(aState == "on") {
					AlertDialog alertDialog = new AlertDialog.Builder(context)
									.setMessage(timeString + " Alarm Cancelled")
									.show();

					aState = "off";
				}
				intent.putExtra("tone", alarmQuote);
				intent.putExtra("alarmState", aState);
				sendBroadcast(intent);

			}
		});


		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				alarmQuote = (int)id;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


	}


	private String setTimeString(int hour, int min){
		String s;
		String minString = String.valueOf(min);
		String hourString = String.valueOf(hour);

		if (hour == 0) {
			hourString = String.valueOf(hour + 12);
			format = "AM";
		}else if(hour == 12) {
			format = "PM";
		}else if(hour > 12) {
			hourString = String.valueOf(hour - 12);
			format = "PM";
		}else {
			format = "AM";
		}
		if(min < 10){
			minString = "0" + String.valueOf(min);
		}
		s = (new StringBuilder()
						.append(hourString)
						.append(":")
						.append(minString)
						.append(" ")
						.append(format).toString());

		return s;
	}
	private void setAlarmText(String output) {
		updateText.setText(output);
	}
}
