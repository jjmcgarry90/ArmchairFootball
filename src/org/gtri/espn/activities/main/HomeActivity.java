package org.gtri.espn.activities.main;

import java.util.ArrayList;
import java.util.TimerTask;



import org.gtri.espn.R;
import org.gtri.espn.activities.general.GameInfoActivity;
import org.gtri.espn.activities.general.OpinionActivity;
import org.gtri.espn.activities.general.PlayInfoActivity;
import org.gtri.espn.dialogs.FavoriteTeamDialog;
import org.gtri.espn.dialogs.MenuDialog;
import org.gtri.objects.Event;
import org.gtri.objects.Huddle;
import org.gtri.objects.Participant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * The hub of interactivity for the app
 */
public class HomeActivity extends Activity {

	// Constants for inter-activity actiI caons
	public static final int MENU_DIALOG = 1;
	public static final int RESULT_LOGOUT = 1;

	private FanbookApplication context;
	private ImageButton gameInfo;
	private TextView textViewInfo;
	private Spinner spinner;
	private ArrayList<String> communities;
	private String communityName;
	private Object communityObject;
	private NewsFeedAdapter adapter;
	private ListView list;
	private ImageButton general,referee,coach,player,predict;
	private Button settings, profile, newsfeed;
	ArrayList<Event> alist = new ArrayList<Event>(); 
	//private Timer notifyTimer;
	
	private class TimerNotificationTask extends TimerTask {
		private Context context;
		private String notifyMessage;
		
		public TimerNotificationTask(Context pContext, String notificationText) {
			this.context = pContext;
			this.notifyMessage = notificationText;
		}
		
		@Override
		public void run() {
			updateUI.sendEmptyMessage(0);
		}
		
		//Handler for communicating with the UI thread
		private Handler updateUI = new Handler() {
			@Override
			public void dispatchMessage(Message msg) {
				super.dispatchMessage(msg);
				try {
				Toast.makeText(context, notifyMessage , Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	private TimerNotificationTask predictionNotification = new TimerNotificationTask(this, "Your prediction for play 1 of game 3 was correct!");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		setContentView(R.layout.newsfeed);

		context = (FanbookApplication) getApplicationContext();
		
		gameInfo = (ImageButton) findViewById(R.id.gameInfo);
		gameInfo.setOnClickListener(gameInfoListener);
		
		general = (ImageButton) findViewById(R.id.generalOpinion);
		general.setOnClickListener(opinionListener);
		referee = (ImageButton) findViewById(R.id.refereeOpinion);
		referee.setOnClickListener(opinionListener);
		coach = (ImageButton) findViewById(R.id.coachOpinion);
		coach.setOnClickListener(opinionListener);
		player = (ImageButton) findViewById(R.id.playerOpinion);
		player.setOnClickListener(opinionListener);
		predict = (ImageButton) findViewById(R.id.predictPlay);
		predict.setOnClickListener(predictListener);
		profile = (Button) findViewById(R.id.profileButton);
		profile.setOnClickListener(viewProfileListener);
		
		settings = (Button)findViewById(R.id.settingsButton);
		settings.setOnClickListener(settingsListener);
		newsfeed = (Button)findViewById(R.id.newsfeedButton);
		newsfeed.setEnabled(false);
		// string that keeps identity of currently selected spinner object
		
		
		// setup spinner of communities
		spinner = (Spinner) findViewById(R.id.spinnerCommunity);
		spinner.setOnItemSelectedListener(new SpinnerListener());
		
		for(int i=0; i<5; i++) {
			alist.add(new Event());
		}
		
		
		adapter = new NewsFeedAdapter();
		
		list = (ListView) findViewById(R.id.newsfeed_event_list);
		list.setAdapter(adapter);
		
		//checkFavorite();
		//TODO: remove this. It schedules a correct prediction notification to pop up every minute
		//context.getNotificationTimer().schedule(predictionNotification, 30000, 60000);
		//context.enableNotificationTask(predictionNotification);
	//	Intent intent = new Intent(getBaseContext(), Test.class);
	//	startActivity(intent);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		try
		{
			if (checkFavorite())
			{
				setupCommunities();
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, toStringArray(communities));
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner.setAdapter(adapter);
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * if first login or if favorite team is null, ask for favorite team
	 */
	private boolean checkFavorite() {
		String name = context.getPreferences().getString(getString(R.string.settingsFavorite), null);
		Log.v("test", context.getUser().getName());
		context.getUser().setFavorite(context.getSchedule().getParticipantByName(name));
		if (context.getUser().getFavorite() == null) {
			Intent intent = new Intent(getBaseContext(), FavoriteTeamDialog.class);
			intent.putExtra("onlyTeams", true);
			startActivity(intent);
			return false;
		}
		return true;
	}

	/*
	 * Setup the communities list
	 */
	private void setupCommunities() {
		communities = new ArrayList<String>();
		if (context.getUser().getFavorite() != null) {
			communities.add(context.getUser().getFavorite().getName());
		}
		for (Participant team : context.getUser().getWatched()) {
			communities.add(team.getName());
		}
		for (Huddle huddle : context.getUser().getHuddles()) {
			communities.add(huddle.getName());
		}
	}

	/*
	 * Convert array list to string array
	 */
	private String[] toStringArray(ArrayList<String> list) {
		String[] temp = new String[list.size()];
		int counter = 0;
		for (String item : list) {
			temp[counter] = item;
			counter++;
		}
		return temp;
	}

	/*
	 * Menu listener
	 */
	private OnClickListener menuListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), MenuDialog.class);
			startActivityForResult(intent, MENU_DIALOG);
		}
	};
	
	private OnClickListener settingsListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), SettingsActivity2.class);
			startActivityForResult(intent, MENU_DIALOG);
		}
	};
	
	private OnClickListener gameInfoListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), GameInfoActivity.class);
			startActivity(intent);
		}
	};
		
	private OnClickListener viewProfileListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener predictListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), PredictActivity.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener opinionListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), OpinionActivity.class);
			int id = v.getId();
			if (id == R.id.generalOpinion)
				intent.putExtra("source", "general");
			else if (id == R.id.refereeOpinion)
				intent.putExtra("source", "referee");
			else if (id == R.id.coachOpinion)
				intent.putExtra("source", "coach");
			else 
				intent.putExtra("source", "player");
			startActivity(intent);
		}
	};

	/*
	 * Add listener
	 */
	private OnClickListener addListener = new OnClickListener() {
		public void onClick(View v) {
			
			// TODO - REMOVE--------------------- fake data for testing
			ArrayList<Participant> participants = new ArrayList<Participant>();
			participants.add(context.getSchedule().getParticipants().get(12));
			participants.add(context.getSchedule().getParticipants().get(28));
			Huddle huddle = new Huddle(new Event(participants), context.getUser());
			context.getUser().addHuddle(huddle);
			Object temp = communityObject;
			communityObject = huddle;
			// TODO - remove
			
			if (communityObject == null) {
				// null
				Toast.makeText(getBaseContext(), "No selected community.", Toast.LENGTH_SHORT).show();
			} else if (communityObject instanceof Participant) {
				// Team
				Toast.makeText(getBaseContext(), "Cannot predict play for a team.", Toast.LENGTH_SHORT).show();
			} else if (communityObject instanceof Huddle) {
				// Huddle
				Intent intent = new Intent(getBaseContext(), PlaySelectionActivity.class);
				intent.putExtra("huddle", (Huddle)communityObject);
				startActivity(intent);
			}
			
			// TODO - remove
			communityObject = temp;
			// TODO - remove
			
			
		}
	};

	/*
	 * Override back button and menu button
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (context.getPreferences().getBoolean(getString(R.string.confirmQuit), true) && keyCode == KeyEvent.KEYCODE_BACK) {
			// Ask the user if they want to quit
			new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(R.string.dialog_quit).setMessage(R.string.dialog_confirm)
					.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Stop the activity
							finish();
						}
					}).setNegativeButton(R.string.dialog_negative, null).show();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			Intent intent = new Intent(getBaseContext(), MenuDialog.class);
			startActivityForResult(intent, MENU_DIALOG);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/*
	 * On later activity results
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MENU_DIALOG) {
			if (resultCode == RESULT_LOGOUT) {
				// Finish activity on logout
				finish();
			}
		}
	}

	/*
	 * Reload the data displayed when the selected community changes
	 */
	private void reloadInformation() {
		// if new selected object, then load new data
		if (spinner.getSelectedItem() != null) {
			communityName = spinner.getSelectedItem().toString();
		} else {
			communityName = null;
		}
		if (communityName == null) {
			// handle null case
			textViewInfo.setText(R.string.none_literal);
			removeInformationCommunity();
		} else {
			communityObject = context.getUser().checkCommunities(communityName);
			if (communityObject == null) {
				// object not found among communities
				textViewInfo.setText(R.string.none_literal);
				removeInformationCommunity();
			} else if (communityObject instanceof Participant) {
				// community is a participant
				loadInformationParticipant();
			} else if (communityObject instanceof Huddle) {
				// community is a huddle
				loadInformationHuddle();
			} else {
				// object not an accepted type
				textViewInfo.setText(R.string.none_literal);
				removeInformationCommunity();
			}
			
		}
	}
	
	/*
	 * Erase current community data
	 */
	private void removeInformationCommunity() {
		communityName = null;
		communityObject = null;
	}

	/*
	 * Load data about the chosen team
	 */
	private void loadInformationParticipant() {
		// TODO - provide useful information
		Participant team = (Participant)communityObject;
	}

	/*
	 * Load data about the chosen huddle
	 */
	private void loadInformationHuddle() {
		// TODO - provide useful information
		Huddle huddle = (Huddle)communityObject;
		textViewInfo.setText(huddle.getName());
	}

	/*
	 * Private spinner listener class
	 */
	private class SpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			communityName = parent.getItemAtPosition(pos).toString();
			reloadInformation();
		}

		public void onNothingSelected(AdapterView<?> parent) {
			// Another interface callback
		}
	}
	
	private class NewsFeedAdapter extends ArrayAdapter<Event> {
		
		
		private NewsFeedAdapter() {
			super(HomeActivity.this, R.layout.newsfeed_list_item, alist);
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			View row;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.newsfeed_list_item, parent, false);
			} else {
				row = convertView;
			}
			
			TextView numComments = (TextView)row.findViewById(R.id.number_comments);
			int rand = (int)(Math.random() * 90 + 10);
			numComments.setText(String.valueOf(rand));
			
			row.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getBaseContext(), PlayInfoActivity.class);
					startActivity(intent);
				}
			});
			return row;
		}
	}

}
