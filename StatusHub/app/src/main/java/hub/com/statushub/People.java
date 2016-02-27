package hub.com.statushub;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;

public class People extends AppCompatActivity
{
    RadioGroup rdGrp;
    SearchView searchView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    StatusAdapter statusAdapter;
    StatusFilter filter;
    TextView apiHits, totalMem;
    Button ethini;

    HashMap<String, String> eth = new HashMap<>();
    String[] eths = {"Asian", "Indian", "African Americans", "Asian Americans", "European", "British", "Jewish", "Latino",
                        "Native American", "Arabic", "All"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people);
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_people, menu);
        SearchManager searchManager = (SearchManager) 	getSystemService(Context.SEARCH_SERVICE);
        MenuItem mSearchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                filter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if (TextUtils.isEmpty(newText))
                {
                    statusAdapter.currJsonArray=statusAdapter.origJsonArray;
                    rdGrp.clearCheck();
                    statusAdapter.notifyDataSetChanged();
                }
                else
                {
                    filter.filter(newText);
                }
                return true;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed()
    {
        if(!statusAdapter.currJsonArray.equals(statusAdapter.origJsonArray))
        {
            statusAdapter.currJsonArray=statusAdapter.origJsonArray;
            rdGrp.clearCheck();
            statusAdapter.notifyDataSetChanged();
        }
        else
            finish();
    }

    private void initialize()
    {
        for(int i=0; i<10; i++)
            eth.put(String.valueOf(i), eths[i]);
        eth.put("10", "Ethnicity");

        progressBar = (ProgressBar) findViewById(R.id.dataFetchPb);
        apiHits = (TextView) findViewById(R.id.apiHitsTv);
        totalMem = (TextView) findViewById(R.id.totMemTv);
        AssetManager am = getAssets();
        apiHits.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Light.ttf"));
        totalMem.setTypeface(Typeface.createFromAsset(am, "fonts/Roboto-Light.ttf"));

        // toolbar and actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //button ethinic and AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
                .setSingleChoiceItems(eths, 10, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        rdGrp.clearCheck();
                        String str = String.valueOf(i);
                        dialogInterface.dismiss();
                        if(i<10)
                        {
                            filter.filter("ETH_" + str);
                        }
                        else
                        {
                            statusAdapter.currJsonArray = statusAdapter.origJsonArray;
                            statusAdapter.notifyDataSetChanged();
                        }

                        ethini.setText(eth.get(str));
                    }
                });
        final AlertDialog dialog = builder.create();
        ethini = (Button) findViewById(R.id.ethinicity);
        ethini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        //RadioGroup and its listener
        rdGrp = (RadioGroup) findViewById(R.id.radioGrp);
        rdGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                switch (i)
                {
                    case R.id.wtRadio:
                        filter.filter("sortListByWeight");
                        break;

                    case R.id.htRadio:
                        filter.filter("sortListByHeight");
                        break;
                }
            }
        });

        //RecyclerView and its adpater and filter
        recyclerView = (RecyclerView) findViewById(R.id.rcView);
        statusAdapter = new StatusAdapter(this, progressBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(statusAdapter);
        filter = (StatusFilter) statusAdapter.getFilter();
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(this,
                new RecyclerViewItemClickListener.OnItemClickListener()
                {
            @Override
            public void onItemClick(View view, int position)
            {
                try {
                    Intent i = new Intent(People.this, PeopleDetails.class);
                    String[] params = {"dob", "weight", "height", "is_veg", "drink", "status", "image"};
                    for(String param : params)
                        i.putExtra(param, statusAdapter.currJsonArray.getJSONObject(position).getString(param));
                    startActivity(i);
                }catch (Exception e)
                {
                    Snackbar.make(findViewById(R.id.ll2), "Internal error occurred", Snackbar.LENGTH_LONG).show();
                }
            }
        }));
    }
}
