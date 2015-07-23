package org.gtri.espn.activities.general;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.Event;
import org.gtri.objects.Huddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class EventListActivity extends Activity {

	private FanbookApplication context;
	private EventListAdapter adapter;
	private ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_event_list);
		context = (FanbookApplication) getApplicationContext();

		adapter = new EventListAdapter();
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);

		TextView emptyView = (TextView) findViewById(R.id.textViewEmpty);
		list.setEmptyView(emptyView);
	}

	/*
	 * Custom adapter class
	 */
	private class EventListAdapter extends ArrayAdapter<Event> {

		private EventListAdapter() {
			super(EventListActivity.this, R.layout.list_item_generic, context.getSchedule().getEvents());
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
					Intent intent = new Intent(getBaseContext(), EventInfoActivity.class);
					intent.putExtra("event", getItem(position));
					startActivity(intent);
				}
			});
			ImageButton btn = (ImageButton) row.findViewById(R.id.button);
			btn.setImageResource(R.drawable.ic_menu_btn_add);
			if (context.getUser().getHuddleByEvent(getItem(position)) == false) {
				btn.setVisibility(Button.VISIBLE);
			} else {
				btn.setVisibility(Button.INVISIBLE);
			}
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					context.getUser().addHuddle(new Huddle(getItem(position), context.getUser()));
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

}
