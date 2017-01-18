package com.tc2r.alarmclck;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;




public class RingService extends Service {

	private static final String TAG = RingService.class.getSimpleName();
	private String alarmState;
	private MediaPlayer mediaplayer;
	private int ringTone;
	private int startSound = 0;
	private boolean isRunning;
	private NotificationManager notificationManager;
	private Notification notification;
	private PendingIntent pendingIntent;
	private Intent nIntent;

	public RingService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		nIntent = new Intent(this.getApplicationContext(), MainActivity.class);
		pendingIntent = PendingIntent.getActivity(this, 0, nIntent, 0);
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notification = new Notification.Builder(this)
						.setContentTitle("ALARM CLOCK!")
						.setContentText("PRESS HERE")
						.setContentIntent(pendingIntent)
						.setSmallIcon(R.drawable.ic_action_call)
						.setAutoCancel(true)
						.build();

		alarmState = intent.getStringExtra("alarmState");
		ringTone = intent.getIntExtra("tone", 0);






		switch (alarmState) {
			case "on":
				startSound = 1;
				//Log.e(TAG, "ALARM ON!");
				break;
			case "off":
				//Log.e(TAG, "ALARM OFF!");
				startSound = 0;
				break;
			default:
				//Log.e(TAG, "ALARM Off!");
				startSound = 0;
				break;
		}

		if (!this.isRunning && startSound == 1) {
			this.isRunning = true;
			startSound = 0;
			ringTone++;
			Log.wtf(TAG, "START ALARM : Ringtone "+ ringTone);


			notificationManager.notify(0, notification);
			switch (ringTone) {
				case 1:
					mediaplayer = MediaPlayer.create(this, R.raw.ringtone);
					break;
				case 2:
					mediaplayer = MediaPlayer.create(this, R.raw.ringtone);
					break;
				case 3:
					mediaplayer = MediaPlayer.create(this, R.raw.ringtone);
					break;
				case 4:
					mediaplayer = MediaPlayer.create(this, R.raw.ringtone);
					break;
				case 5:
					mediaplayer = MediaPlayer.create(this, R.raw.ringtone);
					break;
				default:
					mediaplayer = MediaPlayer.create(this, R.raw.ringtone);

					break;
			}
			mediaplayer.start();


		} else if (startSound == 0 && this.isRunning) {
			Log.e(TAG, "STOP ALARM");
			this.isRunning = false;
			startSound = 0;
			mediaplayer.stop();
			mediaplayer.reset();
		} else if (startSound == 0 && !this.isRunning) {
			Log.e(TAG, "PREAMPT STOP ALARM");
			this.isRunning = false;
			startSound = 0;
		} else if (startSound == 1 && this.isRunning) {

			this.isRunning = true;
			startSound = 0;
		}else{
			Log.e(TAG,"WEIRD");
			this.isRunning = false;
			startSound = 0;
		}
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		this.isRunning = false;
		super.onDestroy();
	}
}
