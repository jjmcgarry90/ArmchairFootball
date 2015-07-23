package org.gtri.espn.activities.general;

import org.gtri.espn.R;
import org.gtri.espn.R.layout;
import org.gtri.espn.dialogs.FavoriteTeamDialog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ManageNewsfeedActivity extends Activity {

	private Button editFiltersButton;
	private ListView filtersList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_newsfeed);
        
        editFiltersButton = (Button) findViewById(R.id.editTeamsBtn);
        editFiltersButton.setOnClickListener(onEditTeams);
        
        filtersList = (ListView) findViewById(R.id.filtersListView);
        //filtersList.setAdapter(new ArrayAdapter<String>(this, R.layout.blah, ARRAY));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_manage_newsfeed, menu);
        return true;
    }
    
    OnClickListener onEditTeams = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(ManageNewsfeedActivity.this, FavoriteTeamDialog.class);
			startActivity(intent);
		}
	};
}
