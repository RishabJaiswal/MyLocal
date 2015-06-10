package com.rj.tb3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

@SuppressLint("ClickableViewAccessibility")
public class GFXSurface extends Activity implements OnTouchListener
{
	MyViewS vs;
	float x, y, sx, sy, fx, fy;
	float dx, dy, scaledx, scaledy, animx, animy;
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		vs = new MyViewS(this);
		vs.setOnTouchListener(this);
		setContentView(vs);
		
		
	}
	@Override
	protected void onPause() 
	{
		super.onPause();
		vs.pause();
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		vs.resume();
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) 
	{
		try {
			Thread.sleep(16);      //Achieving Desired FPS
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		x = event.getX();
		y = event.getY();
		switch(event.getAction())
		{
		case(MotionEvent.ACTION_DOWN):
								sx = event.getX();
								sy = event.getY();
								dx=dy=animx=animy=scaledx=scaledy=fx=fy=0;
								break;
					
		case(MotionEvent.ACTION_UP):
								fx = event.getX();
								fy = event.getY();
								dx = fx-sx;
								dy = fy-sy;
								scaledx = dx/30;
								scaledy = dy/30;
								x=y=0;
								break;
		}
		return true;
	}
	
	public class MyViewS extends SurfaceView implements Runnable
	{
		SurfaceHolder sh;
		Thread th;
		boolean isRunning;
		Bitmap bmp, bmp1;
		Paint p;
		
		public MyViewS(Context context)
		{
			super(context);
			sh = getHolder();
			bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
			bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		
			p = new Paint();
			p.setARGB(100, 50, 50, 50);
			p.setTextAlign(Align.CENTER);
			p.setTextSize(50);
		}
		
		public void resume()
		{
			isRunning=true;
			th = new Thread(this);
			th.start();
		}
		
		public void pause()
		{
			isRunning = false;
			while(true)
			{
				try {
					th.join();
				} catch(InterruptedException e) {
					// TODO: handle exception
				}
				break;
			}
			th = null;
			
		}

		@Override
		public void run()
		{
			while(isRunning)
			{
				if(!sh.getSurface().isValid())
					continue;
			
				Canvas c = sh.lockCanvas();
				c.drawRGB(255,85,13);
				if(x != 0 && y!=0)
					c.drawBitmap(bmp, x-(bmp.getWidth()/2), y-(bmp.getHeight()/2), null); // drawing the ball
				
				if(sx != 0 && sy!=0)
					c.drawBitmap(bmp1, sx-(bmp1.getWidth()/2), sy-(bmp1.getHeight()/2), null); // drawing the ball
				
				if(fx != 0 && fy!=0)
				{
					c.drawBitmap(bmp, fx-(bmp.getWidth()/2)-animx, fy-(bmp.getHeight()/2)-animy, null); // drawing the ball
					c.drawBitmap(bmp1, fx-(bmp1.getWidth()/2), fy-(bmp1.getHeight()/2), null); // drawing the ball
				}
				animx = animx+scaledx;
				animy = animy+scaledy;
				sh.unlockCanvasAndPost(c);
			}
		}
		
		
	}
	
}
