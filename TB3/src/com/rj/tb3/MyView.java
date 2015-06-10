package com.rj.tb3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.view.View;

public class MyView extends View
{
	Bitmap ball;
	float y;
	//Typeface t;
	
	public MyView(Context context)
	{
		super(context);
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		y = 0;
		//t = Typeface.createFromAsset(context.getAssets(), "font.TTF");
	}


	@Override
	public void draw(Canvas canvas) 
	{
		super.draw(canvas);
		canvas.drawColor(Color.rgb(255,85,13));
		canvas.drawBitmap(ball,(canvas.getWidth()/2),y,null);
		if(y < canvas.getHeight())
			y += 10;
		else
			y=0;
		//Rectangle
		Rect rec = new Rect();
		rec.set(0, canvas.getHeight()/2, canvas.getWidth(), 3*(canvas.getHeight()/4));
		Paint paint = new Paint();
		paint.setColor(Color.rgb(50, 50, 50));
		canvas. drawRect(rec, paint);
		
		//Playing with text
		Paint p = new Paint();
		p.setARGB(100, 50, 50, 50);
		p.setTextAlign(Align.CENTER);
		p.setTextSize(50);
		//p.setTypeface(t);
		canvas.drawText("This is my TEXT", canvas.getWidth()/2, 52, p);
		
		//redrawing
		invalidate();
		
	}
	
	
}
