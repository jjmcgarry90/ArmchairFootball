package org.gtri.espn.activities.general;



import org.gtri.espn.R;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

public class OpinionActivity extends Activity {
	private TextView title;
	private Button agree, disagree, submit;
	private EditText commentBox;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opinion);
		
		title = (TextView)findViewById(R.id.opinionTitle);
		agree = (Button)findViewById(R.id.agreeButton);
		disagree = (Button)findViewById(R.id.disagreeButton);
		submit = (Button)findViewById(R.id.submitButton);
		commentBox = (EditText)findViewById(R.id.commentBox);
		String source = getIntent().getExtras().getString("source");
		
		if ("general".equals(source)) {
			title.setText("General Comment");
			agree.setVisibility(View.INVISIBLE);
			disagree.setVisibility(View.INVISIBLE);
			commentBox.setText("Enter your comment here..");
		}
		
		if ("referee".equals(source)) {
			title.setText("Referee Opinion");
			agree.setText("Nice call!");
			disagree.setText("You blind bro?!");
			submit.setVisibility(View.INVISIBLE);
			commentBox.setText("Something Specific to say?");
		}
		
		if ("coach".equals(source)) {
			title.setText("Coach's Decision Opinion");
			agree.setText("Genius!");
			disagree.setText("What were you thinking?!");
			submit.setVisibility(View.INVISIBLE);
			commentBox.setText("Something Specific to say?");
		}
		
		if ("player".equals(source)) {
			title.setText("Player Opinion");
			agree.setText("That's what your paid for!");
			disagree.setText("You idiot!");
			submit.setVisibility(View.INVISIBLE);
			commentBox.setText("Something Specific to say?");
		}
			
		
	}
}
