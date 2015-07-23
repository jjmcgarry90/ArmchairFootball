package org.gtri.espn.activities.general;

import org.gtri.espn.R;
import org.gtri.objects.Event;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventInfoActivity extends Activity {

	private Event event;
	private TextView textViewTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_event_info);
		event = (Event) getIntent().getParcelableExtra("event");
		
		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		textViewTitle.setText(event.getName());
	}

}
