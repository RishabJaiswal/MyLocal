package com.rj.tb3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TB3 extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contain);
		Frag fg1 = new Frag();
		Frag fg2 = new Frag();
		ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ab.addTab(ab.newTab().setText("Tab1").setTabListener(new Listener(fg1)));
		ab.addTab(ab.newTab().setText("Tab2").setTabListener(new Listener(fg2)));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tb3, menu);
		return true;
	}
}

class Listener implements TabListener
{
	Fragment fragment;
	private int counter;
	public Listener(Fragment fragment)
	{
		this.fragment = fragment;
		counter = 0;
	}
	@Override
	public void onTabSelected(ActionBar.Tab tab,FragmentTransaction fragmentTransaction) 
	{		
		if (counter == 0)
			fragmentTransaction.add(R.id.container, fragment, null);
		else
			fragmentTransaction.attach(fragment);
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{
		fragmentTransaction.detach(fragment);
		counter=1;
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{
		//Generally, this is method is empty...
		counter=1;
	}
	
}

class Frag extends Fragment
{
	private int ctr=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//super.onCreateView(inflater,container,savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment, container, false);
		final Button b = (Button) rootView.findViewById(R.id.clickButton);
		b.setText("You have clicked me " + ctr + " times");
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ctr++;
				b.setText("You have clicked me " + ctr + " times");
			}
		});
		final Button b1 = (Button)rootView.findViewById(R.id.next);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Class<?> clzz = Class.forName("com.rj.t3.MENU"); 
				Intent i = new Intent("com.rj.tb3.MENU");
				startActivity(i);
			}
		});
		return rootView;
	}
}
