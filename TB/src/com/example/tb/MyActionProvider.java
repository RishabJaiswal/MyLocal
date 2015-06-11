package com.example.tb;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;

public abstract class MyActionProvider extends ActionProvider {
	Context mContext;
	public MyActionProvider(Context context)
	{
		super(context);
		mContext = context;
	}
	
	//public abstract View onCreateActionView();
	public View onCreateActionView()
	{
		//Context ct = getContext();
		LayoutInflater li = LayoutInflater.from(mContext);
		View view = li.inflate(R.layout.fragment2, null);
		//Button button = (Button)view.findViewById(R.id.fg2Button);
		//button.setText("ActionB");
		return view;
		
	}

	public boolean onPerformDefaultAction()
	{
		return true;
	}
	/*public View onCreateActionView(MenuItem mi)
	{
		//Context ct = getContext();
		LayoutInflater li = LayoutInflater.from(mContext);
		View view = li.inflate(R.layout.fragment2, null);
		//Button button = (Button)view.findViewById(R.id.fg2Button);
		//button.setText("ActionB");
		return view;
		
	}*/

}
