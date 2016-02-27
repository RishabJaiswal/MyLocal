package com.zoomcar.car;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.ImageRequest;
import com.android.volley.request.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rishab on 12-09-2015.
 */
public class CarsListAdapter extends RecyclerView.Adapter<CarItemHolder> implements Filterable
{
    LayoutInflater li;
    Context context;
    public JSONArray cars = new JSONArray();
    public JSONObject apiJSONcars = null;
    CarFilter carFilter = null;

    public CarsListAdapter(final Context contxt)
    {
        context = contxt;
        li = LayoutInflater.from(contxt);

        //Request to fetch JSON
        String url = "https://zoomcar.0x10.info/api/zoomcar?type=json&query=list_cars";
        JsonObjectRequest carsJsonReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject jsonObject)
                    {
                        try {
                        apiJSONcars = jsonObject;
                            cars = apiJSONcars.getJSONArray("cars");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        notifyDataSetChanged();
                        ((TextView)((Activity)(context))
                                .findViewById(R.id.totalCars)).setText("Total cars : " + cars.length());

                        // request to get API hits
                        String url2="https://zoomcar.0x10.info/api/zoomcar?type=json&query=api_hits";
                        JsonObjectRequest apiHitReq = new JsonObjectRequest(url2, null,
                                new Response.Listener<JSONObject>()
                                {

                                    @Override
                                    public void onResponse(JSONObject apiHitsJson)
                                    {
                                        try {
                                            ((TextView)((Activity)(context))
                                                    .findViewById(R.id.apihits))
                                                    .setText("Api hits : " + apiHitsJson.getString("api_hits"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError)
                                    {
                                        Snackbar.make(((Activity)context)
                                                .findViewById(R.id.coord), "Unable to fetch Api hits",Snackbar.LENGTH_LONG).show();
                                    }
                                });
                        MySingleton.getInstance(context).addToRq(apiHitReq,context);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Snackbar.make(((Activity) context)
                            .findViewById(R.id.coord), "Internal error occured", Snackbar.LENGTH_LONG).show();
                    }
                });
        MySingleton.getInstance(context).addToRq(carsJsonReq,context);

    }
    @Override
    public CarItemHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = li.inflate(R.layout.car_item, null, false);
        view.setLayoutParams(lp);
        return new CarItemHolder(view, context);
    }

    @Override
    public void onBindViewHolder(CarItemHolder holder, int position)
    {
        JSONObject car;
        try {
            car = cars.getJSONObject(position);
            holder.carName.setText(car.getString("name"));
            holder.carHourRate.setText("Rate: "+car.getString("hourly_rate").replaceAll(",","")+"/"+"hour");
            holder.carRating.setRating(Float.parseFloat(car.getString("rating")));

                final ImageView parcelImg=holder.carImg;
                ImageRequest planImgRequest = new ImageRequest(car.getString("image"),null,null,
                        new Response.Listener<Bitmap>()
                        {
                            @Override
                            public void onResponse(Bitmap bitmap)
                            {
                                parcelImg.setImageBitmap(bitmap);
                            }
                        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                        new Response.ErrorListener()
                        {
                            public void onErrorResponse(VolleyError error)
                            {
                                //parcelImg.setImageResource(R.drawable.logo);
                            }
                        });
                MySingleton.getInstance(context).addToRq(planImgRequest,context);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return cars.length();
    }

    @Override
    public Filter getFilter()
    {
        if(carFilter == null)
            carFilter = new CarFilter(this);
        return carFilter;
    }
}

class CarItemHolder extends RecyclerView.ViewHolder
{
    TextView carName,carHourRate;
    AppCompatRatingBar carRating;
    ImageView carImg;
    public CarItemHolder(View itemView, Context cntxt)
    {
        super(itemView);
        carName = (TextView) itemView.findViewById(R.id.carName);
        carHourRate = (TextView) itemView.findViewById(R.id.hourlyRate);
        carRating = (AppCompatRatingBar) itemView.findViewById(R.id.carRating);
        carImg = (ImageView) itemView.findViewById(R.id.carImg);

        carName.setTypeface(Typeface.createFromAsset(cntxt.getAssets(), "fonts/" + "Roboto-Bold.ttf"));
        carHourRate.setTypeface(Typeface.createFromAsset(cntxt.getAssets(), "fonts/" + "Roboto-Medium.ttf"));
    }
}

