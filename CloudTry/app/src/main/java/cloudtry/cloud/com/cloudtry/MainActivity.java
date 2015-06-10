package cloudtry.cloud.com.cloudtry;

import android.content.Context;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity
{

    EditText email, userName, pass, confPass;
    Button reg;
    String eid, uname, password, conpass;
    Pair<Context, String> pairs[] = new Pair[3];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Rishab"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize()
    {
        email = (EditText)findViewById(R.id.emailId);
        userName = (EditText)findViewById(R.id.userName);
        pass = (EditText)findViewById(R.id.password);
        confPass = (EditText)findViewById(R.id.confPass);
        reg = (Button)findViewById(R.id.register);

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                eid = email.getText().toString();
                uname = userName.getText().toString();
                password = pass.getText().toString();
                conpass = confPass.getText().toString();
                //new LoginTask(MainActivity.this).execute(uname,password, eid);
                String link="http://10.0.2.2/AndroidDB/login.php";

                StringRequest req = new StringRequest(Request.Method.POST ,link,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                Toast.makeText(MainActivity.this, "Data saved and " + response.toString(), Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                )
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("username", uname);
                        params.put("password", password);
                        params.put("email", eid);
                        return params;
                    }
                };
                MySingleton.getInstance(MainActivity.this).addToRq(req);
            }
        });
    }
}