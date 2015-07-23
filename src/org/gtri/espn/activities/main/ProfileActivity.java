package org.gtri.espn.activities.main;

import java.io.IOException;
import java.net.MalformedURLException;

import org.gtri.espn.R;
import org.gtri.espn.activities.general.EventListActivity;
import org.gtri.espn.activities.general.FriendListActivity;
import org.gtri.espn.activities.general.HuddleActivity;
import org.gtri.espn.activities.general.ParticipantInfoActivity;
import org.gtri.espn.activities.login.LoginScreenActivity;
import org.gtri.helper.FacebookHelper;
import org.gtri.objects.Participant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProfileActivity extends Activity implements OnClickListener {

	private FanbookApplication context;
	private TextView textViewTitle, textViewTeamName;
	private ProfileListAdapter adapter;
	private ListView list;
	private ImageView picture;
	private Button friends, logout, huddles, teams, info, profile, settings, newsfeed;
	private boolean friendsVisible = false, infoVisible=false,
					huddlesVisible=false, teamsVisible=false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_profile);
		context = (FanbookApplication) getApplicationContext();

		textViewTitle = (TextView) findViewById(R.id.profileName);
		textViewTitle.setText(context.getUser().getName() + "'s Profile");

		
		//context.getUser().getFavorite().getFullName()
		
		picture = (ImageView) findViewById(R.id.profileImage);
		//context.getUser().
		picture.setImageBitmap(context.getUser().getProfilePic());
		
		friends = (Button) findViewById(R.id.viewFriendsButton);
		friends.setOnClickListener(onFriendsClick);
		
		logout = (Button) findViewById(R.id.logOutButton);
		logout.setOnClickListener(onLogOut);
		
		huddles = (Button) findViewById(R.id.viewHuddlesButton);
		huddles.setOnClickListener(onHuddlesClick);
		settings = (Button) findViewById(R.id.settingsButton);
		settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), SettingsActivity2.class);
				startActivity(intent);
			}
		});
		profile = (Button) findViewById(R.id.profileButton);
		profile.setEnabled(false);
		newsfeed = (Button) findViewById(R.id.newsfeedButton);
		newsfeed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private OnClickListener onFriendsClick = new OnClickListener() {
		public void onClick(View v) {
			ViewGroup root = (RelativeLayout)findViewById(R.id.friendListHost);
			if (friendsVisible==false) {
	        	getLayoutInflater().inflate(R.layout.activity_general_friend_list, root, true);
				friendsVisible=true;
			}
			
			else {
				root.removeAllViews();
				friendsVisible=false;
			}
 
		}
	};
	
	private OnClickListener onHuddlesClick = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(ProfileActivity.this, HuddleActivity.class);
			startActivity(intent);
			//finish();
		}
	};
	
	private OnClickListener onLogOut = new OnClickListener() {
		public void onClick(View v) {
			try {
				context.getFacebook().logout(context);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent intent = new Intent(getBaseContext(), LoginScreenActivity.class);
			startActivity(intent);
			finish();
		}
	};
	
	/*
	 * Custom adapter class
	 */
	private class ProfileListAdapter extends ArrayAdapter<Participant> {

		private ProfileListAdapter() {
			super(ProfileActivity.this, R.layout.list_item_generic, context.getUser().getWatched());
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
					Intent intent = new Intent(getBaseContext(), ParticipantInfoActivity.class);
					intent.putExtra("participant", getItem(position));
					startActivity(intent);
				}
			});
			ImageButton btn = (ImageButton) row.findViewById(R.id.button);
			btn.setImageResource(R.drawable.ic_menu_block);
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					context.getUser().removeWatched(getItem(position));
					adapter.notifyDataSetChanged();
				}
			});
			return row;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}



}
