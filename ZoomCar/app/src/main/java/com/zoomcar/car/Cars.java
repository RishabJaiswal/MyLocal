package com.zoomcar.car;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

public class Cars extends AppCompatActivity
{
    RecyclerView carsList;
    Toolbar toolBar;
    SearchView searchView;
    CarsListAdapter cla;
    RadioGroup radioGroup;
    AppCompatRadioButton hourlyRateRad, ratingRad;
    AppCompatRatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars);
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_cars, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem mSearchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                cla.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if(TextUtils.isEmpty(newText))
                {
                    cla.getFilter().filter("");
                    radioGroup.clearCheck();
                }
                else
                    cla.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initialize()
    {
        //Rating bar
        ratingBar = (AppCompatRatingBar)findViewById(R.id.carRating);

        //Toolbar and setting app bar
        toolBar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        //RecyclerView and its adpater
        carsList = (RecyclerView)findViewById(R.id.carsList);
        cla = new CarsListAdapter(Cars.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        carsList.setLayoutManager(layoutManager);
        carsList.setAdapter(cla);
        carsList.addOnItemTouchListener(new RecyclerViewItemClickListener(this, new RecyclerViewItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                try {
                    JSONObject car = cla.cars.getJSONObject(position);
                    Intent i = new Intent(Cars.this, CarDetails.class);
                    String[] attrs ={"name", "type", "hourly_rate", "rating", "seater", "ac", "image"};
                    for (String attr : attrs)
                        i.putExtra(attr, cla.cars.getJSONObject(position).getString(attr));
                    i.putExtra("latitude", car.getJSONObject("location")
                            .getString("latitude"));
                    i.putExtra("longitude", car.getJSONObject("location")
                            .getString("longitude"));
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }));

        //Rating group and radio button
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        hourlyRateRad = (AppCompatRadioButton) findViewById(R.id.hourlyRateRad);
        ratingRad = (AppCompatRadioButton)findViewById(R.id.carRatingRad);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if(checkedId==R.id.hourlyRateRad)
                    cla.getFilter().filter("sortMyListByRate");
                else
                    cla.getFilter().filter("sortMyListByRating");
            }
        });

    }
}
