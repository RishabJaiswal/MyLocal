package com.zoomcar.car;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.ImageRequest;
import com.android.volley.request.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by Rishab on 12-09-2015.
 */
public class CarDetails extends AppCompatActivity implements View.OnClickListener
{
    MapView map;
    GoogleMap googleMap;
    TextView vac, vseater, vcarName, vrate, vtype;
    String ac, seater, carName, rate, rating, type, carImageUrl, latitude, longitude;
    ImageView carImg;
    AppCompatRatingBar ratingBar;
    FloatingActionButton currLocFab, shareFab, bookFab;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_details);

        map = (MapView)findViewById(R.id.map);
        map.onCreate(savedInstanceState);
        MapsInitializer.initialize(this);

        initialize();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        map.onLowMemory();
    }

    private void initialize()
    {
        datePicker = new DatePicker(this);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) {
            datePicker.setCalendarViewShown(false);
            datePicker.setMinDate(System.currentTimeMillis() - 1000);
        }
        //getting all views
        googleMap = map.getMap();
        vac = (TextView)findViewById(R.id.ac);
        vseater = (TextView) findViewById(R.id.seater);
        vcarName = (TextView) findViewById(R.id.carName);
        vrate = (TextView)findViewById(R.id.rate);
        vtype = (TextView)findViewById(R.id.type);
        carImg = (ImageView)findViewById(R.id.carImage);
        ratingBar = (AppCompatRatingBar)findViewById(R.id.carRating);
        currLocFab = (FloatingActionButton)findViewById(R.id.currLocFab);
        shareFab = (FloatingActionButton)findViewById(R.id.shareFab);
        bookFab = (FloatingActionButton)findViewById(R.id.bookFab);

        //setting fab listener
        currLocFab.setOnClickListener(this);
        shareFab.setOnClickListener(this);
        bookFab.setOnClickListener(this);

        //Getting all data from the intent
        Intent i = getIntent();
        ac = i.getStringExtra("ac");
        seater = i.getStringExtra("seater");
        carName = i.getStringExtra("name");
        rate = i.getStringExtra("hourly_rate");
        rating = i.getStringExtra("rating");
        type = i.getStringExtra("type");
        carImageUrl = i.getStringExtra("image");
        latitude = i.getStringExtra("latitude");
        longitude = i.getStringExtra("longitude");

        //binding textView and other views with data
        if(Integer.parseInt(ac)>0)
            vac.setText("AC");
        else
            vac.setText("No AC");
        vseater.setText(seater);
        vcarName.setText(carName);
        vrate.setText("Rate: "+rate+"/hour");
        vtype.setText(type);
        ratingBar.setRating(Float.parseFloat(rating));

        //adding marker on the map
        LatLng curr = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        googleMap.addMarker(new MarkerOptions()
                .position(curr)
                .title("Parcel"));
        //animating camera
        CameraUpdate newcamera = CameraUpdateFactory.newLatLngZoom(curr, 6f);
        googleMap.animateCamera(newcamera);

        //setting imagee request
        ImageRequest planImgRequest = new ImageRequest(carImageUrl,null,null,
                new Response.Listener<Bitmap>()
                {
                    @Override
                    public void onResponse(Bitmap bitmap)
                    {
                        carImg.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener()
                {
                    public void onErrorResponse(VolleyError error)
                    {
                        //carImage.setImageResource(R.drawable.next);
                    }
                });
        MySingleton.getInstance(CarDetails.this).addToRq(planImgRequest, this);

        //setting typface
        vcarName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + "Roboto-Regular.ttf"));
        vrate.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + "Roboto-Medium.ttf"));
        vtype.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + "Roboto-Bold.ttf"));
        vac.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + "Roboto-Bold.ttf"));
        vseater.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/" + "Roboto-Bold.ttf"));

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.shareFab: Intent i = new Intent();
                                i.setAction(Intent.ACTION_SEND);
                                String msg = "Car name: "+ carName +
                                        ", type: "+ type +
                                        ", "+seater+ "Seater, "+
                                        "Rate: "+rate +"/hour, "+ "Rating: "+rating+"/5.0, ";
                                if(Integer.parseInt(ac)>0)
                                    msg+="AC";
                                else
                                    msg+="No AC";
                                i.putExtra(Intent.EXTRA_TEXT, msg);
                                i.setType("text/plain");
                                startActivity(Intent.createChooser(i, "Share car details..."));
                                break;

            case R.id.bookFab:
                                final AlertDialog.Builder builder = new AlertDialog.Builder(CarDetails.this, R.style.MyAlertDialogStyle);
                                builder.setTitle("Pick up date");
                                builder.setMessage("Date when you will pick your car");
                                builder.setView(datePicker);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int d = datePicker.getDayOfMonth();
                                        int m = datePicker.getMonth();
                                        int y = datePicker.getYear();
                                        Snackbar.make(findViewById(R.id.coord), "Date :" + d + "/" + m + "/" + y, Snackbar.LENGTH_LONG).show();
                                    }
                                });
                                builder.setNegativeButton("Cancel", null);
                                builder.show();
                                break;
            case R.id.currLocFab:

                String url = "https://zoomcar.0x10.info/api/zoomcar?type=json&query=list_cars";
                JsonObjectRequest carsReq = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject jsonObject)
                            {
                                try {
                                    JSONArray cars = jsonObject.getJSONArray("parcels");
                                    JSONObject car=null;
                                    for(int i=0; i<cars.length(); i++)
                                    {
                                        car = cars.getJSONObject(i);
                                        if(car.getString("name").equals(carName))
                                        {
                                            LatLng curr = new LatLng(car.getJSONObject("location").getDouble("latitude"),
                                                    car.getJSONObject("location").getDouble("longitude"));
                                            googleMap.clear();
                                            googleMap.addMarker(new MarkerOptions()
                                                    .position(curr)
                                                    .title("Parcel"));
                                            CameraUpdate newcamera = CameraUpdateFactory.newLatLngZoom(curr, 6f);
                                            googleMap.animateCamera(newcamera);
                                        }
                                    }
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
                                Toast.makeText(CarDetails.this, "Internal error occured", Toast.LENGTH_SHORT).show();
                            }
                        });
                MySingleton.getInstance(this).addToRq(carsReq, this);
        }

    }
}
