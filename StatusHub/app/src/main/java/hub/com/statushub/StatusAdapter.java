package hub.com.statushub;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.ImageRequest;
import com.android.volley.request.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rishab on 24-10-2015.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusHolder> implements Filterable
{
    LayoutInflater li;
    Context cntxt;
    JSONArray origJsonArray, currJsonArray = new JSONArray();
    StatusFilter statusFilter;

    public StatusAdapter(final Context context, final ProgressBar pb)
    {
        cntxt = context;
        li = LayoutInflater.from(context);
        // fetching data
        String url = "https://tipstat.0x10.info/api/tipstat?type=json&query=list_status";
        JsonObjectRequest jsonReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            origJsonArray = response.getJSONArray("members");
                            currJsonArray = origJsonArray;
                            notifyDataSetChanged();
                            pb.setVisibility(View.GONE);
                            ((TextView)((Activity)cntxt).findViewById(R.id.totMemTv)).setText("Total members: " + origJsonArray.length());

                        } catch (JSONException e)
                        {
                            Snackbar.make(((Activity) cntxt).findViewById(R.id.coordIv), "Internal error occured", Snackbar.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Snackbar.make(((Activity)cntxt).findViewById(R.id.coordIv), "Internal error occured", Snackbar.LENGTH_LONG).show();
                    }
                });
        MySingleton.getInstance(context).addToRq(jsonReq);

        String url2 = "https://tipstat.0x10.info/api/tipstat?type=json&query=api_hits";
        JsonObjectRequest stringRequest = new JsonObjectRequest(url2, null,
                new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    ((TextView)((Activity)cntxt).findViewById(R.id.apiHitsTv)).setText("Api hits: " + response.getString("api_hits"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        MySingleton.getInstance(context).addToRq(stringRequest);
    }

    @Override
    public StatusHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = li.inflate(R.layout.people_item, null);
        StatusHolder holder = new StatusHolder(view, cntxt);
        return holder;
    }

    @Override
    public void onBindViewHolder(final StatusHolder holder, int position)
    {
        String url = null;
        String status = null;
        try {
            url = currJsonArray.getJSONObject(position).getString("image");
            status = currJsonArray.getJSONObject(position).getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.statusTv.setText(status);
        ImageRequest imgRequest = new ImageRequest(url, null, null,
                new Response.Listener<Bitmap>()
                {
                    @Override
                    public void onResponse(Bitmap response)
                    {
                        holder.pic.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener()
                {
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });
        MySingleton.getInstance(cntxt).addToRq(imgRequest);
    }

    @Override
    public int getItemCount()
    {
        return currJsonArray.length();
    }

    @Override
    public Filter getFilter()
    {
        if(statusFilter == null)
            statusFilter = new StatusFilter(this);
        return statusFilter;
    }
}
class StatusHolder extends RecyclerView.ViewHolder
{

    ImageView pic;
    TextView statusTv;
    public StatusHolder(View view, Context cntxt)
    {
        super(view);
        pic = (ImageView) view.findViewById(R.id.pic);
        statusTv = (TextView) view.findViewById(R.id.status);
        statusTv.setTypeface(Typeface.createFromAsset(cntxt.getAssets(), "fonts/Roboto-Bold.ttf"));
    }
}

