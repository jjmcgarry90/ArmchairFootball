package org.gtri.espn.activities.general;

import org.gtri.espn.R;
import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.Participant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ParticipantInfoActivity extends Activity {

	private FanbookApplication context;
	private Participant participant;
	private TextView textViewTitle, textViewTeamInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_participant_info);
		context = (FanbookApplication) getApplicationContext();
		participant = (Participant) getIntent().getParcelableExtra("participant");
		participant = context.getSchedule().getParticipantByName(participant.getName());

		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		textViewTitle.setText(participant.getFullName());
		
		textViewTeamInfo = (TextView) findViewById(R.id.textViewTeamInfo);
		textViewTeamInfo.setText(participant.getCity()+", "+participant.getState()+"\n"+participant.getStadium());
	}

}
