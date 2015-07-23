package org.gtri.helper;

import org.gtri.espn.R;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ViewBuilder {

	/*
	 * Creates a divider view
	 */
	public static View buildDivider(Context context) {
		// Creates a divider
		int divider_height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
		View divider = new View(context);
		divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, divider_height));
		divider.setBackgroundColor(context.getResources().getColor(R.color.White));
		return divider;
	}
	
	/*
	 * Creates a divider view
	 */
	public static View buildDividerSmall(Context context) {
		int divider_height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
		View divider = new View(context);
		LinearLayout.LayoutParams params_divider = new LayoutParams(LayoutParams.FILL_PARENT, divider_height);
		params_divider.setMargins((int) context.getResources().getDimension(R.dimen.margin_medium), 0, (int) context.getResources().getDimension(R.dimen.margin_medium), 0);
		divider.setLayoutParams(params_divider);
		divider.setBackgroundColor(context.getResources().getColor(R.color.White));
		return divider;
	}

}
