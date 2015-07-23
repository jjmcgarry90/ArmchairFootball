package org.gtri.espn.activities.general;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.espn.dialogs.CreateGroupDialog;
import org.gtri.objects.Group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class GroupListActivity extends Activity {

	private FanbookApplication context;
	private ImageButton buttonCreateGroup, buttonGo;

	private GroupListAdapter adapter;
	private ListView list;
	private AutoCompleteTextView searchBar;

	private static final int CUSTOM_DIALOG = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_group_list);
		context = (FanbookApplication) getApplicationContext();

		buttonCreateGroup = (ImageButton) findViewById(R.id.buttonCreateGroup);
		buttonCreateGroup.setOnClickListener(createGroupListener);

		buttonGo = (ImageButton) findViewById(R.id.buttonGo);
		buttonGo.setOnClickListener(goListener);

		adapter = new GroupListAdapter();
		list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);

		TextView emptyView = (TextView) findViewById(R.id.textViewEmpty);
		list.setEmptyView(emptyView);

		setupAutoComplete();
		findViewById(R.id.mainLayout).requestFocus();
	}

	/*
	 * Sets up the auto complete data set
	 */
	private void setupAutoComplete() {
		// Load group names into auto complete source
		ArrayList<String> autoCompleteSource = new ArrayList<String>();
		for (Group group : context.getUser().getGroups()) {
			autoCompleteSource.add(group.getName());
		}
		ArrayAdapter<String> groupBinder = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteSource);
		searchBar = (AutoCompleteTextView) findViewById(R.id.searchbar);
		searchBar.setThreshold(1);
		searchBar.setAdapter(groupBinder);
	}
	
	/*
	 * Data set changed
	 */
	private void updateData() {
		adapter.notifyDataSetChanged();
		setupAutoComplete();
	}

	/*
	 * Custom adapter class
	 */
	private class GroupListAdapter extends ArrayAdapter<Group> {

		private GroupListAdapter() {
			super(GroupListActivity.this, R.layout.list_item_generic, context.getUser().getGroups());
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
					Intent intent = new Intent(getBaseContext(), GroupInfoActivity.class);
					intent.putExtra("group", getItem(position));
					startActivity(intent);
				}
			});
			ImageButton btn = (ImageButton) row.findViewById(R.id.button);
			btn.setImageResource(R.drawable.ic_menu_block);
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					context.getUser().removeGroup(getItem(position));
					updateData();
				}
			});
			return row;
		}
	}

	/*
	 * Create group listener
	 */
	private OnClickListener createGroupListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), CreateGroupDialog.class);
			startActivityForResult(intent, CUSTOM_DIALOG);
		}
	};

	/*
	 * Go Listener
	 */
	private OnClickListener goListener = new OnClickListener() {
		public void onClick(View v) {
			// Check for valid user name
			String name = searchBar.getText().toString();
			if (context.getUser().hasGroup(name) == true) {
				// Go to selected group's profile
				Intent intent = new Intent(getBaseContext(), GroupInfoActivity.class);
				intent.putExtra("group", context.getUser().getGroupByName(name));
				startActivity(intent);
			} else {
				Toast.makeText(getBaseContext(), "Group does not exist.", Toast.LENGTH_SHORT).show();
			}

		}
	};

	/*
	 * On later activity results
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CUSTOM_DIALOG) {
			if (resultCode == Activity.RESULT_OK) {
				Group group = new Group(data.getStringExtra(CreateGroupDialog.RESULT_VALUE));
				// group.addMember(context.getUser());
				context.getUser().addGroup(group);
				updateData();
			}
		}
	}

	/*
	 * Force reload of screen data
	 */
	protected void onResume() {
		super.onResume();
		updateData();
	}
	
}
