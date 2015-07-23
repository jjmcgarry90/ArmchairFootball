package org.gtri.espn.activities.general;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;



public class PlayInfoActivity extends Activity {

	// Constants for inter-activity actions


	private FanbookApplication context;
	private ArrayList comments;
	private ListView commentsList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_stats);
		context = (FanbookApplication) getApplicationContext();
				
		 //** set up formatting
		 int pixelsValue = 20;
		 float d = getBaseContext().getResources().getDisplayMetrics().density;
		 int margin = (int)(pixelsValue * d);
		 LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 p.setMargins(0, 0, 0, margin/2);
		 LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		 p2.setMargins(margin, 0, 0, margin/2);
		 //**
		 
		 LayoutInflater inflater = getLayoutInflater();
		 ViewGroup root = (ViewGroup) findViewById(R.id.play_info_comment_area);
		 
		 View v = inflater.inflate(R.layout.comment, null);
		 v.setLayoutParams(p);
		 root.addView(v);
		 
		 for(int i=1; i<=2; i++) {
			 v = inflater.inflate(R.layout.comment, null);
			 v.setLayoutParams(p2);
			 root.addView(v);
		 }
		 
		 v = inflater.inflate(R.layout.comment, null);
		 v.setLayoutParams(p);
		 root.addView(v);
		 
		 v = inflater.inflate(R.layout.comment, null);
		 v.setLayoutParams(p2);
		 root.addView(v);
		   
	}
}