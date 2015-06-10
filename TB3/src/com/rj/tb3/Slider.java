package com.rj.tb3;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Slider extends Activity
{

	String fonts[]={"fonts/Roboto-BlackItalic.ttf",
			      "fonts/Roboto-Light.ttf",
			      "fonts/Roboto-Medium.ttf" ,
			      "fonts/Roboto-Thin.ttf",
			      "fonts/Roboto-Regular.ttf"};
	
	int ids[]={R.id.font1,
			R.id.font2,
			R.id.font3,
			R.id.font4,
			R.id.font5};
	
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slider);
		Typeface tf;
		
		int i=0;
		for (String font:fonts)
		{
			tf =Typeface.createFromAsset(getAssets(), font);
			tv = (TextView)findViewById(ids[i++]);
			tv.setTypeface(tf);
		}
		
	}
 	
}
