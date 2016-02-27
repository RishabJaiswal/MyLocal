package hub.com.statushub;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Rishab on 24-10-2015.
 */
public class MySingleton
{
    private static MySingleton instance;
    private RequestQueue rq;
    private static Context cntxt;
    private MySingleton(Context context)
    {
        cntxt = context;
        rq = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context)
    {
        if(instance==null)
            instance = new MySingleton(context);
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if(rq == null)
            rq = Volley.newRequestQueue(cntxt.getApplicationContext());
        return rq;
    }

    public <T> void addToRq(Request<T> req)
    {
        getRequestQueue().add(req);
    }

    public <T> void addToRq(Request<T> req, Object tag)
    {
        req.setTag(tag);
        getRequestQueue().add(req);
    }
}

