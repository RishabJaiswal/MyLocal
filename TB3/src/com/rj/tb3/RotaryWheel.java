package com.rj.tb3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class RotaryWheel extends Activity
{

	LinearLayout ll;
	Rwheel wheel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rotate);
		ll = (LinearLayout)findViewById(R.id.ll);
		wheel = new Rwheel(this);
		ll.addView(wheel, 1);
	
	}
	
}
