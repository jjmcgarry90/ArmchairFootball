package org.gtri.espn.dialogs;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.Participant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FavoriteTeamDialog extends Activity {

	private FanbookApplication context;
	private ParticipantListAdapter adapter;
	private ListView list;
	private Button buttonOkay, buttonCancel, buttonSort;
	private ToggleButton viewTeams, viewGames;
	private boolean onlyTeams;

	private ArrayList<Participant> listWatched;
	private Participant favorite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_favorite);
		context = (FanbookApplication) getApplicationContext();
		onlyTeams = getIntent().getBooleanExtra("onlyTeams", false);
		
		if (context.getUser().getFavorite() == null) {
			favorite = null;
		} else {
			favorite = context.getUser().getFavorite();
		}
		listWatched = new ArrayList<Participant>();
		listWatched.addAll(context.getUser().getWatched());

		buttonOkay = (Button) findViewById(R.id.buttonOkay);
		buttonOkay.setOnClickListener(okayListener);

		//buttonCancel = (ImageButton) findViewById(R.id.buttonCancel);
		//buttonCancel.setOnClickListener(cancelListener);
		buttonSort = (Button) findViewById(R.id.buttonSort);
		buttonSort.setOnClickListener(sortListener);

		viewTeams = (ToggleButton) findViewById(R.id.toggleTeams);
		viewTeams.setOnClickListener(viewTeamsListener);
		//viewTeams.setOnCheckedChangeListener(viewTeamsListener);
		viewGames = (ToggleButton) findViewById(R.id.toggleGames);
		viewGames.setOnClickListener(viewGamesListener);
		//viewGames.setOnCheckedChangeListener(viewGamesListener);
		if (onlyTeams) {
			viewGames.setVisibility(View.INVISIBLE);
			viewTeams.setClickable(false);
		}
		viewTeams.setChecked(true);
		viewGames.setChecked(false);
		
		if (onlyTeams) {
			viewTeams.setClickable(false);
		}
		
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
			if (favorite != null && listWatched !=null)
			{
				saveSettings();
				finish();
			}
			else
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(FavoriteTeamDialog.this);
				builder.setMessage("You must select a favorite team and at least one team to watch").setCancelable(true).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				AlertDialog diag = builder.create();
				diag.show();
			}
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
	
	private OnClickListener sortListener = new OnClickListener() {
		public void onClick(View v) {
			String[] items = {"Alphabetical", "Conference", "Most Friends Watching"};
			AlertDialog.Builder builder = new AlertDialog.Builder(FavoriteTeamDialog.this);
			builder.setTitle("Select Sorting Criteria");
			builder.setItems(items, sortSelection);
			AlertDialog diag = builder.create();
			diag.show();
		}
	};

	private OnClickListener viewGamesListener = new OnClickListener() {
		public void onClick(View v) {
			viewTeams.setChecked(false);
			viewGames.setChecked(true);
		}
	};
	
	private OnClickListener viewTeamsListener = new OnClickListener() {
		public void onClick(View v) {
			viewGames.setChecked(false);
			viewTeams.setChecked(true);
		}
	};
	
	private DialogInterface.OnClickListener sortSelection = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int selectedItem) {
			switch (selectedItem) {
				case 0:
					//alphabetical
					break;
				case 1:
					//sort by conf
					break;
				case 2:
					//sort by subscribed friends
					break;
			}
		}
	};
	
	/*
	 * Override back button for clean exit
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new AlertDialog.Builder(FavoriteTeamDialog.this);
			builder.setMessage("Please select a favorite team and click done").setCancelable(true).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			AlertDialog diag = builder.create();
			diag.show();
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
		context.getUser().setFavorite(favorite);
		context.getUser().setWatched(listWatched);
		context.updatePreferences();
	}

	/*
	 * Custom adapter class
	 */
	private class ParticipantListAdapter extends ArrayAdapter<Participant> {

		private ParticipantListAdapter() {
			super(FavoriteTeamDialog.this, R.layout.list_item_team, context.getSchedule().getParticipants());
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
					removeWatched(getItem(position));
					adapter.notifyDataSetChanged();
				}
			});
			RadioButton buttonWatched = (RadioButton) row.findViewById(R.id.buttonWatched);
			if (checkWatched(getItem(position)) == true) {
				buttonWatched.setChecked(true);
			} else {
				buttonWatched.setChecked(false);
			}
			if (favorite == null) {
				// favorite is null
				buttonWatched.setVisibility(RadioButton.VISIBLE);
				buttonWatched.setClickable(true);
			} else if (favorite.getName().equals(getItem(position).getName())) {
				// this team is already a favorite
				buttonWatched.setChecked(true);
				buttonWatched.setClickable(false);
				//buttonWatched.setVisibility(RadioButton.INVISIBLE);
			} else {
				// this team is not the favorite
				buttonWatched.setClickable(true);
				buttonWatched.setVisibility(RadioButton.VISIBLE);
			}
			buttonWatched.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (checkWatched(getItem(position)) == true && favorite != getItem(position)) {
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
