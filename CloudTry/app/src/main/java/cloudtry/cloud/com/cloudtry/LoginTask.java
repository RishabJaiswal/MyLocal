package cloudtry.cloud.com.cloudtry;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Rishab on 24-03-2015.
 */
public class LoginTask extends AsyncTask<String, Void, String>
{
    Context context;
    public LoginTask(Context ct)
    {
        context = ct;
    }
    @Override
    protected String doInBackground(String... params)
    {
        String success = "N0";
        try{
            String username = params[0];
            String password = params[1];
            String email = params[2];
            //setting data for post method
            String link="http://localhost/AndroidDB/login.php";
            /*String data  = URLEncoder.encode("username", "utf-8")
                    + "=" + URLEncoder.encode(username, "utf-8");
            data += "&" + URLEncoder.encode("password", "utf-8")
                    + "=" + URLEncoder.encode(password, "utf-8");
            data += "&" + URLEncoder.encode("email", "utf-8")
                    + "=" + URLEncoder.encode(email, "utf-8");
            //setting URL and opening connection
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter
                    (conn.getOutputStream());
            wr.write(data);
            wr.flush();
            //reading data from
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            success = "YES";
            return success;*/

            //Using Volley
            RequestQueue rq = Volley.newRequestQueue(context);
            StringRequest sr = new StringRequest(Request.Method.POST, link,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            Toast.makeText(context, "Data saved", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(context, "Data not saved", Toast.LENGTH_LONG).show();
                        }
                    });
            sr.getHeaders().put("username", username);
            sr.getHeaders().put("password", password);
            sr.getHeaders().put("email", email);
            rq.add(sr);

            return success;

        }catch(Exception e){
            return success;
        }
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        //Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
