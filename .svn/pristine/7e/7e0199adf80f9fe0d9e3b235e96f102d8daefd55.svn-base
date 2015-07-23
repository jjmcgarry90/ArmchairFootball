package org.gtri.helper;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationHelper {

	private Context context;
	private int icon;
	private String message, title;
	private Class<? extends Activity> intentClass;

	private int NOTIFICATION_ID = 1;

	/*
	 * Constructor
	 */
	public NotificationHelper(Context context, int icon, String message, String title, Class<? extends Activity> intentClass) {
		this.context = context;
		this.icon = icon;
		this.message = message;
		this.title = title;
		this.intentClass = intentClass;
		setup();
	}

	public void setup() {
		// Create the notification
		Notification n = new Notification(icon, title, System.currentTimeMillis());

		// And the Intent of what to do when the user selects it
		Intent intent = new Intent(context, intentClass);
		PendingIntent wrappedIntent = PendingIntent.getActivity(context, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);

		// Set notification information
		n.setLatestEventInfo(context, title, message, wrappedIntent);
		n.flags |= Notification.FLAG_AUTO_CANCEL;
		n.defaults |= Notification.DEFAULT_ALL;

		// Invoke the notification service
		NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mgr.notify(NOTIFICATION_ID, n);
	}

}