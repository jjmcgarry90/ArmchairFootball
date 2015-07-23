package org.gtri.espn.activities.general;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.Participant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ParticipantListActivity extends Activity {

	private FanbookApplication context;
	private ParticipantListAdapter adapter;
	private ListView list;
	private AutoCompleteTextView searchBar;
	private ImageButton buttonGo;

	private String favoriteTeam;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_participant_list);
		context = (FanbookApplication) getApplicationContext();

		if (context.getUser().getFavorite() == null) {
			favoriteTeam = "None";
		} else {
			favoriteTeam = context.getUser().getFavorite().getName();
		}

		adapter = new ParticipantListAdapter();
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);

		buttonGo = (ImageButton) findViewById(R.id.buttonGo);
		buttonGo.setOnClickListener(goListener);

		TextView emptyView = (TextView) findViewById(R.id.textViewEmpty);
		list.setEmptyView(emptyView);

		setupAutoComplete();

		findViewById(R.id.mainLayout).requestFocus();
	}

	/*
	 * Sets up the auto complete data set
	 */
	private void setupAutoComplete() {
		// Load team names into auto complete source
		ArrayList<String> autoCompleteSource = new ArrayList<String>();
		for (Participant participant : context.getSchedule().getParticipants()) {
			autoCompleteSource.add(participant.getName());
		}
		ArrayAdapter<String> teamBinder = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteSource);
		searchBar = (AutoCompleteTextView) findViewById(R.id.searchbar);
		searchBar.setThreshold(1);
		searchBar.setAdapter(teamBinder);
	}

	/*
	 * Save all the user's changed settings
	 */
	private void saveSettings() {
		// Save all the user's settings
		SharedPreferences.Editor ed = context.getPreferences().edit();
		ed.putString(getString(R.string.settingsFavorite), favoriteTeam);
		ed.commit();
		if (favoriteTeam.equals("None")) {
			context.getUser().setFavorite(null);
		} else {
			context.getUser().setFavorite(context.getSchedule().getParticipantByName(favoriteTeam));
		}
	}

	/*
	 * Go Listener
	 */
	private OnClickListener goListener = new OnClickListener() {
		public void onClick(View v) {
			// Check for valid user name
			String name = searchBar.getText().toString();
			if (context.getSchedule().hasParticipant(name) == true) {
				// Go to selected friend's profile
				Intent intent = new Intent(getBaseContext(), ParticipantInfoActivity.class);
				intent.putExtra("participant", context.getSchedule().getParticipant(name));
				startActivity(intent);
			} else {
				Toast.makeText(getBaseContext(), "Team does not exist.", Toast.LENGTH_SHORT).show();
			}

		}
	};

	/*
	 * Custom adapter class
	 */
	private class ParticipantListAdapter extends ArrayAdapter<Participant> {

		private ParticipantListAdapter() {
			super(ParticipantListActivity.this, R.layout.list_item_team, context.getSchedule().getParticipants());
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			View row;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_item_team, parent, false);
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
			ImageButton buttonFavorite = (ImageButton) row.findViewById(R.id.buttonFavorite);
			if (context.getUser().getFavorite() == null) {
				buttonFavorite.setImageResource(R.drawable.btn_star_big_off);
				buttonFavorite.setVisibility(ImageButton.VISIBLE);
			} else if (context.getUser().getFavorite().getName().equals(getItem(position).getName())) {
				buttonFavorite.setImageResource(R.drawable.btn_star_big_on_pressed);
				buttonFavorite.setVisibility(ImageButton.VISIBLE);
			} else {
				buttonFavorite.setVisibility(ImageButton.INVISIBLE);
			}
			buttonFavorite.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (context.getUser().getFavorite() == null) {
						// If null favorite, add as favorite
						context.getUser().setFavorite(getItem(position));
						favoriteTeam = getItem(position).getName();
					} else if (context.getUser().getFavorite().getName().equals(getItem(position).getName())) {
						// If already favorite, remove favorite
						context.getUser().setFavorite(null);
						favoriteTeam = "None";
					} else {
						// If not favorite, add as favorite
						context.getUser().setFavorite(getItem(position));
						favoriteTeam = getItem(position).getName();
					}
					context.getUser().removeWatched(getItem(position));
					adapter.notifyDataSetChanged();
				}
			});
				RadioButton buttonWatched = (RadioButton) row.findViewById(R.id.buttonWatched);
				if (context.getUser().checkWatched(getItem(position)) == true) {
					buttonWatched.setChecked(true);
				} else {
					buttonWatched.setChecked(false);
				}
				if (context.getUser().getFavorite() == null) {
					// favorite is null
					buttonWatched.setVisibility(RadioButton.VISIBLE);
				} else if (context.getUser().getFavorite().getName().equals(getItem(position).getName())) {
					// this team is already a favorite
					buttonWatched.setVisibility(RadioButton.INVISIBLE);
				} else {
					// this team is not the favorite
					buttonWatched.setVisibility(RadioButton.VISIBLE);
				}
				buttonWatched.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (context.getUser().checkWatched(getItem(position)) == true) {
							// Remove watched
							context.getUser().removeWatched(getItem(position));
						} else {
							// Add watched
							context.getUser().addWatched(getItem(position));
						}
						adapter.notifyDataSetChanged();
					}
				});

			return row;
		}
	}

	/*
	 * Force save of data
	 */
	protected void onPause() {
		super.onResume();
		saveSettings();
	}

	/*
	 * Force reload of screen data
	 */
	protected void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}
}
