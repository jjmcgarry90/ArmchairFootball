package org.gtri.espn.activities.main;

import java.util.ArrayList;

import org.gtri.espn.R;
import org.gtri.objects.CustomToggle;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PredictActivity extends Activity implements OnClickListener {
	
	private static final String LOG_TAG = "test";
	int lastExpandedGroupPosition;
	Button btnCancel;
	Button huddle;
	Button offense;
	Button defense;
	Button result;
	LinearLayout resultLayout;
	LinearLayout defenseLayout;
	LinearLayout offenseLayout;
	RelativeLayout predictLayout;
	View resultScreen;
	View defenseScreen;
	View offenseScreen;
	CustomToggle first, turn, huge, stop, td, notd, fg, nofg, defPen, offPen, 
				 blitz, noPass, breakup, givePass, block, rough, stuff, giveRun,
				 run, pass, kick, left, middle, right, Long, Short, trick, sack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_predict);
		
		btnCancel = (Button) findViewById(R.id.predict_btnCancel);
		offense = (Button) findViewById(R.id.btnOffense);
		defense = (Button) findViewById(R.id.btnDefense);	
		result = (Button) findViewById(R.id.btnResult);
		
		
		
		huddle = (Button) findViewById(R.id.predict_btnHuddle);
		
		
		offense.setOnClickListener(this);
		offense.performClick();
		
		result.setOnClickListener(this);
		defense.setOnClickListener(this);

		
		huddle.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

        //Start result listeners
		case R.id.btnFirst:
			if(first.getState()==1) {
				Log.d("test", "1");
				first.setBackgroundResource(R.drawable.custom_toggleon);	
				turn.setState(0);
				turn.setBackgroundResource(R.drawable.custom_toggledisabled);
				stop.setState(0);
				stop.setBackgroundResource(R.drawable.custom_toggledisabled);
				td.setState(0);
				td.setBackgroundResource(R.drawable.custom_toggledisabled);
				fg.setState(0);
				fg.setBackgroundResource(R.drawable.custom_toggledisabled);
				offPen.setState(0);
				offPen.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (first.getState()==0) {
				Log.d("test", "0");
				first.setBackgroundResource(R.drawable.custom_toggleoff);
				stop.setBackgroundResource(R.drawable.custom_toggleoff);
				turn.setBackgroundResource(R.drawable.custom_toggleoff);
				huge.setBackgroundResource(R.drawable.custom_toggleoff);
				td.setBackgroundResource(R.drawable.custom_toggleoff);
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
				offPen.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnTurn:
			if(turn.getState()==1) {
				Log.d("test", "1");
				turn.setBackgroundResource(R.drawable.custom_toggleon);	
				first.setState(0);
				first.setBackgroundResource(R.drawable.custom_toggledisabled);
				huge.setState(0);
				huge.setBackgroundResource(R.drawable.custom_toggledisabled);
				td.setState(0);
				td.setBackgroundResource(R.drawable.custom_toggledisabled);
				fg.setState(0);
				fg.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (huge.getState()==0) {
				Log.d("test", "0");
				turn.setBackgroundResource(R.drawable.custom_toggleoff);
				stop.setBackgroundResource(R.drawable.custom_toggleoff);
				first.setBackgroundResource(R.drawable.custom_toggleoff);
				huge.setBackgroundResource(R.drawable.custom_toggleoff);
				td.setBackgroundResource(R.drawable.custom_toggleoff);
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
			
		case R.id.btnHuge:
			if(huge.getState()==1) {
				Log.d("test", "1");
				huge.setBackgroundResource(R.drawable.custom_toggleon);
				turn.setState(0);
				turn.setBackgroundResource(R.drawable.custom_toggledisabled);
				stop.setState(0);
				stop.setBackgroundResource(R.drawable.custom_toggledisabled);
				td.setState(0);
				td.setBackgroundResource(R.drawable.custom_toggledisabled);
				fg.setState(0);
				fg.setBackgroundResource(R.drawable.custom_toggledisabled);
				offPen.setState(0);
				offPen.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (huge.getState()==0) {
				Log.d("test", "0");
				huge.setBackgroundResource(R.drawable.custom_toggleoff);
				stop.setBackgroundResource(R.drawable.custom_toggleoff);
				turn.setBackgroundResource(R.drawable.custom_toggleoff);
				td.setBackgroundResource(R.drawable.custom_toggleoff);
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
				offPen.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnStop:
			if(stop.getState()==1) {
				Log.d("test", "1");
				stop.setBackgroundResource(R.drawable.custom_toggleon);
				first.setState(0);
				first.setBackgroundResource(R.drawable.custom_toggledisabled);
				huge.setState(0);
				huge.setBackgroundResource(R.drawable.custom_toggledisabled);
				td.setState(0);
				td.setBackgroundResource(R.drawable.custom_toggledisabled);
				fg.setState(0);
				fg.setBackgroundResource(R.drawable.custom_toggledisabled);
				notd.performClick();
			}
			else if (stop.getState()==0) {
				Log.d("test", "0");
				stop.setBackgroundResource(R.drawable.custom_toggleoff);
				first.setBackgroundResource(R.drawable.custom_toggleoff);
				huge.setBackgroundResource(R.drawable.custom_toggleoff);
				td.setBackgroundResource(R.drawable.custom_toggleoff);
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
				notd.performClick();
			}
			break;
			
		case R.id.btnTd:
			if(td.getState()==1) {
				Log.d("test", "1");
				td.setBackgroundResource(R.drawable.custom_toggleon);
				first.setState(0);
				first.setBackgroundResource(R.drawable.custom_toggledisabled);
				fg.setState(0);
				fg.setBackgroundResource(R.drawable.custom_toggledisabled);
				turn.setState(0);
				turn.setBackgroundResource(R.drawable.custom_toggledisabled);
				stop.setState(0);
				stop.setBackgroundResource(R.drawable.custom_toggledisabled);
				notd.setState(0);
				notd.setBackgroundResource(R.drawable.custom_toggledisabled);
				nofg.setState(0);
				nofg.setBackgroundResource(R.drawable.custom_toggledisabled);
				offPen.setState(0);
				offPen.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (td.getState()==0) {
				Log.d("test", "0");
				td.setBackgroundResource(R.drawable.custom_toggleoff);
				first.setBackgroundResource(R.drawable.custom_toggleoff);
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
				turn.setBackgroundResource(R.drawable.custom_toggleoff);
				stop.setBackgroundResource(R.drawable.custom_toggleoff);
				notd.setBackgroundResource(R.drawable.custom_toggleoff);
				nofg.setBackgroundResource(R.drawable.custom_toggleoff);
				offPen.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnNoTd:
			if(notd.getState()==1) {
				Log.d("test", "1");
				notd.setBackgroundResource(R.drawable.custom_toggleon);
				td.setState(0);
				td.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (notd.getState()==0) {
				Log.d("test", "0");
				notd.setBackgroundResource(R.drawable.custom_toggleoff);
				td.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnField:
			if(fg.getState()==1) {
				Log.d("test", "1");
				fg.setBackgroundResource(R.drawable.custom_toggleon);
				first.setState(0);
				first.setBackgroundResource(R.drawable.custom_toggledisabled);
				huge.setState(0);
				huge.setBackgroundResource(R.drawable.custom_toggledisabled);
				turn.setState(0);
				turn.setBackgroundResource(R.drawable.custom_toggledisabled);
				notd.setState(0);
				notd.setBackgroundResource(R.drawable.custom_toggledisabled);
				stop.setState(0);
				stop.setBackgroundResource(R.drawable.custom_toggledisabled);
				nofg.setState(0);
				nofg.setBackgroundResource(R.drawable.custom_toggledisabled);
				offPen.setState(0);
				offPen.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (fg.getState()==0) {
				Log.d("test", "0");
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
				first.setBackgroundResource(R.drawable.custom_toggleoff);
				huge.setBackgroundResource(R.drawable.custom_toggleoff);
				turn.setBackgroundResource(R.drawable.custom_toggleoff);
				stop.setBackgroundResource(R.drawable.custom_toggleoff);
				notd.setBackgroundResource(R.drawable.custom_toggleoff);
				nofg.setBackgroundResource(R.drawable.custom_toggleoff);
				offPen.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnNoField:
			if(nofg.getState()==1) {
				Log.d("test", "1");
				nofg.setBackgroundResource(R.drawable.custom_toggleon);
				fg.setState(0);
				fg.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (nofg.getState()==0) {
				Log.d("test", "0");
				nofg.setBackgroundResource(R.drawable.custom_toggleoff);
				fg.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnDefPen:
			if(defPen.getState()==1) {
				Log.d("test", "1");
				defPen.setBackgroundResource(R.drawable.custom_toggleon);
				turn.setState(0);
				turn.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (defPen.getState()==0) {
				Log.d("test", "0");
				defPen.setBackgroundResource(R.drawable.custom_toggleoff);
				turn.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnOffPen:
			if(offPen.getState()==1) {
				Log.d("test", "1");
				offPen.setBackgroundResource(R.drawable.custom_toggleon);
				notd.setState(0);
				notd.setBackgroundResource(R.drawable.custom_toggledisabled);
				nofg.setState(0);
				nofg.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (offPen.getState()==0) {
				Log.d("test", "0");
				offPen.setBackgroundResource(R.drawable.custom_toggleoff);
				notd.setBackgroundResource(R.drawable.custom_toggleoff);
				nofg.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
		//End result listeners
			
		//Start defense listeners
		case R.id.btnBlitz:
			if(blitz.getState()==1) {
				Log.d("test", "1");
				blitz.setBackgroundResource(R.drawable.custom_toggleon);
				noPass.setState(0);
				noPass.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (blitz.getState()==0) {
				Log.d("test", "0");
				blitz.setBackgroundResource(R.drawable.custom_toggleoff);
				noPass.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
		
		case R.id.btnNoQbPass:
			if(noPass.getState()==1) {
				Log.d("test", "1");
				noPass.setBackgroundResource(R.drawable.custom_toggleon);
				blitz.setState(0);
				blitz.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (noPass.getState()==0) {
				Log.d("test", "0");
				noPass.setBackgroundResource(R.drawable.custom_toggleoff);
				blitz.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnBreakup:
			if(breakup.getState()==1) {
				Log.d("test", "1");
				breakup.setBackgroundResource(R.drawable.custom_toggleon);
				givePass.setState(0);
				givePass.setBackgroundResource(R.drawable.custom_toggledisabled);
				block.setState(0);
				block.setBackgroundResource(R.drawable.custom_toggledisabled);
				rough.setState(0);
				rough.setBackgroundResource(R.drawable.custom_toggledisabled);
				sack.setState(0);
				sack.setBackgroundResource(R.drawable.custom_toggledisabled);
				stuff.setState(0);
				stuff.setBackgroundResource(R.drawable.custom_toggledisabled);
				giveRun.setState(0);
				giveRun.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (breakup.getState()==0) {
				Log.d("test", "0");
				breakup.setBackgroundResource(R.drawable.custom_toggleoff);
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				sack.setBackgroundResource(R.drawable.custom_toggleoff);
				stuff.setBackgroundResource(R.drawable.custom_toggleoff);
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnGiveup:
			if(givePass.getState()==1) {
				Log.d("test", "1");
				givePass.setBackgroundResource(R.drawable.custom_toggleon);
				breakup.setState(0);
				breakup.setBackgroundResource(R.drawable.custom_toggledisabled);
				block.setState(0);
				block.setBackgroundResource(R.drawable.custom_toggledisabled);
				rough.setState(0);
				rough.setBackgroundResource(R.drawable.custom_toggledisabled);
				sack.setState(0);
				sack.setBackgroundResource(R.drawable.custom_toggledisabled);
				stuff.setState(0);
				stuff.setBackgroundResource(R.drawable.custom_toggledisabled);
				giveRun.setState(0);
				giveRun.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (givePass.getState()==0) {
				Log.d("test", "0");
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				breakup.setBackgroundResource(R.drawable.custom_toggleoff);
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				sack.setBackgroundResource(R.drawable.custom_toggleoff);
				stuff.setBackgroundResource(R.drawable.custom_toggleoff);
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnBlock:
			if(block.getState()==1) {
				Log.d("test", "1");
				block.setBackgroundResource(R.drawable.custom_toggleon);
				blitz.setState(0);
				blitz.setBackgroundResource(R.drawable.custom_toggledisabled);
				noPass.setState(0);
				noPass.setBackgroundResource(R.drawable.custom_toggledisabled);
				givePass.setState(0);
				givePass.setBackgroundResource(R.drawable.custom_toggledisabled);
				breakup.setState(0);
				breakup.setBackgroundResource(R.drawable.custom_toggledisabled);
				rough.setState(0);
				rough.setBackgroundResource(R.drawable.custom_toggledisabled);
				sack.setState(0);
				sack.setBackgroundResource(R.drawable.custom_toggledisabled);
				stuff.setState(0);
				stuff.setBackgroundResource(R.drawable.custom_toggledisabled);
				giveRun.setState(0);
				giveRun.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (block.getState()==0) {
				Log.d("test", "0");
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				blitz.setBackgroundResource(R.drawable.custom_toggleoff);
				noPass.setBackgroundResource(R.drawable.custom_toggleoff);
				breakup.setBackgroundResource(R.drawable.custom_toggleoff);
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				sack.setBackgroundResource(R.drawable.custom_toggleoff);
				stuff.setBackgroundResource(R.drawable.custom_toggleoff);
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnRough:
			if(rough.getState()==1) {
				Log.d("test", "1");
				rough.setBackgroundResource(R.drawable.custom_toggleon);
				blitz.setState(0);
				blitz.setBackgroundResource(R.drawable.custom_toggledisabled);
				noPass.setState(0);
				noPass.setBackgroundResource(R.drawable.custom_toggledisabled);
				givePass.setState(0);
				givePass.setBackgroundResource(R.drawable.custom_toggledisabled);
				breakup.setState(0);
				breakup.setBackgroundResource(R.drawable.custom_toggledisabled);
				block.setState(0);
				block.setBackgroundResource(R.drawable.custom_toggledisabled);
				sack.setState(0);
				sack.setBackgroundResource(R.drawable.custom_toggledisabled);
				stuff.setState(0);
				stuff.setBackgroundResource(R.drawable.custom_toggledisabled);
				giveRun.setState(0);
				giveRun.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (rough.getState()==0) {
				Log.d("test", "0");
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				blitz.setBackgroundResource(R.drawable.custom_toggleoff);
				noPass.setBackgroundResource(R.drawable.custom_toggleoff);
				breakup.setBackgroundResource(R.drawable.custom_toggleoff);
				sack.setBackgroundResource(R.drawable.custom_toggleoff);
				stuff.setBackgroundResource(R.drawable.custom_toggleoff);
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnStuff:
			if(stuff.getState()==1) {
				Log.d("test", "1");
				stuff.setBackgroundResource(R.drawable.custom_toggleon);
				blitz.setState(0);
				blitz.setBackgroundResource(R.drawable.custom_toggledisabled);
				noPass.setState(0);
				noPass.setBackgroundResource(R.drawable.custom_toggledisabled);
				givePass.setState(0);
				givePass.setBackgroundResource(R.drawable.custom_toggledisabled);
				breakup.setState(0);
				breakup.setBackgroundResource(R.drawable.custom_toggledisabled);
				block.setState(0);
				block.setBackgroundResource(R.drawable.custom_toggledisabled);
				stuff.setState(0);
				stuff.setBackgroundResource(R.drawable.custom_toggledisabled);
				giveRun.setState(0);
				giveRun.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (stuff.getState()==0) {
				Log.d("test", "0");
				stuff.setBackgroundResource(R.drawable.custom_toggleoff);
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				blitz.setBackgroundResource(R.drawable.custom_toggleoff);
				noPass.setBackgroundResource(R.drawable.custom_toggleoff);
				breakup.setBackgroundResource(R.drawable.custom_toggleoff);
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnGive:
			if(giveRun.getState()==1) {
				Log.d("test", "1");
				giveRun.setBackgroundResource(R.drawable.custom_toggleon);
				givePass.setState(0);
				givePass.setBackgroundResource(R.drawable.custom_toggledisabled);
				breakup.setState(0);
				breakup.setBackgroundResource(R.drawable.custom_toggledisabled);
				block.setState(0);
				block.setBackgroundResource(R.drawable.custom_toggledisabled);
				stuff.setState(0);
				stuff.setBackgroundResource(R.drawable.custom_toggledisabled);
				rough.setState(0);
				rough.setBackgroundResource(R.drawable.custom_toggledisabled);

			}
			else if (giveRun.getState()==0) {
				Log.d("test", "0");
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
				stuff.setBackgroundResource(R.drawable.custom_toggleoff);
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				breakup.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnSack:
			if(sack.getState()==1) {
				Log.d("test", "1");
				sack.setBackgroundResource(R.drawable.custom_toggleon);
				block.setState(0);
				block.setBackgroundResource(R.drawable.custom_toggledisabled);
				noPass.setState(0);
				noPass.setBackgroundResource(R.drawable.custom_toggledisabled);
				givePass.setState(0);
				givePass.setBackgroundResource(R.drawable.custom_toggledisabled);
				rough.setState(0);
				rough.setBackgroundResource(R.drawable.custom_toggledisabled);
				giveRun.setState(0);
				giveRun.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (sack.getState()==0) {
				Log.d("test", "0");
				sack.setBackgroundResource(R.drawable.custom_toggleoff);
				block.setBackgroundResource(R.drawable.custom_toggleoff);
				noPass.setBackgroundResource(R.drawable.custom_toggleoff);
				givePass.setBackgroundResource(R.drawable.custom_toggleoff);
				rough.setBackgroundResource(R.drawable.custom_toggleoff);
				giveRun.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
		//End defense listeners
			
		//Start offense listeners
		case R.id.btnRun:
			Log.d("test", "1");
			if(run.getState()==1) {
				run.setBackgroundResource(R.drawable.custom_toggleon);
				pass.setState(0);
				pass.setBackgroundResource(R.drawable.custom_toggledisabled);
				kick.setState(0);
				kick.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (run.getState()==0) {
				Log.d("test", "0");
				run.setBackgroundResource(R.drawable.custom_toggleoff);
				pass.setBackgroundResource(R.drawable.custom_toggleoff);
				kick.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnPass:
			if(pass.getState()==1) {
				Log.d("test", "1");
				pass.setBackgroundResource(R.drawable.custom_toggleon);	
				run.setState(0);
				run.setBackgroundResource(R.drawable.custom_toggledisabled);
				kick.setState(0);
				kick.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (pass.getState()==0) {
				Log.d("test", "0");
				run.setBackgroundResource(R.drawable.custom_toggleoff);
				pass.setBackgroundResource(R.drawable.custom_toggleoff);
				kick.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnKick:
			if(kick.getState()==1) {
				Log.d("test", "1");
				kick.setBackgroundResource(R.drawable.custom_toggleon);
				pass.setState(0);
				pass.setBackgroundResource(R.drawable.custom_toggledisabled);
				run.setState(0);
				run.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (kick.getState()==0) {
				Log.d("test", "0");
				run.setBackgroundResource(R.drawable.custom_toggleoff);
				pass.setBackgroundResource(R.drawable.custom_toggleoff);
				kick.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnLeft:
			if(left.getState()==1) {
				Log.d("test", "1");
				left.setBackgroundResource(R.drawable.custom_toggleon);
				right.setState(0);
				right.setBackgroundResource(R.drawable.custom_toggledisabled);
				middle.setState(0);
				middle.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (left.getState()==0) {
				Log.d("test", "0");
				left.setBackgroundResource(R.drawable.custom_toggleoff);
				right.setBackgroundResource(R.drawable.custom_toggleoff);
				middle.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnMiddle:
			if(middle.getState()==1) {
				Log.d("test", "1");
				middle.setBackgroundResource(R.drawable.custom_toggleon);
				right.setState(0);
				right.setBackgroundResource(R.drawable.custom_toggledisabled);
				left.setState(0);
				left.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (middle.getState()==0) {
				Log.d("test", "0");
				middle.setBackgroundResource(R.drawable.custom_toggleoff);
				right.setBackgroundResource(R.drawable.custom_toggleoff);
				left.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnRight:
			if(right.getState()==1) {
				Log.d("test", "1");
				right.setBackgroundResource(R.drawable.custom_toggleon);
				middle.setState(0);
				middle.setBackgroundResource(R.drawable.custom_toggledisabled);
				left.setState(0);
				left.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (right.getState()==0) {
				Log.d("test", "0");
				right.setBackgroundResource(R.drawable.custom_toggleoff);
				left.setBackgroundResource(R.drawable.custom_toggleoff);
				middle.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnLong:
			if(Long.getState()==1) {
				Log.d("test", "1");
				Long.setBackgroundResource(R.drawable.custom_toggleon);		
				Short.setState(0);
				Short.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (Long.getState()==0) {
				Log.d("test", "0");
				Long.setBackgroundResource(R.drawable.custom_toggleoff);
				Short.setBackgroundResource(R.drawable.custom_toggleoff);
				
			}
			break;
			
		case R.id.btnShort:
			if(Short.getState()==1) {
				Log.d("test", "1");
				Short.setBackgroundResource(R.drawable.custom_toggleon);		
				Long.setState(0);
				Long.setBackgroundResource(R.drawable.custom_toggledisabled);
			}
			else if (Short.getState()==0) {
				Log.d("test", "0");
				Short.setBackgroundResource(R.drawable.custom_toggleoff);
				Long.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
			
		case R.id.btnTrick:
			if(trick.getState()==1) {
				Log.d("test", "1");
				trick.setBackgroundResource(R.drawable.custom_toggleon);		
			}
			else if (trick.getState()==0) {
				Log.d("test", "0");
				trick.setBackgroundResource(R.drawable.custom_toggleoff);
			}
			break;
		//End offense listeners
		
		case R.id.btnOffense:
			switchToOffense();
            break;
			
		case R.id.btnDefense:
			switchToDefense();
			break;

            
		case R.id.btnResult:
			switchToResult();
			break;
			
           
		case R.id.predict_btnCancel:
			finish();
			break;
		}
		
	}
	
	public void switchToResult() {
		 //Check if the Layout already exists
        resultLayout = (LinearLayout)findViewById(R.layout.activity_predict_result);
        if(resultLayout == null){
            	//Inflate the Hidden Layout Information View 
            	predictLayout = (RelativeLayout)findViewById(R.id.predict_relative);
            	resultScreen = getLayoutInflater().inflate(R.layout.activity_predict_result, predictLayout, false);
            	predictLayout.removeAllViews();
            	predictLayout.addView(resultScreen);
            
            	
            	//Buttonlisteners
            	first = (CustomToggle) findViewById(R.id.btnFirst);
            	first.setOnClickListener(this);
            	turn = (CustomToggle) findViewById(R.id.btnTurn);
            	turn.setOnClickListener(this);
            	huge = (CustomToggle) findViewById(R.id.btnHuge);
            	huge.setOnClickListener(this);
            	stop = (CustomToggle) findViewById(R.id.btnStop);
            	stop.setOnClickListener(this);
            	td = (CustomToggle) findViewById(R.id.btnTd);
            	td.setOnClickListener(this);
            	notd = (CustomToggle) findViewById(R.id.btnNoTd);
            	notd.setOnClickListener(this);
            	fg = (CustomToggle) findViewById(R.id.btnField);
            	fg.setOnClickListener(this);
            	nofg = (CustomToggle) findViewById(R.id.btnNoField);
            	nofg.setOnClickListener(this);
            	defPen = (CustomToggle) findViewById(R.id.btnDefPen);
            	defPen.setOnClickListener(this);
            	offPen = (CustomToggle) findViewById(R.id.btnOffPen);
            	offPen.setOnClickListener(this);
            	
            	offense = (Button) findViewById(R.id.btnOffense);
            	offense.setOnClickListener(this);
    			defense = (Button) findViewById(R.id.btnDefense);
    			defense.setOnClickListener(this);
    			result = (Button) findViewById(R.id.btnResult);
    			result.setOnClickListener(this);
        }
	}
	
	public void switchToOffense() {
		 //Check if the Layout already exists
        offenseLayout = (LinearLayout)findViewById(R.layout.activity_predict_offense);
        if(offenseLayout == null){
            //Inflate the Hidden Layout Information View 
            predictLayout = (RelativeLayout)findViewById(R.id.predict_relative);
            offenseScreen = getLayoutInflater().inflate(R.layout.activity_predict_offense, predictLayout, false);
            predictLayout.removeAllViews();
            predictLayout.addView(offenseScreen);
            
            //buttonlisteners
            run = (CustomToggle) findViewById(R.id.btnRun);
            run.setOnClickListener(this);
            pass = (CustomToggle) findViewById(R.id.btnPass);
            pass.setOnClickListener(this);
            kick = (CustomToggle) findViewById(R.id.btnKick);
            kick.setOnClickListener(this);
            left = (CustomToggle) findViewById(R.id.btnLeft);
            left.setOnClickListener(this);
            middle = (CustomToggle) findViewById(R.id.btnMiddle);
            middle.setOnClickListener(this);
            right = (CustomToggle) findViewById(R.id.btnRight);
            right.setOnClickListener(this);
            Long = (CustomToggle) findViewById(R.id.btnLong);
            Long.setOnClickListener(this);
            Short = (CustomToggle) findViewById(R.id.btnShort);
            Short.setOnClickListener(this);
            trick = (CustomToggle) findViewById(R.id.btnTrick);
            trick.setOnClickListener(this);
            
            offense = (Button) findViewById(R.id.btnOffense);
            offense.setOnClickListener(this);
    		defense = (Button) findViewById(R.id.btnDefense);
    		defense.setOnClickListener(this);
    		result = (Button) findViewById(R.id.btnResult);
            result.setOnClickListener(this);   
        }
	}

	public void switchToDefense() {
		 //Check if the Layout already exists
        defenseLayout = (LinearLayout)findViewById(R.layout.activity_predict_defense);
        if(defenseLayout == null){
            //Inflate the Hidden Layout Information View 
            predictLayout = (RelativeLayout)findViewById(R.id.predict_relative);
            defenseScreen = getLayoutInflater().inflate(R.layout.activity_predict_defense, predictLayout, false);
            predictLayout.removeAllViews();
            predictLayout.addView(defenseScreen);
            
            
            //buttonlisteners
            blitz = (CustomToggle) findViewById(R.id.btnBlitz);
        	blitz.setOnClickListener(this);
        	noPass = (CustomToggle) findViewById(R.id.btnNoQbPass);
        	noPass.setOnClickListener(this);
        	breakup = (CustomToggle) findViewById(R.id.btnBreakup);
        	breakup.setOnClickListener(this);
        	givePass = (CustomToggle) findViewById(R.id.btnGiveup);
        	givePass.setOnClickListener(this);
        	block = (CustomToggle) findViewById(R.id.btnBlock);
        	block.setOnClickListener(this);
        	rough = (CustomToggle) findViewById(R.id.btnRough);
        	rough.setOnClickListener(this);
        	stuff = (CustomToggle) findViewById(R.id.btnStuff);
        	stuff.setOnClickListener(this);
        	giveRun = (CustomToggle) findViewById(R.id.btnGive);
        	giveRun.setOnClickListener(this);
        	sack = (CustomToggle) findViewById(R.id.btnSack);
        	sack.setOnClickListener(this);
        	
            
            offense = (Button) findViewById(R.id.btnOffense);
            offense.setOnClickListener(this);
    		defense = (Button) findViewById(R.id.btnDefense);
    		defense.setOnClickListener(this);
    		result = (Button) findViewById(R.id.btnResult);
            result.setOnClickListener(this);     
        }
	}
}