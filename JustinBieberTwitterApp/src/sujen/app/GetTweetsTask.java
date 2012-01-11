package sujen.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class GetTweetsTask extends AsyncTask<Void, Void, String>{


	@Override
	protected String doInBackground(Void... params) {

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
		        	return out.toString();
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
		return "Error";
	}

}
