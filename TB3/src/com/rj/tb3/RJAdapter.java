package com.rj.tb3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class RJAdapter extends BaseAdapter
{
	Context mContext;
	public int icons[]={R.drawable.eat, 
						R.drawable.cook,
						R.drawable.meet,
						R.drawable.musicconcert,
						R.drawable.movie,
						R.drawable.shopping,
						R.drawable.study,
						R.drawable.travel,
						R.drawable.play,
						R.drawable.newyearseve,
						R.drawable.justthrowaparty};
	
	public RJAdapter(Context context)
	{
		mContext = context;
	}

	@Override
	public int getCount() 
	{
		return icons.length;
	}

	@Override
	public Object getItem(int position) 
	{
		return null;
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View counterView, ViewGroup arg2)
	{
		ImageView im = new ImageView(mContext);
		im.setImageResource(icons[position]);
		return im;
	}

}
