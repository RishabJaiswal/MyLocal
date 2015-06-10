package com.example.tb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class TB extends ActionBarActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tb);

		// ArrayAdapter and ListView
		  String[] a={"d","a","I am RJ","TB","SPACEflight","AmazingWorld"};
		  String b="I am adding something using add()";
		  ListView host=(ListView)findViewById(R.id.ListViewid); 
		  ArrayAdapter<String> bridge = new ArrayAdapter<String>(this,R.layout.forlistview,R.id.listViewTextView);
		  bridge.add(b);
		  bridge.add("Adding");
		  //bridge.clear();
		  //ArrayAdapter<CharSequence> bridge = ArrayAdapter.createFromResource(this, R.array.ListViewArray, com.example.tb.R.layout.activity_tb);
		  host.setAdapter(bridge);
		  //public void clear() of ArrrayAdapter to clear all items in the array;

		// ViewStub
		  //ViewStub stub = (ViewStub)findViewById(R.id.stub);
		  //stub.setVisibility(View.VISIBLE); //(or use) View inflated = stub.inflate();
		 
	    //Spinner
		  Spinner SpinnerHost = (Spinner)findViewById(R.id.spinner);
		  ArrayAdapter<String> SpinnerBridge = new ArrayAdapter<String>(this,R.layout.forlistview,R.id.listViewTextView, a);
		  SpinnerHost.setAdapter(SpinnerBridge);
		  
		//GridView
		  //GridView GridViewHost = (GridView)findViewById(R.id.gridview);
		  //ArrayAdapter<String> bridge2 = new ArrayAdapter<String>(this,R.layout.for_grid_view,R.id.imageView,a);
		  //GridViewHost.setAdapter(bridge2);
		  
		//ActionBar Up button an display 
		  ActionBar ab = getSupportActionBar();
		  ab.setDisplayHomeAsUpEnabled(true);// set icon as up(home) button
		  ab.setDisplayShowHomeEnabled(true);//Home(icon) missing
		  //ab.setDisplayShowHomeEnabled(true);// showing action bar icon
		  //ab.setDisplayShowTitleEnabled(true);// not showing action bar icon
		  //ab.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
		  //ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
		//Setting title and subtitle
			ab.setTitle("Whatsapp Name");
			ab.setSubtitle("Whatsapp time");
		  
		//Open Second Activity from first
		   TextView button=(TextView)findViewById(R.id.TextView1);
	       button.setOnClickListener(new View.OnClickListener() 
	       {
	    	   @Override
	           public void onClick(View v) 
	    	   {
	    		   Intent i = new Intent(getApplicationContext(),TB2.class);
	    		   startActivity(i);
	    	   }
	       });
	}
	
	@Override //creating options menu
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dbzmenu, menu);
		
		//Getting the linked actionView
		MenuItem mi = menu.findItem(R.id.item4);
		Button AVButton = (AV) MenuItemCompat.getActionView(mi);
		AVButton.setText("Button2");
		
		//Listener when action view expands/collapse
		MenuItemCompat.setOnActionExpandListener(mi, new OnActionExpandListener()
		{
			public boolean onMenuItemActionCollapse(MenuItem item) 
			{
		        EditText et = (EditText)findViewById(R.id.View1id);
		        et.setText("Action View Collapsed");
		        return true;  // Return true to collapse action view
		    }

		    public boolean onMenuItemActionExpand(MenuItem item) 
		    {
		    	EditText et = (EditText)findViewById(R.id.View1id);
		        et.setText("Action View Expanded");
		    	return true;  // Return true to expand action view
		    }
		});
		
		//Getting the linked ActionProvider and setting the intent
		/*final ShareActionProvider shp;
		MenuItem mi2 = menu.getItem(R.id.item2);
		shp = (ShareActionProvider)MenuItemCompat.getActionProvider(mi2);
		shp.setShareIntent(null); */
		return super.onCreateOptionsMenu(menu);
	}
}
	                