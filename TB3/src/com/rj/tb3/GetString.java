package com.rj.tb3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class GetString extends Activity implements OnCheckedChangeListener, OnClickListener
{
	//private TextView name2;
	private RadioGroup rg;
	private TextView showt;
	private Button backB;
	String put = "Option 1 Selected";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		
		//name2 = (TextView) findViewById(R.id.Name);
		rg = (RadioGroup) findViewById(R.id.rg);
		showt = (TextView) findViewById(R.id.show);
		backB = (Button) findViewById(R.id.back);
		
		//String Nameww = getIntent().getExtras().getString("Name");
		//name2.setText(Nameww);
		rg.setOnCheckedChangeListener(this);
		backB.setOnClickListener(this);
		
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) 
	{
		switch (arg1)
		{
		case R.id.good :	put = "Option 1 Selected";
							break;
		case R.id.better :	put = "Option 2 Selected";
							break;
		case R.id.best :	put = "Option 3 Selected";
							break;
		}
		showt.setText(put);
	}

	@Override
	public void onClick(View arg0) 
	{
		Intent i = new Intent();
		Bundle backPack = new Bundle();
		backPack.putString("Answer", put);
		i .putExtras(backPack);
		setResult(RESULT_OK, i);
		finish();
	}
	
	
}
