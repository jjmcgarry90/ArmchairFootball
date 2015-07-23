package org.gtri.espn.activities.login;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.espn.activities.main.HomeActivity;
import org.gtri.espn.activities.main.MainActivity;
import org.gtri.objects.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * The user must enter a user name to proceed
 */
public class LoginUsernameActivity extends Activity {

	private FanbookApplication context;
	private Button buttonCancel, buttonSave;
	private EditText editTextUsername;
	private String email, username;
	private ProgressDialog spinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_username);
		context = (FanbookApplication) getApplicationContext();

		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(cancelListener);

		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(saveListener);

		editTextUsername = (EditText) findViewById(R.id.editTextUsername);
		email = LoginEmailActivity.email;
	}

	/*
	 * Cancel
	 */
	private OnClickListener cancelListener = new OnClickListener() {
		public void onClick(View v) {
			// Force to go back to email login screen
			setResult(RESULT_CANCELED);
			finish();
		}
	};

	/*
	 * Save
	 */
	private OnClickListener saveListener = new OnClickListener() {
		public void onClick(View v) {
			// TODO - check if user name is valid
			username = editTextUsername.getText().toString();

			// Loading screen
			spinner = new ProgressDialog(v.getContext());
			spinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
			spinner.setMessage("Saving...");
			spinner.show();

			if (isValidUsername(username)) {

				// if the email already exists, check for matching usernames
//				if (context.isExistingEmail()) {
//
//					Log.v("test", "email on file");
//					// usernames match
//					if (username.equals(context.getDbUsername())) {
//
//						SharedPreferences.Editor ed = context.getPreferences().edit();
//						ed.putString(getString(R.string.settingsEmail), email);
//						ed.putString(getString(R.string.settingsUsername), username);
//						ed.commit();
//
//						context.load();
//						spinner.dismiss();
//						Intent intent = new Intent(getBaseContext(), HomeActivity.class);
//						startActivity(intent);
//						// Finish and send results
//						setResult(RESULT_OK);
//						finish();
//					}
//
//					// usernames did not match
//					else {
//						Log.v("test", "usernames do not match");
//						Toast.makeText(getBaseContext(), "Username does not match username on file!", Toast.LENGTH_LONG).show();
//					}
//
//				}

				// if this is a new email address, go ahead and save it
				//else {
					//save();
				try {
					context.setUser(new User(email, username));
					Intent intent = new Intent(getBaseContext(), MainActivity.class);
					startActivity(intent);
					// Finish and send results
					setResult(RESULT_OK);
					spinner.dismiss();
					finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//}

			}
		}
	};

	/*
	 * Checks validity of the username
	 */
	private boolean isValidUsername(String str) {
		// TODO - check string for validity (sql injections, formatting, etc.)
		if (str == null || str.equals("")) {
			Toast.makeText(getBaseContext(), "Invalid username.", Toast.LENGTH_LONG).show();
			return false;
		}
		// TODO - check database for uniqueness
		// if (!isUnique(str)) {
		// Toast.makeText(getBaseContext(), "Username taken.",
		// Toast.LENGTH_LONG).show();
		// return false;
		// }
		return true;
	}

	/*
	 * Checks if a username is unique
	 */
	/*
	 * private boolean isUnique(String str) { // TODO - query server for string
	 * uniqueness return true; }
	 */

	public String createUser(List<NameValuePair> nameValuePairs) {

		String url = "http://130.207.195.175:80/espn/createUser.php";
		String serverResponsePhrase;
		int serverStatusCode = -1;
		String bytesSent = "none";
		HttpPost httppost = new HttpPost(url);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;
		// Send POST message with given parameters to the HTTP server.
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			response = httpclient.execute(httppost);
			response.getEntity().getContentType();
			response.getEntity().getContentLength();

			InputStream is = response.getEntity().getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(20);

			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			bytesSent = new String(baf.toByteArray());

			// Response from the server
			serverResponsePhrase = response.getStatusLine().getReasonPhrase();
			serverStatusCode = response.getStatusLine().getStatusCode();

			Log.v("test", "server response: " + serverResponsePhrase);
			Log.v("test", "status code: " + String.valueOf(serverStatusCode));
			Log.v("test", "bytes sent: " + bytesSent);
			return bytesSent;

		} catch (Exception e) {
			Log.e("test", "An error occured while trying to post to the server");
		}
		return bytesSent;
	}

	/*
	 * Save the username and email settings
	 */
	public void save() {
		Log.v("test", "creating new user");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		// Adding parameters to send to the HTTP server for login.
		Log.v("test", context.getDbEmail());
		nameValuePairs.add(new BasicNameValuePair("email", context.getDbEmail()));
		nameValuePairs.add(new BasicNameValuePair("username", username));

		// add email and username to database
		String response = createUser(nameValuePairs);

		if (Integer.valueOf(response) == 1) {
			SharedPreferences.Editor ed = context.getPreferences().edit();
			ed.putString(getString(R.string.settingsEmail), email);
			ed.putString(getString(R.string.settingsUsername), username);
			ed.commit();
		}

		else {
			Toast.makeText(getBaseContext(), "A server error has occurred", Toast.LENGTH_LONG).show();
		}

	}

}
