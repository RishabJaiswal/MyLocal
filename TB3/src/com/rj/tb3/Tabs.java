package com.rj.tb3;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener
{
	Drawable icon;
	TabHost th;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		icon = getResources().getDrawable(R.drawable.ic_launcher);
		Button addTab = (Button) findViewById(R.id.addtab);
		addTab.setOnClickListener(this);
		
		th= (TabHost) findViewById(R.id.tabhost);//getting  the TabHost from the xml
		th.setup();
		TabSpec spec = th.newTabSpec("tag1");//getting the tab specifications
		spec.setContent(R.id.tab1);
		spec.setIndicator("Tab 1", icon);
		th.addTab(spec);  //adding the tab to the TabHost
		
		TabSpec spec1 = th.newTabSpec("tag2");//getting the tab specifications
		spec1.setContent(R.id.tab2);
		spec1.setIndicator("Tab 2",icon);
		th.addTab(spec1); 
		
		TabSpec spec2 = th.newTabSpec("tag3");//getting the tab specifications
		spec2.setContent(R.id.tab3);
		spec2.setIndicator("Tab 3",icon);
		th.addTab(spec2);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		
		case R.id.addtab:
						TabSpec sp = th.newTabSpec("newTabTag");
						sp.setContent(new TabContentFactory() {
							
							@Override
							public View createTabContent(String arg0) 
							{
								TextView tv = new TextView(Tabs.this);
								tv.setText("You just created a new TAB");
								return tv;
							}
						});
						sp.setIndicator("New Tab", icon);
						th.addTab(sp);
						break;
		}
		
	}
	
	
	
}
