package com.rj.tb3;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.InboxStyle;

public class MyService extends Service
{

	private IBinder ibr =  new LocalBinder();
	
	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return ibr;
	}
	
	public void serviceFunc()
	{
		NotificationManager nm;
		NotificationCompat.Builder noti;
		noti = (new NotificationCompat.Builder(this)).setContentTitle("Service has been started")
				.setContentText("Service Started").setSmallIcon(R.drawable.ic_launcher);
		nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(1, noti.build());
	}
	
	public class LocalBinder extends Binder
	{
		public MyService getService()
		{
			return MyService.this;
		}
	}
	
}
