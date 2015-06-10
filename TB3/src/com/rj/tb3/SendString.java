package com.rj.tb3;

import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rj.tb3.MyService.LocalBinder;

public class SendString extends FragmentActivity implements OnClickListener
{
	private EditText name;
	private Button sa, safr, dia;
	String name1;
	Bundle basket = new Bundle();
	TextView answer;
	Builder builder1, builder3, builder5;
	NotificationManager nm;
	NotificationCompat.Builder noti;
	InboxStyle ibx;
	MyService serv; // Service;
	Boolean bound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		
		noti = (new NotificationCompat.Builder(this)).setContentTitle("The String")
											.setContentText("The string has been sent").setSmallIcon(R.drawable.ic_launcher);
		ibx = new InboxStyle(); 
		ibx.setBigContentTitle("The string in inbox style");
		ibx.addLine("This is notification in Inbox Style");
		nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
		 //Creating AlertDialog
		Builder builder = new Builder(SendString.this);
		builder1 = builder.setMessage("Opening Dialog")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() 
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
			});
		
		//Adding a list in Alert Dialog
		Builder builder2 = new Builder(SendString.this);
		CharSequence items[] = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
		builder3 = builder2.setTitle("Choose an item")
				.setItems(items, new DialogInterface.OnClickListener() 
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) 
					{
						// TODO Auto-generated method stub
						
					}
				});
		
		//Adding multiple choices
		Builder builder4 = new Builder(SendString.this);
		CharSequence items1[] = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
		builder5 = builder4.setTitle("Choose an item")
				.setMultiChoiceItems(items1, null, new DialogInterface.OnMultiChoiceClickListener() 
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
						// TODO Auto-generated method stub
						
					}
				})
				.setPositiveButton("OK", new DialogInterface.OnClickListener() 
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
				{
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				});
	}
	
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.bSA:	
						name1 = name.getText().toString();
						basket.putString("Name", name1);
						try{
						//Class<?> clzz = Class.forName("com.tb3.GetString");
						Intent i =  new Intent(SendString.this, GetString.class);
						i.putExtras(basket);
						startActivity(i);
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
		case R.id.bSAFR:
						Intent i =  new Intent(SendString.this, GetString.class);
						startActivityForResult(i, 0);
						ibx.addLine("This notification is from SAFR button");
						noti.setStyle(ibx);
						nm.notify(1, noti.build());
						break;
		
		case R.id.dialog:
						builder1.create().show();
						builder3.show();
						builder5.show();
						ibx.addLine("This is notification is from Dialog button");
						noti.setStyle(ibx);
						nm.notify(2, noti.build());
						break;
		}
		
	}
	
	
	
	@Override
	protected void onStart()
	{
		super.onStart();
		bind();
	}
	

	@Override
	protected void onStop() 
	{
		super.onStop();
		unbindService(sc);
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)
			{
				String ans = data.getExtras().getString("Answer");
				answer.setText("Answer: " + ans);
			}
	}

	private void initialize()
	{
		name = (EditText) findViewById(R.id.enterName);
		sa = (Button) findViewById(R.id.bSA);
		safr = (Button) findViewById(R.id.bSAFR);
		sa.setOnClickListener(this);
		safr.setOnClickListener(this);
		answer =  (TextView)findViewById(R.id.answer);
		dia = (Button) findViewById(R.id.dialog);
		dia.setOnClickListener(this);
	}
	
	private void bind()
	{
		Intent i =  new Intent(SendString.this, MyService.class);
		bindService(i, sc, Context.BIND_AUTO_CREATE);
	}
	
	ServiceConnection sc = new ServiceConnection()
	{
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) 
		{
			bound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder ib) 
		{
			bound = true;
			LocalBinder lb = (LocalBinder) ib;
			serv = lb.getService();
			serv.serviceFunc();
		}
	};

}