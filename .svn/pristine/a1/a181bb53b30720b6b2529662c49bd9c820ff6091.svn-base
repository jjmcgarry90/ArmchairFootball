package org.gtri.espn.activities.general;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.Group;
import org.gtri.objects.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GroupInfoActivity extends Activity {

	private FanbookApplication context;
	private Group group;
	private TextView textViewTitle;
	private GroupMemberListAdapter adapter;
	private ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_group_info);
		context = (FanbookApplication) getApplicationContext();
		group = (Group) getIntent().getParcelableExtra("group");
		group = context.getUser().getGroupByName(group.getName());

		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		textViewTitle.setText(group.getName());

		adapter = new GroupMemberListAdapter();
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);

		TextView emptyView = (TextView) findViewById(R.id.textViewEmpty);
		list.setEmptyView(emptyView);
	}

	/*
	 * Custom adapter class
	 */
	private class GroupMemberListAdapter extends ArrayAdapter<User> {

		private GroupMemberListAdapter() {
			super(GroupInfoActivity.this, R.layout.list_item_generic_no_button, group.getMembers());
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			View row;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.list_item_generic_no_button, parent, false);
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
