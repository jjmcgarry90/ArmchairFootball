package org.gtri.espn.activities.general;


import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.espn.activities.main.HomeActivity;
import org.gtri.objects.Event;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class GameInfoActivity extends Activity {

	// Constants for inter-activity actions


	private FanbookApplication context;
	private ArrayList comments;
	private ListView commentsList;
	private TextView title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_stats);
		context = (FanbookApplication) getApplicationContext();
		
		title = (TextView)findViewById(R.id.info_title);
		title.setText("Game Info");
		
		comments = new ArrayList();
		for(int i=0; i<5; i++)
			comments.add(new Object());
		
		CommentsAdapter adapter = new CommentsAdapter();
	//	commentsList = (ListView)findViewById(R.id.commentsList);
		//commentsList.setAdapter(adapter);
	}
	
private class CommentsAdapter extends ArrayAdapter {
		
		
		private CommentsAdapter() {
			super(GameInfoActivity.this, R.layout.comment, comments);
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			View row;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.comment, parent, false);
			} else {
				row = convertView;
			}
			
			return row;
		}
	}
}