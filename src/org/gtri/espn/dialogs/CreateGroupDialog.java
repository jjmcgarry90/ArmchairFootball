package org.gtri.espn.dialogs;

import org.gtri.espn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CreateGroupDialog extends Activity {

	public static final String RESULT_VALUE = "RESULT_VALUE_KEY";
	private Button buttonCreate, buttonCancel;
	private EditText editTextName;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_group);
		
		editTextName = (EditText)findViewById(R.id.editTextName);
		
		buttonCreate = (Button)findViewById(R.id.buttonCreate);
		buttonCreate.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				okay();
			}
		});
		
		buttonCancel = (Button)findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				cancel();
			}
		});
		
	} // end method
	
	private void okay(){
		Intent resultIntent = new Intent(this, CreateGroupDialog.class);
		resultIntent.putExtra(RESULT_VALUE, editTextName.getText().toString());
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	} // end method
	
	private void cancel(){
		Intent resultIntent = new Intent(this, CreateGroupDialog.class);
		setResult(Activity.RESULT_CANCELED, resultIntent);
		finish();
	} // end method
	
} // end class