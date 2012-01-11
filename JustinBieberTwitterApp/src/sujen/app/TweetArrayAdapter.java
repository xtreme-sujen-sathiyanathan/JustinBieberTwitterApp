package sujen.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetArrayAdapter extends ArrayAdapter<BieberTweet>{
	List<BieberTweet> tweets = new ArrayList<BieberTweet>();
	Context context;
	public TweetArrayAdapter(Context context, int textViewResourceId,
			List<BieberTweet> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.tweets = objects;
	}
	
	public int getCount()
	{
		return this.tweets.size();
	}
	
	public BieberTweet getItem(int index)
	{
		return this.tweets.get(index);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.list_item, parent, false);
		}

		// Get item
		BieberTweet tweet = getItem(position);
		
		// Get reference to ImageView 
		ImageView profilePicture = (ImageView) row.findViewById(R.id.profile_picture);
		
		// Get reference to TextView
		TextView user = (TextView) row.findViewById(R.id.user);
		
		// Get reference to TextView
		TextView text = (TextView) row.findViewById(R.id.text);


		TextView creationTime = (TextView) row.findViewById(R.id.creation_time);
		
		//Set stuff
		user.setText(tweet.getUser());
		text.setText(tweet.getText());
		creationTime.setText(tweet.getCreationTime());
		try {
			InputStream is = (InputStream) new URL(tweet.getProfilePictureLink()).getContent();
			Drawable drawable = Drawable.createFromStream(is,"src name");
			profilePicture.setImageDrawable(drawable);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// return row
		return row;
	}
}
