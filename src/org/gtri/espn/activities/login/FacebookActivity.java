package org.gtri.espn.activities.login;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.espn.activities.main.HomeActivity;
import org.gtri.helper.SessionStore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

/*
 * If the user's account has expired or wants to log in via facebook
 */
public class FacebookActivity extends Activity {

	private FanbookApplication context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = (FanbookApplication) getApplicationContext();
		authorize();
	}

	/*
	 * Authorize if the session is invalid
	 */
	public void authorize() {
		if (!context.getFacebook().isSessionValid()) {
			context.getFacebook()
					.authorize(this, new String[] { getString(R.string.email), getString(R.string.publishStream) }, Facebook.FORCE_DIALOG_AUTH, (DialogListener) new LoginDialogListener());
		} else {
			goHome();
		}
	}

	/*
	 * Once session is valid, go to home screen
	 */
	public void goHome() {
		if (context.getFacebook().isSessionValid()) {
			Intent intent = new Intent(getBaseContext(), HomeActivity.class);
			startActivity(intent);
			// Finish and send results
			setResult(RESULT_OK);
			finish();
		}
	}

	/*
	 * Exit without valid session
	 */
	public void error() {
		Toast.makeText(getBaseContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
		setResult(RESULT_CANCELED);
		finish();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		context.getFacebook().authorizeCallback(requestCode, resultCode, data);
	}

	/*
	 * Custom dialog implementation of facebook dialog methods
	 */
	private class LoginDialogListener implements DialogListener {
		public void onComplete(Bundle values) {
			try {
				// The user has logged in
				SessionStore.save(context.getFacebook(), getBaseContext());
			} catch (Exception error) {
				Toast.makeText(getBaseContext(), error.toString(), Toast.LENGTH_SHORT).show();
			}
			context.load();
			goHome();
		}

		public void onFacebookError(FacebookError error) {
			error();
		}

		public void onError(DialogError error) {
			error();
		}

		public void onCancel() {
			setResult(RESULT_CANCELED);
			finish();
		}

	}

}