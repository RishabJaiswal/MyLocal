package com.rj.tb3;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener
{
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		//Toast noti = Toast.makeText(Settings.this, "Settings Changes", Toast.LENGTH_LONG);
		//noti.show();
		
		LayoutInflater inf = getLayoutInflater();
		View view = inf.inflate(R.layout.noti, (ViewGroup)findViewById(R.id.notif));
		Toast notifi = new Toast(getBaseContext());
		notifi.setView(view);
		notifi.setDuration(Toast.LENGTH_LONG );
		notifi.show();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		SharedPreferences shdp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		shdp.registerOnSharedPreferenceChangeListener(this);
		
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    PreferenceManager.getDefaultSharedPreferences(getBaseContext())
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    PreferenceManager.getDefaultSharedPreferences(getBaseContext())
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
}
