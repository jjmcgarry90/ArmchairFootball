package org.gtri.espn.activities.main;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.helper.ExpandListChild;
import org.gtri.helper.ExpandListGroup;
import org.gtri.helper.ExpandableListAdapter;
import org.gtri.objects.Huddle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class PlaySelectionActivity extends Activity {

	private FanbookApplication context;
	private Huddle huddle;
	private TextView textViewTitle, textViewInfo;
	private ExpandableListAdapter expandableListAdapter;
	private ArrayList<ExpandListGroup> expandableListItems;
	private ExpandableListView expandableListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_selection);
		context = (FanbookApplication) getApplicationContext();
		huddle = (Huddle) getIntent().getParcelableExtra("huddle");
		huddle = context.getUser().getHuddleByName(huddle.getName());

		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		textViewTitle.setText(huddle.getEvent().getAbbreviation());

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		textViewInfo.setText(huddle.getEvent().getFormattedName());

		expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
		expandableListItems = SetStandardGroups();
		expandableListAdapter = new ExpandableListAdapter(PlaySelectionActivity.this, expandableListItems);
		expandableListView.setAdapter(expandableListAdapter);
	}

	/*
	 * Create the options in the expandable list view
	 */
	public ArrayList<ExpandListGroup> SetStandardGroups() {
		ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();

		// Create General play list
		ArrayList<ExpandListChild> list1 = new ArrayList<ExpandListChild>();
		ExpandListGroup gru1 = new ExpandListGroup("General");
		ExpandListChild ch1_1 = new ExpandListChild("Touchdown");
		list1.add(ch1_1);
		ExpandListChild ch1_2 = new ExpandListChild("First Down");
		list1.add(ch1_2);
		ExpandListChild ch1_3 = new ExpandListChild("Field Goal");
		list1.add(ch1_3);
		ExpandListChild ch1_4 = new ExpandListChild("Turnover");
		list1.add(ch1_4);
		gru1.setItems(list1);

		// Create Offense play list
		ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();
		ExpandListGroup gru2 = new ExpandListGroup("Offense");
		ExpandListChild ch2_1 = new ExpandListChild("Run");
		list2.add(ch2_1);
		ExpandListChild ch2_2 = new ExpandListChild("Pass");
		list2.add(ch2_2);
		ExpandListChild ch2_3 = new ExpandListChild("Kick");
		list2.add(ch2_3);
		gru2.setItems(list2);

		// Create Defense play list
		ArrayList<ExpandListChild> list3 = new ArrayList<ExpandListChild>();
		ExpandListGroup gru3 = new ExpandListGroup("Defense");
		ExpandListChild ch3_1 = new ExpandListChild("Run Stop");
		list3.add(ch3_1);
		ExpandListChild ch3_2 = new ExpandListChild("Pass Defense");
		list3.add(ch3_2);
		ExpandListChild ch3_3 = new ExpandListChild("Interception");
		list3.add(ch3_3);
		gru3.setItems(list3);

		// Add groups to list of groups
		list.add(gru1);
		list.add(gru2);
		list.add(gru3);

		return list;
	}

}
