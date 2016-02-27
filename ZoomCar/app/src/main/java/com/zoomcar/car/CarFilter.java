package com.zoomcar.car;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Rishab on 12-09-2015.
 */
public class CarFilter extends Filter
{

    CarsListAdapter carsListAdapter;
    JSONArray initCars;

    public CarFilter(CarsListAdapter carsListAdapter)
    {
        this.carsListAdapter = carsListAdapter;
        try {
            initCars = carsListAdapter.apiJSONcars.getJSONArray("cars");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        FilterResults results = new FilterResults();
        try{

            if (TextUtils.isEmpty(constraint))
            {
                results.values = initCars;
                results.count = initCars.length();
            }
            else if(constraint.toString().startsWith("sortMyListBy"))
            {

                JSONArray sortedJSONArray = new JSONArray();
                List<JSONObject> jsonValues = new ArrayList();
                //initialising comparator to sort jsonValues
                Comparator<JSONObject> comparator = null;
                final String sortKey;
                if(constraint.toString().endsWith("Rate"))
                    comparator = new RateComparator("hourly_rate");
                else
                    comparator = new RatingComparator("rate");

                //getting only JSONvalues into a list
                for (int i = 0; i < initCars.length(); i++)
                    jsonValues.add(initCars.getJSONObject(i));
                //sorting the JSON values
                Collections.sort(jsonValues, comparator);
                //putting sorting values into JSONArray
                for(int i=0; i<jsonValues.size(); i++)
                    sortedJSONArray.put(jsonValues.get(i));
                results.values = sortedJSONArray;
                results.count = sortedJSONArray.length();
            }
            else
            {
                JSONObject car;
                JSONArray cars = new JSONArray();
                for (int i = 0; i < initCars.length(); i++)
                {
                    car = initCars.getJSONObject(i);
                    if (car.getString("name").toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        cars.put(car);
                    }
                }
                results.values = cars;
                results.count = cars.length();
            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results)
    {
        carsListAdapter.cars = (JSONArray)results.values;
        carsListAdapter.notifyDataSetChanged();
    }
}

class RateComparator implements Comparator<JSONObject>
{
    String sortKey;
    public RateComparator(String sortKey)
    {
        this.sortKey = sortKey;
    }
    @Override
    public int compare(JSONObject a, JSONObject b)
    {
        int price1=0, price2=0;
        try {
            price1 = Integer.parseInt(a.getString(sortKey).replaceAll(",", ""));
            price2 = Integer.parseInt(b.getString(sortKey).replaceAll(",", ""));
        } catch (Exception e)
        {
            Log.e("Error: ", e.toString());
        }
        if(price1 < price2)
            return -1;
        else if (price1 > price2)
            return 1;
        return 0;
    }
}
class RatingComparator implements Comparator<JSONObject>
{
    String sortKey;
    public RatingComparator(String sortKey)
    {
        this.sortKey = sortKey;
    }
    @Override
    public int compare(JSONObject a, JSONObject b)
    {
        float price1=0, price2=0;
        try {
            price1 = Float.parseFloat(a.getString(sortKey).replaceAll(",", ""));
            price2 = Float.parseFloat(b.getString(sortKey).replaceAll(",", ""));
        } catch (Exception e)
        {
            Log.e("Error: ", e.toString());
        }
        if(price1 < price2)
            return -1;
        else if (price1 > price2)
            return 1;
        return 0;
    }
}
