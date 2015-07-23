package org.gtri.espn.activities.general;

import org.gtri.espn.R;
import org.gtri.objects.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FriendInfoActivity extends Activity {

	private User friend;
	private TextView textViewTitle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_friend_info);
        friend = (User) getIntent().getParcelableExtra("friend");

		textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		textViewTitle.setText(friend.getName());
    }
    
}
