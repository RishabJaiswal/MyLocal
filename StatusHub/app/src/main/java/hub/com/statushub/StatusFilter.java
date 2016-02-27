package hub.com.statushub;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rishab on 24-10-2015.
 */
public class StatusFilter extends Filter
{
    StatusAdapter statusAdapter;
    public StatusFilter(StatusAdapter statusAdapter)
    {
        this.statusAdapter = statusAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence query)
    {
        String constraint = query.toString().trim();
        FilterResults results = new FilterResults();
        JSONArray resultJSONArray = new JSONArray();
        try {
        if(constraint.startsWith("sortListBy"))
        {
                List<JSONObject> jsonValues = new ArrayList();

                //initialising comparator to sort jsonValues
                String sortKey = null;
                if (constraint.endsWith("Weight"))
                    sortKey = "weight";
                else
                    sortKey = "height";
                Comparator<JSONObject> comparator = new HtWtComparator(sortKey);

                //getting only JSONvalues into a list
                for (int i = 0; i < statusAdapter.currJsonArray.length(); i++)
                    jsonValues.add(statusAdapter.currJsonArray.getJSONObject(i));

                //sorting the JSON values
                Collections.sort(jsonValues, comparator);

                //putting sorting values into JSONArray
                for (int i = 0; i < jsonValues.size(); i++)
                    resultJSONArray.put(jsonValues.get(i));
        }
        else
        {
            JSONObject jsonObject;
            if(TextUtils.isDigitsOnly(constraint))
            {
                for(int i=0; i<statusAdapter.origJsonArray.length(); i++)
                {
                    jsonObject = statusAdapter.origJsonArray.getJSONObject(i);
                    if(jsonObject.getString("weight").equals(constraint) || jsonObject.getString("height").equals(constraint))
                        resultJSONArray.put(jsonObject);
                }
            }
            else if(constraint.startsWith("ETH_"))
            {
                String e = constraint.replace("ETH_","");
                for(int i=0; i<statusAdapter.origJsonArray.length(); i++)
                {
                    jsonObject = statusAdapter.origJsonArray.getJSONObject(i);
                    if(jsonObject.getString("ethnicity").equals(e))
                        resultJSONArray.put(jsonObject);
                }
            }
            else
            {
                for(int i=0; i<statusAdapter.origJsonArray.length(); i++)
                {
                    jsonObject = statusAdapter.origJsonArray.getJSONObject(i);
                    if(jsonObject.getString("status").toLowerCase().contains(constraint.toLowerCase()))
                        resultJSONArray.put(jsonObject);
                }
            }
        }
        }catch (JSONException e){
            e.printStackTrace();
        }
        results.values = resultJSONArray;
        results.count = resultJSONArray.length();
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults)
    {
        statusAdapter.currJsonArray = (JSONArray) filterResults.values;
        statusAdapter.notifyDataSetChanged();
    }
}

class HtWtComparator implements Comparator<JSONObject>
{
    String sortKey;
    public HtWtComparator(String sortKey)
    {
        this.sortKey = sortKey;
    }
    @Override
    public int compare(JSONObject a, JSONObject b)
    {
        int htwt1 = 0, htwt2 = 0;
        try {
            htwt1 = Integer.parseInt(a.getString(sortKey).replaceAll(",", ""));
            htwt2 = Integer.parseInt(b.getString(sortKey).replaceAll(",", ""));
        } catch (Exception e) {
            Log.e("Error: ", e.toString());
        }
        if(htwt1 < htwt2)
            return  -1;
        else if(htwt1 > htwt2)
            return  1;
        return 0;
    }
}
