package com.rj.tb3;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		Button sub = (Button)findViewById(R.id.sub);
		final ToggleButton tog = (ToggleButton)findViewById(R.id.tog);
		final EditText pass = (EditText)findViewById(R.id.pass);
		final TextView inv = (TextView)findViewById(R.id.invalid);
		
		tog.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View arg0) {
				if (tog.isChecked())
					pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				else
					pass.setInputType(InputType.TYPE_CLASS_TEXT);
				
			}
		});
		
		final float size = inv.getTextSize();
		sub.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				String password = pass.getText().toString();
				if (password.equals("Rishab"))
				{
					inv.setTextSize(size);
					inv.setTextColor(Color.GREEN);
					inv.setText("Your Password is valid");
					inv.setGravity(Gravity.CENTER);
				}
				else if(password.equals("Yo") | password.equals("yo") )
				{
					Random ran = new Random();
					inv.setTextSize(ran.nextInt(80));
					inv.setText("Yo!!!");
					inv.setTextColor(Color.rgb(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256)));
					switch(ran.nextInt(3))
					{
						case 0:	inv.setGravity(Gravity.CENTER);
								break;
						case 1:	inv.setGravity(Gravity.LEFT);
								break;
						case 2:	inv.setGravity(Gravity.RIGHT);
								break;
						default:break;
					}
				}
				else
				{
					inv.setTextSize(size);
					inv.setTextColor(Color.RED);
					inv.setText("Invalid");
					inv.setGravity(Gravity.CENTER);
				}
			}
		});
	}
	
}
