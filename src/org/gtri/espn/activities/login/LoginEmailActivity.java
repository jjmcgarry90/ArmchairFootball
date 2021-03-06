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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * The user must enter a unique email to proceed
 */
public class LoginEmailActivity extends Activity {

	private FanbookApplication context;
	public static String email = null;
	private static final int CHOICE_CONNECT = 0;
	private Button buttonCancel, buttonConnect;
	private EditText editTextEmail;
	private ProgressDialog spinner;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_email);
		context = (FanbookApplication) getApplicationContext();
		
		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(cancelListener);

		buttonConnect = (Button) findViewById(R.id.buttonConnect);
		buttonConnect.setOnClickListener(connectListener);

		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		
	}
	
	/*
	 * Cancel
	 */
	private OnClickListener cancelListener = new OnClickListener() {
		public void onClick(View v) {
			// Force to go back to login choice screen
			setResult(RESULT_CANCELED);
			finish();
		}
	};

	/*
	 * Connect
	 */
	private OnClickListener connectListener = new OnClickListener() {
		public void onClick(View v) {
			email = editTextEmail.getText().toString();
			
			// Loading screen
			spinner = new ProgressDialog(v.getContext());
	        spinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        spinner.setMessage("Connecting...");
	        spinner.show();
	        
			if (isValidEmail(email) == true) {
				
//				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);  
//				// Adding parameters to send to the HTTP server for login.
//				nameValuePairs.add(new BasicNameValuePair("email", email));
//				
//				String response = "";
//				response = userLogin(nameValuePairs);
//				Log.v("test", response);
//				JSONObject json = context.createJSON(response);
//				
//				//http post returned null meaning email does not exist
//				if(json==null){
//					//no email in db so we will create an account with specified email
//					Log.v("test", "New email address");
//					context.setDbEmail(email);
//					context.setExistingEmail(false);
//				}
//				else {
//					//set the email/username to values in db 
//					try {
//						Log.v("test", "Existing email address");
//						context.setDbEmail(json.getString("email"));
//						context.setDbUsername(json.getString("username"));
//						context.setExistingEmail(true);
//					} 
//					catch (JSONException e) {
//						Log.v("test", "Could not retrieve fields from JSON");
//						e.printStackTrace();
//					}
//				}
				spinner.dismiss();
				Intent intent = new Intent(getBaseContext(), LoginUsernameActivity.class);
				startActivityForResult(intent, CHOICE_CONNECT);
			}
		}
	};
	
	/*
	 * Checks validity of the email address
	 */
	private boolean isValidEmail(String str) {
		// TODO - check string for validity (sql injections, formatting, etc.)
		if (str == null || "".equals(str) || !str.contains("@")) {
			Toast.makeText(getBaseContext(), "Invalid email address.", Toast.LENGTH_LONG).show();
			return false;
		}
		// TODO - check database for uniqueness
		if (!isUnique(str)) {
			Toast.makeText(getBaseContext(), "Email already in use.", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
	
	/*
	 * Checks if an email is unique
	 */
	private boolean isUnique(String str) {
		// TODO - query server for string uniqueness
		return true;
	}
	
	public String userLogin(List<NameValuePair> nameValuePairs) {

    	String url = "http://130.207.195.175:80/espn/login.php";
    	String serverResponsePhrase;
    	int serverStatusCode=-1;
    	String bytesSent="none";
    	HttpPost httppost = new HttpPost(url);
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	HttpResponse response = null;
    	// Send POST message  with given parameters to the HTTP server.
    	try {                    
    		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  

    		response = httpclient.execute(httppost);
    		response.getEntity().getContentType();
    		response.getEntity().getContentLength();
    		
    		
    		InputStream is = response.getEntity().getContent();
    		BufferedInputStream bis = new BufferedInputStream(is);
    		ByteArrayBuffer baf = new ByteArrayBuffer(20);

    		int current = 0;  
    		while((current = bis.read()) != -1) {  
    			baf.append((byte)current);  
    		}  

    		bytesSent = new String(baf.toByteArray());

    		// Response from the server          
    		serverResponsePhrase = response.getStatusLine().getReasonPhrase();
    		serverStatusCode = response.getStatusLine().getStatusCode();

    		Log.v("test", "server response: "+serverResponsePhrase);
    		Log.v("test", "status code: "+String.valueOf(serverStatusCode));
    		Log.v("test", "bytes sent: "+bytesSent);
    		return bytesSent;

    	}
    	catch (Exception e) {
    		Log.e("test", "An error occured while trying to post to the server");			
    	}
    	return bytesSent;
    }
	
	/*
	 * On later activity results
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == CHOICE_CONNECT) {
        	if (resultCode == RESULT_OK) {
        		// Finish and send results
    			setResult(RESULT_OK);
    			finish();
            }
        }
    }

}
