package org.gtri.espn.activities.general;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.helper.FacebookHelper;
import org.gtri.objects.User;

import com.facebook.android.DialogError;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FriendListActivity extends Activity {

	private FanbookApplication context;
	private FriendListAdapter adapter;
	private ListView list;
	private ImageButton addFriend, buttonGo;
	private AutoCompleteTextView searchBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_friend_list);
		context = (FanbookApplication) getApplicationContext();

		adapter = new FriendListAdapter();
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);

		TextView emptyView = (TextView) findViewById(R.id.textViewEmpty);
		list.setEmptyView(emptyView);

		buttonGo = (ImageButton) findViewById(R.id.buttonGo);
		buttonGo.setOnClickListener(goListener);

		addFriend = (ImageButton) findViewById(R.id.addFriend);
		addFriend.setOnClickListener(addFriendListener);

		setupAutoComplete();
		if (context.getUser().getFriendsList().size() == 0)
		{
			FacebookHelper helper = new FacebookHelper(context);
			ArrayList<User> friends = helper.getFriends();
			context.getUser().addFriend(friends);
		}

		findViewById(R.id.mainLayout).requestFocus();
	}

	/*
	 * Sets up the auto complete data set
	 */
	private void setupAutoComplete() {
		// Load friend names into auto complete source
		ArrayList<String> autoCompleteSource = new ArrayList<String>();
		for (User friend : context.getUser().getFriendsList()) {
			autoCompleteSource.add(friend.getName());
		}
		ArrayAdapter<String> friendBinder = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteSource);
		searchBar = (AutoCompleteTextView) findViewById(R.id.searchbar);
		searchBar.setThreshold(1);
		searchBar.setAdapter(friendBinder);
	}

	/*
	 * Add Friend
	 */
	private OnClickListener addFriendListener = new OnClickListener() {
		public void onClick(View v) {
			Log.d("add friend clicked", String.valueOf(v.getId()));
			//Intent intent = new Intent(getBaseContext(), FBAppRequestActivity.class);
			//startActivity(intent);
			Bundle params = new Bundle();
			params.putString("message", "JOIN ME ON ARMCHAIR QUARTERBACK");
			context.getFacebook().dialog(FriendListActivity.this, "apprequests", params, new AppRequestsListener());
			//OPEN A NEW FRIEND LIST TO SEND REQUESTS
//			// Only works for email
//			if (context.isEmailSessionValid()) {
//				// prompt the user for an email address
//				final EditText input = new EditText(getBaseContext());
//				new AlertDialog.Builder(getBaseContext()).setTitle("Add a Friend").setMessage("Enter your friend's email").setView(input)
//						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int whichButton) {
//								String friendEmail = input.getText().toString();
//								String message = DatabaseHandler.addFriend(context.getUser().getEmail(), friendEmail);
//								// TODO error handling if message.equals();
//							}
//						}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int whichButton) {
//								// Do nothing.
//							}
//						}).show();
//			}
		}
	};

	/*
	 * Go Listener
	 */
	private OnClickListener goListener = new OnClickListener() {
		public void onClick(View v) {
			// Check for valid user name
			String name = searchBar.getText().toString();
			if (context.getUser().hasFriend(name) == true) {
				// Go to selected friend's profile
				Intent intent = new Intent(getBaseContext(), FriendInfoActivity.class);
				intent.putExtra("friend", context.getUser().getFriend(name));
				startActivity(intent);
			} else {
				Toast.makeText(getBaseContext(), "Friend does not exist.", Toast.LENGTH_SHORT).show();
			}

		}
	};

	/*
	 * Custom adapter class
	 */
	private class FriendListAdapter extends ArrayAdapter<User> {

		private FriendListAdapter() {
			super(FriendListActivity.this, R.layout.list_item_generic, context.getUser().getFriendsList());
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			View row;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_item_generic, parent, false);
			} else {
				row = convertView;
			}
			TextView tv = (TextView) row.findViewById(R.id.text);
			tv.setText(getItem(position).getName());
			tv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getBaseContext(), FriendInfoActivity.class);
					intent.putExtra("friend", getItem(position));
					startActivity(intent);
				}
			});
			ImageButton btn = (ImageButton) row.findViewById(R.id.button);
			btn.setImageResource(R.drawable.ic_menu_blocked_user);
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					context.getUser().removeFriend(getItem(position));
					adapter.notifyDataSetChanged();
				}
			});
			return row;
		}
	}

	/*
	 * Force reload of screen data
	 */
	protected void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
    /*
     * callback for the apprequests dialog which sends an app request to user's
     * friends.
     */
    public class AppRequestsListener implements DialogListener {
        @Override
        public void onComplete(Bundle values) {
        	final int errorCode = values.getInt("error_code");
        	if (!values.isEmpty() && errorCode == 0)
        	{
        		Toast toast = Toast.makeText(getApplicationContext(), "App request sent",
        			Toast.LENGTH_SHORT);
        		toast.show();
        	}
        	else {
        		Toast toast = Toast.makeText(getApplicationContext(), "App request canceled",
            			Toast.LENGTH_SHORT);
            		toast.show();
        	}
        		
        }

        @Override
        public void onFacebookError(FacebookError error) {
            Toast.makeText(getApplicationContext(), "Facebook Error: " + error.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast toast = Toast.makeText(getApplicationContext(), "App request cancelled",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        @Override
        public void onError(DialogError e) {
            e.printStackTrace();
        }
    }

}
