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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class GetTweetsTask extends AsyncTask<Void, Void, String>{
	List<BieberTweet> tweets = new ArrayList<BieberTweet>();


	@Override
	protected String doInBackground(Void... params) {

		List<BieberTweet> tweets = new ArrayList<BieberTweet>();

		 HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response;
	        StatusLine statusLine;
	        try {
				response = httpclient.execute(new HttpGet("http://search.twitter.com/search.json?q=%23bieber&rpp=10&result_type=recent"));
				
				//Checks if status is OK
		        statusLine = response.getStatusLine();
		        Log.v("check"," got response");
		        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		        {
		        	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        	response.getEntity().writeTo(out);
		        	createTweets(out.toString());
		        	return "success";
		        }
		        
			} catch (ClientProtocolException e) {
				Log.v("check","client protocol exception");
				e.printStackTrace();
			} catch (IOException e) {
				Log.v("check","ioexception");
				e.printStackTrace();
			}
	        catch(Exception e)
	        {
	        	Log.v("check",e.getMessage());
	        }
			return "error";
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
