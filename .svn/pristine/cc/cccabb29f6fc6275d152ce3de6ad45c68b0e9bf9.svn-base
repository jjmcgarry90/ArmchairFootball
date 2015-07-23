package org.gtri.espn.dialogs;

import org.gtri.espn.R;
import org.gtri.espn.activities.general.EventListActivity;
import org.gtri.espn.activities.general.FriendListActivity;
import org.gtri.espn.activities.general.ParticipantListActivity;
import org.gtri.espn.activities.main.HomeActivity;
import org.gtri.espn.activities.main.ProfileActivity;
import org.gtri.espn.activities.main.SettingsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class MenuDialog extends Activity {

	private ImageButton buttonGames, buttonFriends, buttonTeams, buttonProfile, buttonSettings, buttonCancel;

	// Constants for inter-activity actions
	public static final int CHOICE_SETTINGS = 1;
	public static final int RESULT_LOGOUT = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_menu);

		// Setup navigation buttons
		buttonGames = (ImageButton) findViewById(R.id.buttonGames);
		buttonGames.setOnClickListener(gamesListener);
		buttonTeams = (ImageButton) findViewById(R.id.buttonTeams);
		buttonTeams.setOnClickListener(teamsListener);
		buttonFriends = (ImageButton) findViewById(R.id.buttonFriends);
		buttonFriends.setOnClickListener(friendsListener);
		buttonProfile = (ImageButton) findViewById(R.id.buttonProfile);
		buttonProfile.setOnClickListener(profileListener);
		buttonSettings = (ImageButton) findViewById(R.id.buttonSettings);
		buttonSettings.setOnClickListener(settingsListener);
		buttonCancel = (ImageButton) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(cancelListener);
	} // end method

	/*
	 * Games
	 */
	private OnClickListener gamesListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), EventListActivity.class);
			startActivity(intent);
			finish();
		}
	};

	/*
	 * Teams
	 */
	private OnClickListener teamsListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), ParticipantListActivity.class);
			startActivity(intent);
			finish();
		}
	};

	/*
	 * Friends
	 */
	private OnClickListener friendsListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), FriendListActivity.class);
			startActivity(intent);
			finish();
		}
	};

	/*
	 * Profile
	 */
	private OnClickListener profileListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
			startActivity(intent);
			finish();
		}
	};

	/*
	 * Settings
	 */
	private OnClickListener settingsListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
			startActivityForResult(intent, CHOICE_SETTINGS);
		}
	};
	
	/*
	 * Cancel
	 */
	private OnClickListener cancelListener = new OnClickListener() {
		public void onClick(View v) {
			// Force back to home
			finish();
		}
	};

	/*
	 * On later activity results
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CHOICE_SETTINGS) {
			if (resultCode == RESULT_LOGOUT) {
				// Finish activity on logout
				setResult(HomeActivity.RESULT_LOGOUT);
				finish();
			} else {
				finish();
			}
		}
	}

} // end class