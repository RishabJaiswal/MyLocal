package com.example.tb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tb.PlaceholderFragment.Interaction;

public class TB2 extends ActionBarActivity implements Interaction  {
	public void interact() //Defining outside create()
	{
		TextView tv = (TextView)findViewById(R.id.TB2TextView);
		tv.setText("Fragment interaction");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_tb2);
		PlaceholderFragment fg = new PlaceholderFragment();
		FragmentRJ fg2 = new FragmentRJ();
		
		if (savedInstanceState == null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.add(R.id.container, fg);
			ft.addToBackStack("state1");
			ft.add(R.id.container, fg2);
			ft.add(R.id.container, new PlaceholderFragment());
			ft.commit();
			FragmentTransaction ft2 = fm.beginTransaction();
			ft2.hide(fg);
			ft2.addToBackStack(null);
			ft2.commit();
			interact();
		}
		ActionBar ab2 = getSupportActionBar(); //setting as up button
		ab2.setHomeButtonEnabled(true);
		ab2.setDisplayHomeAsUpEnabled(true);
		ab2.setDisplayShowHomeEnabled(true);
		//ab2.setDisplayOptions(1,ActionBar.DISPLAY_HOME_AS_UP);
		//ab2.setDisplayOptions(1,ActionBar.DISPLAY_SHOW_HOME);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.dbzmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
}

	/**
	 * A placeholder fragment containing a simple view.
	 */
class PlaceholderFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tb2, container, false);
			return rootView;
		}
		
		public static interface Interaction
		{
			public void interact();
		}
	}
	
class FragmentRJ extends Fragment {
		public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
			return inflater.inflate(R.layout.fragment2, container, false);
		}
	}
