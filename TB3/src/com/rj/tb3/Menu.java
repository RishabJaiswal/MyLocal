package com.rj.tb3;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity
{
	String classes[] ={"TB3act2","TextPlay", "Email", "SendString", "Settings", "GFX", "GFXSurface", "Slider", 
			"Tabs", "RotaryWheel", "Anim","Grid"};
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		String item = classes[position];
		try{
		Class<?> clazz = Class.forName("com.rj.tb3." + item);
		Intent i = new Intent(this,clazz);
		startActivity(i);
		finish();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
}
