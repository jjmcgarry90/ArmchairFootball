package org.gtri.espn.activities.main;

import org.gtri.espn.R;
import org.gtri.espn.dialogs.ResetTeamDialog;
import org.gtri.helper.SessionStore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private Button buttonLogout, buttonFavoriteTeam;
	private CheckBox checkBoxConfirmExit, checkBoxSplashScreen;
	private FanbookApplication context;
	private String favoriteTeam;
	private TextView textViewTeamName, textViewUserInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		context = (FanbookApplication) getApplicationContext();

		// Current user info
		textViewUserInfo = (TextView) findViewById(R.id.textViewUserInfo);
		textViewUserInfo.setText(context.getUser().getName());

		checkBoxConfirmExit = (CheckBox) findViewById(R.id.checkBoxConfirmExit);
		checkBoxConfirmExit.setChecked(context.getPreferences().getBoolean(getString(R.string.confirmQuit), true));
		
		checkBoxSplashScreen = (CheckBox) findViewById(R.id.checkBoxSplashScreen);
		checkBoxSplashScreen.setChecked(context.getPreferences().getBoolean(getString(R.string.splashActive), true));
		
		buttonFavoriteTeam = (Button) findViewById(R.id.buttonFavoriteTeam);
		buttonFavoriteTeam.setOnClickListener(favoriteListener);

		buttonLogout = (Button) findViewById(R.id.buttonLogout);
		buttonLogout.setOnClickListener(logoutListener);
	}

	/*
	 * Always load favorite team
	 */
	protected void onResume() {
		super.onResume();
		// Current favorite team
		if (context.getUser().getFavorite() == null) {
			favoriteTeam = "None";
		} else {
			favoriteTeam = context.getUser().getFavorite().getName();
		}
		textViewTeamName = (TextView) findViewById(R.id.textViewTeamName);
		textViewTeamName.setText(favoriteTeam);
	}

	/*
	 * Favorite team listener
	 */
	private OnClickListener favoriteListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), ResetTeamDialog.class);
			startActivity(intent);
		}
	};
	
	/*
	 * Override for auto-save on leave
	 */
	protected void onPause() {
		super.onPause();
		saveSettings();
	}

	/*
	 * Save all the user's changed settings
	 */
	private void saveSettings() {
		// Save all the user's settings
		SharedPreferences.Editor ed = context.getPreferences().edit();
		ed.putBoolean(getString(R.string.splashActive), checkBoxSplashScreen.isChecked());
		ed.putBoolean(getString(R.string.confirmQuit), checkBoxConfirmExit.isChecked());
		ed.putString(getString(R.string.settingsFavorite), favoriteTeam);
		ed.commit();
		if (favoriteTeam.equals("None")) {
			context.getUser().setFavorite(null);
		} else {
			context.getUser().setFavorite(context.getSchedule().getParticipantByName(favoriteTeam));
		}
	}

	/*
	 * Logout
	 */
	private OnClickListener logoutListener = new OnClickListener() {
		public void onClick(View v) {
			// Ask the user if they want to quit
			new AlertDialog.Builder(SettingsActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.dialog_logout)
					.setMessage(R.string.dialog_confirm_logout).setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// logout, set result, close app
							logout();
							setResult(HomeActivity.RESULT_LOGOUT);
							finish();
						}
					}).setNegativeButton(R.string.dialog_negative, null).show();
		}

	};

	/*
	 * Logout the current user and wipe preferences
	 */
	private void logout() {
		// Wipes all user data from local device
		SharedPreferences.Editor ed = context.getPreferences().edit();
		ed.clear();
		ed.commit();
		SessionStore.clear(context);
		try {
			context.getFacebook().logout(context);
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), "Error.", Toast.LENGTH_SHORT).show();
		}

		context.setUser(null);

	}

}
