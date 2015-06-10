package com.rj.tb3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

public class GFX extends Activity
{
	MyView v;
	PowerManager.WakeLock wl;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{ 
		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP, "lights");
		super.onCreate(savedInstanceState);
		v = new MyView(this);
		setContentView(v);
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
		wl.release();
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		wl.acquire();
	}

}

