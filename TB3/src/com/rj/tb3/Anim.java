package com.rj.tb3;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
//import android.animation.LayoutTransition;

public class Anim extends Activity
{
	private View a1, a2, a3, a4, a5;
	private LinearLayout wh;
	//private LayoutTransition lt;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim);
		
		a1 = findViewById(R.id.a1);
		a2 = findViewById(R.id.a2);
		a3 = findViewById(R.id.a3);
		a4 = findViewById(R.id.a4);
		a5 = findViewById(R.id.a5);
		wh = (LinearLayout)findViewById(R.id.wh);
		
		a2.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				((TextView)a2).setText("Clicked");
			}
		});
		
		a1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				((TextView)a1).setText("Clicked");
			}
		});
		
		//lt = wh.getLayoutTransition();
		//lt.enableTransitionType(LayoutTransition.DISAPPEARING);
				
		objanimalp(a1);
		viewpropanimalp(a2);
		tapeanim();
		//animation();
	}
	
	private void objanimalp(final View v)   //Object Animator..just change the name of the property
	{
		ObjectAnimator o = ObjectAnimator.ofFloat(v, "translationX", -250, 0);
		ObjectAnimator o1 = ObjectAnimator.ofFloat(v, "x", -250, 0);
		
		o.setDuration(3500);
		o1.setDuration(3000);
		
		o.addUpdateListener(new AnimatorUpdateListener()   // update listener
		{
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) 
			{
				wh.invalidate();
			}
		});
		
		o.addListener(new AnimatorListenerAdapter()  // setting normal listener 
		{
			@Override
			public void onAnimationEnd(Animator arg0)
			{
				a1.setVisibility(View.GONE);
				wh.removeView(v);
			}
		});
		
		o.setInterpolator(new AccelerateDecelerateInterpolator());
		
		AnimatorSet as = new AnimatorSet();  //Animator set holding animations a1, a2
		as.playTogether(o, o1);
		as.start();
	}
	
	private void viewpropanimalp(final View v) // View property animator alpha...
	{
		animate(v).x(100f).setDuration(3000).setListener(new AnimatorListenerAdapter()
		{
			public void onAnimationEnd(Animator aeg0)
			{
				wh.removeView(v);
			}
		});
	}
	
	private void tapeanim()  // for tape restoring on their positions when activity starts..
	{
		AnimatorSet as = new AnimatorSet(); //Set of all tape animations
		List<Animator> list =  new ArrayList(); //for creating  a list of all animations , later passed to animator set
		
		int ids[] = {R.id.a3, R.id.a4, R.id.a5};
		ObjectAnimator[] objs = new ObjectAnimator[3];
		long i=0l ,delay=200l; // for adding delays
		
		int j=0;
		for (int id:ids)
		{
			objs[j] = ObjectAnimator.ofFloat(findViewById(id), "translationX", -100, 0);
			objs[j].setDuration(3000);
			objs[j].setStartDelay(i);
			list.add(objs[j]);
			i+=delay; //setting delay for next tape's animation
			j++;
		}
		as.playTogether(list);
		as.setInterpolator(new AccelerateDecelerateInterpolator());
		as.start();
		
	}

	/*private void animation()    // fade-in fade-out animations
	{
		pb= findViewById(R.id.spin);
		sc = findViewById(R.id.con);
		
		sc.setVisibility(View.VISIBLE);
		
		animate(sc).alpha(1f).setDuration(4000).setListener(null);
		animate(pb).alpha(0f).setDuration(4000).setListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationEnd(Animator arg0) 
			{
				pb.setVisibility(View.GONE);
			}
		});
	} */
}
