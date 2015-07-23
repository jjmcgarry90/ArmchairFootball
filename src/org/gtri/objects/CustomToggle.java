package org.gtri.objects;


import org.gtri.espn.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class CustomToggle extends Button
{
        private String DEBUGTAG = "CustonButtonExample";
        
        // Keeps track of the current state, 0, 1, or 2
        private int _state;

        // Get the attributes created in attrs.xml
        private static final int[] STATE_ONE_SET =
        {
                R.attr.state_one
        };
        
        private static final int[] STATE_TWO_SET =
        {
                R.attr.state_two
        };

        // Constructors
        public CustomToggle(Context context)
        {
                super(context);
                
                // Set the default state and text
                _state = 0;
        }
        
        public CustomToggle(Context context, AttributeSet attrs)
        {
                super(context, attrs);
                
                // Set the default state and text
                _state = 0;
        }
        
        public CustomToggle(Context context, AttributeSet attrs, int defStyle)
        {
                super(context, attrs, defStyle);
                
                // Set the default state and text
                _state = 0;
        }
        
        @Override
        public boolean performClick()
        {
                // Move to the next state
                nextState();
                
                return super.performClick();
        }
        
        // Generate the drawable needed for the current state
        @Override
        protected int[] onCreateDrawableState(int extraSpace)
        {
                // Add the number of states you have
                final int[] drawableState = super.onCreateDrawableState(extraSpace + 3);
                
                if(_state == 0)
                {
                        mergeDrawableStates(drawableState, STATE_ONE_SET);
                }
                else if(_state == 1)
                {
                        mergeDrawableStates(drawableState, STATE_TWO_SET);
                }
                
                return drawableState;
        }
        
        // Set current state, 0-2
        public void setState(int state)
        {
                if((state > -1) && (state < 3))
                {
//                      Log.d(DEBUGTAG, "   Setting Toggle state to " + state);
                        _state = state;
                }
        }
        
        // Returns current state
        public int getState()
        {
                return _state;
        }

        // Increases state, or loops to 0
        public void nextState()
        {
                _state++;
                
                // Loop around if at the last state
                if(_state > 1)
                {
                        _state = 0;
                }
                
//              Log.d(DEBUGTAG, "   Setting Toggle state to " + _state);
               // showShortToast("ToggleState: " + _state);
        }
        
        // Decreases state, or loops to 2
        public void previousState()
        {
                _state--;
                
                // Loop around if at the first state
                if(_state < 0)
                {
                        _state = 1;
                }
                
                Log.d(DEBUGTAG, "   Setting Toggle state to " + _state);
                //showShortToast("ToggleState: " + _state);
        }
        
        
        // A method just to make using Toasts easier
        private void showShortToast(String s)
    {
        Toast.makeText(this.getContext(), s, Toast.LENGTH_SHORT).show();
    }
}