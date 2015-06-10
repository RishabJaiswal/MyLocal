package com.rj.tb3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

public class Grid extends Activity
{

	Button grid, list;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);
		RJAdapter adap = new RJAdapter(this);
		grid = (Button)findViewById(R.id.grid);
		list = (Button)findViewById(R.id.list);
		
		final ListView myList = (ListView)findViewById(R.id.myList);
		final GridView myGrid = (GridView)findViewById(R.id.myGrid);
		myGrid.setAdapter(adap);
		myList.setAdapter(adap);
		
		grid.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				myList.setVisibility(View.GONE);
				myGrid.setVisibility(View.VISIBLE);
			}
		});
		
		list.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				myGrid.setVisibility(View.GONE);
				myList.setVisibility(View.VISIBLE);
			}
		});
	}
	
}
