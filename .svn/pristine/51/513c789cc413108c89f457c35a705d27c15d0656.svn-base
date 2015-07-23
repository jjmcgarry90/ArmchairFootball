package org.gtri.espn.activities.login;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.espn.activities.main.HomeActivity;
import org.gtri.espn.activities.main.MainActivity;
import org.gtri.espn.activities.main.PredictActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginScreenActivity extends Activity {

	private FanbookApplication context;
	
	// Possible request codes
	private static final int CHOICE_FACEBOOK = 0;
	private static final int CHOICE_EMAIL = 1;
	private TextView welcomeText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		context = (FanbookApplication) getApplicationContext();
		
		//load up preferences and save them to the application context *OLD COMMENT, DONE AUTOMATICALLY 
		
		welcomeText = (TextView) findViewById(R.id.textViewWelcome);
		// User must choose between logging in with Facebook or Email
		// The choice is saved and used automatically for future app launches

		Button buttonLoginFacebook = (Button) findViewById(R.id.buttonLoginFacebook);
		buttonLoginFacebook.setOnClickListener(loginFacebookListener);

		Button buttonLoginEmail = (Button) findViewById(R.id.buttonLoginEmail);
		buttonLoginEmail.setOnClickListener(loginEmailListener);
		
		Button continueButton = (Button) findViewById(R.id.buttonContinue);
		continueButton.setOnClickListener(continueListener);
		Button changeUserButton = (Button) findViewById(R.id.buttonChangeUser);
		changeUserButton.setOnClickListener(changeUserListener);
		
		//determine if user data is already present. If so, allow the user to 
		//continue using saved preferences or change users
		String name = context.getPreferences().getString(getString(R.string.settingsUsername), null);
		if (context.getPreferences().getBoolean(getString(R.string.firstRun), true) || name == null)
		{
			//FIRST RUN
			findViewById(R.id.layoutNewUser).setVisibility(View.VISIBLE);
			findViewById(R.id.layoutReturnUser).setVisibility(View.GONE);
		}
		else
		{
			findViewById(R.id.layoutNewUser).setVisibility(View.GONE);
			findViewById(R.id.layoutReturnUser).setVisibility(View.VISIBLE);
			//String name = context.getPreferences().getString(getString(R.string.settingsUsername), null);
			welcomeText.setText("Welcome Back, " + name + ".");
		}
	}

	/*
	 * User wants to login via Facebook
	 */
	private OnClickListener loginFacebookListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), FacebookActivity.class);
			startActivityForResult(intent, CHOICE_FACEBOOK);
			//context.choseFacebook();
			context.usingFacebook = true;
			finish();
		}
	};

	/*
	 * User wants to login via email
	 */
	private OnClickListener loginEmailListener = new OnClickListener() {
		public void onClick(View v) {
			//Intent intent = new Intent(getBaseContext(), LoginEmailActivity.class);
			Intent intent = new Intent(getBaseContext(), LoginEmailActivity.class);
			//context.choseEmail();
			context.usingFacebook = false;
			startActivityForResult(intent, CHOICE_EMAIL);
			finish();
		}
	};
	
	private OnClickListener continueListener = new OnClickListener() {
		public void onClick(View v) {
			//Intent intent = new Intent(getBaseContext(), LoginEmailActivity.class);
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			//context.choseEmail();
			context.usingFacebook = false;
			startActivityForResult(intent, CHOICE_EMAIL);
			finish();
		}
	};
	
	private OnClickListener changeUserListener = new OnClickListener() {
		public void onClick(View v) {
			//clear user prefs
			context.resetPreferences();
			
			//allow user to do fresh login
			welcomeText.setText("Welcome, Friend!");
			findViewById(R.id.layoutNewUser).setVisibility(View.VISIBLE);
			findViewById(R.id.layoutReturnUser).setVisibility(View.GONE);
			
		}
	};
	
	/*
	 * On later activity results
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOICE_FACEBOOK) {
            if (resultCode == RESULT_OK) {
            	// Finish and send results
            	finish();
            }
        } else if (requestCode == CHOICE_EMAIL) {
        	if (resultCode == RESULT_OK) {
        		// Finish and send results
    			finish();
            }
        }
    }
	
	/*
	 * Override back button for confirmed exit
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    //Handle the back button
	    if(context.getPreferences().getBoolean(getString(R.string.confirmQuit), true) && keyCode == KeyEvent.KEYCODE_BACK) {
	        //Ask the user if they want to quit
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle(R.string.dialog_quit)
	        .setMessage(R.string.dialog_confirm)
	        .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                //Stop the activity
	                finish();    
	            }
	        })
	        .setNegativeButton(R.string.dialog_negative, null)
	        .show();
	        return true;
	    } else {
	        return super.onKeyDown(keyCode, event);
	    }
	}

}
