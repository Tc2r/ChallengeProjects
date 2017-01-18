package com.tc2r.alarmclck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Tc2r on 1/17/2017.
 * <p>
 * Description:
 */

public class AlarmReceiver extends BroadcastReceiver{
	private static final String TAG = AlarmReceiver.class.getSimpleName();
	Intent ringIntent;
	@Override
	public void onReceive(Context context, Intent intent) {

		// get extra data alarmstate and sound from intent
		String alarmState = intent.getStringExtra("alarmState");
		int alarmTone = intent.getIntExtra("tone", 0);
		Log.wtf(TAG, "Turning Alarm " + alarmState);

		ringIntent = new Intent(context, RingService.class);
		ringIntent.putExtra("alarmState", alarmState);
		ringIntent.putExtra("tone", alarmTone);
		context.startService(ringIntent);

		// pass it to service
	}
}
