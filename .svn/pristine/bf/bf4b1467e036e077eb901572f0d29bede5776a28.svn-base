package org.gtri.espn.dialogs;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.Participant;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ResetTeamDialog extends Activity {

	private FanbookApplication context;
	private ParticipantListAdapter adapter;
	private ListView list;
	private ImageButton buttonOkay, buttonCancel;

	private ArrayList<Participant> listWatched;
	private Participant favorite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_favorite);
		context = (FanbookApplication) getApplicationContext();

		favorite = null;
		listWatched = new ArrayList<Participant>();

		buttonOkay = (ImageButton) findViewById(R.id.buttonOkay);
		buttonOkay.setOnClickListener(okayListener);

		buttonCancel = (ImageButton) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(cancelListener);

		adapter = new ParticipantListAdapter();
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);

		TextView emptyView = (TextView) findViewById(R.id.textViewEmpty);
		list.setEmptyView(emptyView);
	}

	/*
	 * Okay Listener
	 */
	private OnClickListener okayListener = new OnClickListener() {
		public void onClick(View v) {
			saveSettings();
			finish();
		}
	};

	/*
	 * Cancel Listener
	 */
	private OnClickListener cancelListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};

	/*
	 * Override back button for clean exit
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/*
	 * Save all the user's changed settings
	 */
	private void saveSettings() {
		// Save all the user's settings
		SharedPreferences.Editor ed = context.getPreferences().edit();
		if (favorite == null) {
			ed.putString(getString(R.string.settingsFavorite), "None");
		} else {
			ed.putString(getString(R.string.settingsFavorite), favorite.getName());
		}
		ed.commit();
		context.getUser().setFavorite(favorite);
		context.getUser().setWatched(listWatched);
	}

	/*
	 * Custom adapter class
	 */
	private class ParticipantListAdapter extends ArrayAdapter<Participant> {

		private ParticipantListAdapter() {
			super(ResetTeamDialog.this, R.layout.list_item_team, context.getSchedule().getParticipants());
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
			ImageButton buttonFavorite = (ImageButton) row.findViewById(R.id.buttonFavorite);
			if (favorite == null) {
				buttonFavorite.setImageResource(R.drawable.btn_star_big_off);
				buttonFavorite.setVisibility(ImageButton.VISIBLE);
			} else if (favorite.getName().equals(getItem(position).getName())) {
				buttonFavorite.setImageResource(R.drawable.btn_star_big_on_pressed);
				buttonFavorite.setVisibility(ImageButton.VISIBLE);
			} else {
				buttonFavorite.setVisibility(ImageButton.INVISIBLE);
			}
			buttonFavorite.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (favorite == null) {
						// If null favorite, add as favorite
						favorite = getItem(position);
					} else if (favorite.getName().equals(getItem(position).getName())) {
						// If already favorite, remove favorite
						favorite = null;
					} else {
						// If not favorite, add as favorite
						favorite = getItem(position);
					}
					adapter.notifyDataSetChanged();
				}
			});
			RadioButton buttonWatched = (RadioButton) row.findViewById(R.id.buttonWatched);
			if (checkWatched(getItem(position)) == true) {
				buttonWatched.setChecked(true);
			} else {
				buttonWatched.setChecked(false);
			}
			buttonWatched.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (checkWatched(getItem(position)) == true) {
						// Remove watched
						removeWatched(getItem(position));
					} else {
						// Add watched
						addWatched(getItem(position));
					}
					adapter.notifyDataSetChanged();
				}
			});
			return row;
		}
	}
	
	public void addWatched(Participant newWatched) {
		if (checkWatched(newWatched) == false) {
			listWatched.add(newWatched);
		}
	}

	public boolean checkWatched(Participant newWatched) {
		for (Participant watched : listWatched) {
			if (watched.getName().equals(newWatched.getName())) {
				return true;
			}
		}
		return false;
	}

	public void removeWatched(Participant participant) {
		listWatched.remove(participant);
	}

	/*
	 * Force reload of screen data
	 */
	protected void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}
}
