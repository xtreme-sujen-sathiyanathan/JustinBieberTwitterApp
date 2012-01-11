package sujen.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.*;

public class JustinBieberTwitterApp extends Activity {
	List<BieberTweet> tweets = new ArrayList<BieberTweet>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.v("check","OK");
        try {

	        	createTweets(new GetTweetsTask().execute().get());
	        }
        catch(Exception e)
        {
        	Log.v("check",e.getMessage());
        }
        
        ListView lv = (ListView) this.findViewById(R.id.tweetLV);
      lv.setAdapter(new TweetArrayAdapter(this, R.layout.list_item, tweets));
    }
    
    public void createTweets(String response)
    {
    	try {
			JSONObject jsonObject = new JSONObject(response);
			JSONArray results = jsonObject.getJSONArray("results");
			BieberTweet bt;
			for(int i = 0; i < results.length();i++)
			{
				 bt = new BieberTweet(results.getJSONObject(i));
				 tweets.add(bt);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
    }
}