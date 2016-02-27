package hub.com.statushub;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.ImageRequest;

import java.util.Scanner;

public class PeopleDetails extends AppCompatActivity
{
    TextView birthdayTv, wtTv, htTv, isVegTv, drinksTv, statusTv, statusTxt;
    ImageView pic;
    String picUrl = null;
    FloatingActionButton fab;
    DrawableTinting tinter;
    Intent i;
    String isVegStr = null;
    String drinksStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_details);
        initialize();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void initialize()
    {
        tinter = new DrawableTinting(this);
        //fab
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(tinter.tint(R.drawable.share, Color.WHITE));

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TextViews and setting typeface
        birthdayTv = (TextView) findViewById(R.id.birthday);
        wtTv = (TextView) findViewById(R.id.wt);
        htTv = (TextView) findViewById(R.id.ht);
        isVegTv = (TextView) findViewById(R.id.isVeg);
        drinksTv = (TextView) findViewById(R.id.drink);
        statusTv = (TextView) findViewById(R.id.status);
        statusTxt = (TextView) findViewById(R.id.statusTxt);
        pic = (ImageView) findViewById(R.id.pic);
        AssetManager am = getAssets();
        birthdayTv.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Medium.ttf"));
        wtTv.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Medium.ttf"));
        htTv.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Medium.ttf"));
        isVegTv.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Bold.ttf"));
        drinksTv.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Bold.ttf"));
        statusTv.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Bold.ttf"));
        statusTxt.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Light.ttf"));
        //setting color
        isVegTv.setCompoundDrawablesWithIntrinsicBounds(tinter.tint(R.drawable.veg, Color.GREEN), null, null, null);
        drinksTv.setCompoundDrawablesWithIntrinsicBounds(tinter.tint(R.drawable.drink, Color.RED), null, null ,null);


        //Getting intent and setting textViews
        i = getIntent();
        String birthday = i.getStringExtra("dob");
        final String ht = i.getStringExtra("height");
        final String wt = i.getStringExtra("weight");
        String isVeg = i.getStringExtra("is_veg");
        final String drinks = i.getStringExtra("drink");
        final String status = i.getStringExtra("status");
        final String d, m ,y;

        //birthday, ht, wt, and status TextView
        Scanner sc = new Scanner(birthday);
        sc.useDelimiter("-");
        y = sc.next();  m = sc.next()+"/"; d = sc.next()+"/";
        birthdayTv.setText(d+m+y);
        wtTv.setText((Integer.parseInt(wt)/1000)+" kgs");
        htTv.setText(ht + " cm");
        statusTv.setText(status);

        //isVeg TextView
        if(isVeg != null)
        {
            if(isVeg.equals("1"))
                isVegStr = "Yes";
            else
                isVegStr = "No";
            isVegTv.setText(isVegStr);
        }

        //drinks TextView
        if(drinks != null)
        {
            if(drinks.equals("1"))
                drinksStr = "Yes";
            else
                drinksStr = "No";
            drinksTv.setText(drinksStr);
        }

        //profile pic and fething image
        picUrl = i.getStringExtra("image");
        ImageRequest imgRequest = new ImageRequest(picUrl, null, null,
                new Response.Listener<Bitmap>()
                {
                    @Override
                    public void onResponse(Bitmap response)
                    {
                        pic.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener()
                {
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });
        MySingleton.getInstance(this).addToRq(imgRequest);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, " Date of birth: " + d + m + y +
                        "\nHeight: " + ht + "cm" +
                        "\nWeight :" + (Integer.parseInt(wt) / 1000) + " kgs" +
                        "\nIs Veg: " + isVegStr +
                        "\nDrinks: " + drinksStr +
                        "\nStatus: " + status);
                startActivity(Intent.createChooser(i, "Share via"));

            }
        });
    }

}
