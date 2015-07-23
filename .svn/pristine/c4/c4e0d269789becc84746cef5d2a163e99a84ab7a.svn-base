package org.gtri.espn.activities.main;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.gtri.espn.R;
import org.gtri.helper.DatabaseHandler;
import org.gtri.helper.EspnApiHandler;
import org.gtri.helper.FacebookHelper;
import org.gtri.helper.SessionStore;
import org.gtri.objects.Participant;
import org.gtri.objects.Schedule;
import org.gtri.objects.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;

public class FanbookApplication extends Application {

	private Facebook facebook;
	private SharedPreferences preferences;
	private AsyncFacebookRunner runner;
	private User user;
	private Schedule schedule;
	private String dbEmail;
	private String dbUsername;
	private boolean existingEmail;
	private EspnApiHandler handler;
	private Timer notifyTimer;
	public boolean usingFacebook = false;
	
	public void onCreate() {
		super.onCreate();
		notifyTimer = new Timer(true);
		loadPreferences();
	}
	
	public void onResume() {
		facebook.extendAccessTokenIfNeeded(this, null);
	}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	public AsyncFacebookRunner getRunner() {
		return runner;
	}

	public void setRunner(AsyncFacebookRunner mAsyncRunner) {
		this.runner = mAsyncRunner;
	}

	public SharedPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(SharedPreferences preferences) {
		this.preferences = preferences;
	}

	public boolean isEmailSessionValid() {
		if (this.getPreferences().getString(getString(R.string.settingsEmail), null) != null
				&& this.getPreferences().getString(getString(R.string.settingsUsername), null) != null) {
			return true;
		}
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDbEmail(String dbEmail) {
		this.dbEmail = dbEmail;
	}

	public String getDbEmail() {
		return dbEmail;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public boolean isExistingEmail() {
		return existingEmail;
	}

	public void setExistingEmail(boolean existingEmail) {
		this.existingEmail = existingEmail;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public JSONObject createJSON(String json) {
		JSONArray jArray = null;
		JSONObject jObj = null;
		try {
			jArray = new JSONArray(json);
			Log.v("test", jArray.toString());
			jObj = (JSONObject) jArray.get(0);
		}

		catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;
	}

	/*
	 * Determine if previous saved facebook data on device return true if data
	 * is found return false if invalid data is found return null if not data is
	 * found
	 */
	private Boolean checkFacebookData() {
		SessionStore.restore(getFacebook(), this);
		return (getFacebook().isSessionValid() && usingFacebook);
	}

	/*
	 * Determine if previous saved email data on device return true if data is
	 * found return false if no data is found
	 */
	private boolean checkEmailData() {
		return isEmailSessionValid();
	}

	/*
	 * Attempt to load from local memory
	 */
	public void load() {
		/*
		 * 3 options - checked in this order: Facebook Data authorized Email
		 * data No data
		 */
		if (checkFacebookData() == true) {
			// Facebook data
			FacebookHelper helper = new FacebookHelper(this);
			String name = helper.getName();
			String picUrl = helper.getPicture();
			ArrayList<User> friends = helper.getFriends();
			Log.v("espn", facebook.getAppId());
			if (name != null) {
				setUser(new User());
				getUser().setName(name);
				getUser().addFriend(friends);
			} else {
				return;
			}
			if (picUrl != null)
			{
				getUser().setProfilePic(FacebookHelper.getBitmap(picUrl));
			}
		} else if (checkEmailData() == true) {
			// Email data
			setUser(new User(getPreferences().getString(getString(R.string.settingsEmail), null), getPreferences()
					.getString(getString(R.string.settingsUsername), null)));
			DatabaseHandler.getFriends(getUser().getEmail());
			getUser().setProfilePic(((BitmapDrawable)this.getResources().getDrawable(R.drawable.default_pic)).getBitmap());

		} else {
			// User newUser = new User();
			// newUser.setLoginType(null);
			// setUser(newUser);
			// no data, so go to login screen
		}
	}

	public EspnApiHandler getHandler() {
		return handler;
	}

	public void setHandler(EspnApiHandler handler) {
		this.handler = handler;
	}
	
	public void enableNotificationTask(TimerTask taskToExecute)
	{
		notifyTimer.schedule(taskToExecute, 5000, 5000);
	}
	
	public Timer getNotificationTimer() {
		return notifyTimer;
	}
	
	
	public void disableNotificationTask(TimerTask taskToCancel)
	{
		taskToCancel.cancel();
		notifyTimer.purge();
	}

	public void resetPreferences() {
		SharedPreferences.Editor ed = getPreferences().edit();
		ed.putBoolean(getString(R.string.firstRun), false);
		ed.putBoolean(getString(R.string.splashActive), true);
		ed.putBoolean(getString(R.string.confirmQuit), true);
		ed.putString(getString(R.string.settingsEmail), null);
		ed.putString(getString(R.string.settingsUsername), null);
		ed.putString(getString(R.string.settingsFavorite), null);
		ed.commit();
		
	}
	
	public void loadPreferences() {
		this.preferences = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE);
	}
	
	public void updatePreferences() {
		//call if the user changes their favorite teams or email or something
		//clears all preferences, then re-adds everything. We must clear them because the number of watched teams can vary
		SharedPreferences.Editor ed = preferences.edit();
		ed.clear();
		ed.putBoolean(getString(R.string.firstRun), false);
		ed.putBoolean(getString(R.string.splashActive), true);
		ed.putBoolean(getString(R.string.confirmQuit), true);
		ed.putString(getString(R.string.settingsEmail), user.getEmail());
		ed.putString(getString(R.string.settingsUsername), user.getName());
		ed.putString(getString(R.string.settingsFavorite), user.getFavorite().getName());
//		ArrayList<Participant> watchedTeams = user.getWatched();
//		
//		for (int i = 0; i < watchedTeams.size(); i++)
//		{
//			ed.putString(R.string.settingsWatchedTeams + String.valueOf(i), watchedTeams.get(i).getName());
//		}
		ed.commit();
	}
}
