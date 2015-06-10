package com.rj.tb3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class TB3act2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tb3act2);
		final MediaPlayer song = MediaPlayer.create(TB3act2.this, R.raw.coldplay);
		SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(getBaseContext()); //accessing Shared  preferences
		boolean play = shp.getBoolean("Setting 2", true);
		if (play == true)
			song.start();
		Thread timer = new Thread() 
		{
			public void run()
			{
				try{
					sleep(1000);
				}catch(Exception e) {
					e.printStackTrace();
				}
				finally{
					Intent i = new Intent("com.rj.tb3.TB3");
					startActivity(i);
					song.stop();
					finish();
				}
			}
		};
		timer.start();
	}
}
