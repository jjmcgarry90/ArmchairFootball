package org.gtri.espn.activities.general;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.gtri.espn.R;
public class Test extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	   setContentView(R.layout.test);
	   ViewGroup root = (ViewGroup)findViewById(R.id.test_root);
	   LayoutInflater inflater = getLayoutInflater();
	   
	   View v = inflater.inflate(R.layout.comment, null);
	   int pixelsValue = 20; // margin in pixels
	   float d = getBaseContext().getResources().getDisplayMetrics().density;
	   int margin = (int)(pixelsValue * d);
	   Log.v("espn",String.valueOf(margin));
	   LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	   p.setMargins(margin, 0, 0, 0);
	   v.setLayoutParams(p);
	   root.addView(v);
	  
	   ViewGroup second = (ViewGroup) inflater.inflate(R.layout.newsfeed_list_item, root);

	}

}
