package org.gtri.espn.activities.main;

import org.gtri.espn.R;
import org.gtri.espn.activities.general.ManageHuddles;
import org.gtri.espn.activities.general.ManageNewsfeedActivity;
import org.gtri.espn.activities.general.ManageProfiles;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class SettingsActivity2 extends ActivityGroup {

	private FanbookApplication context;
	private TabHost tabHost;
	private Button profile, settings, newsfeed;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		context = (FanbookApplication) getApplicationContext();
		
		profile = (Button) findViewById(R.id.profileButton);
		profile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
				startActivity(intent);
				
			}
		});
		
		settings = (Button)findViewById(R.id.settingsButton);
		settings.setEnabled(false);
		newsfeed = (Button)findViewById(R.id.newsfeedButton);
		newsfeed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();		
			}
		});
		
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(getLocalActivityManager());
		
		TabSpec tab1 = tabHost.newTabSpec("tab1");
		tab1.setIndicator("Newsfeed").setContent(new Intent(this, ManageNewsfeedActivity.class));
		
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		tab2.setIndicator("Profiles").setContent(new Intent(this, ManageProfiles.class));
		
		TabSpec tab3 = tabHost.newTabSpec("tab3");
		tab3.setIndicator("Huddles").setContent(new Intent(this, ManageHuddles.class));
		
		try {
			tabHost.addTab(tab1);
			tabHost.addTab(tab2);
			tabHost.addTab(tab3);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}