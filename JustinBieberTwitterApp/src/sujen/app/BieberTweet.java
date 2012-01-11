package sujen.app;

import org.json.JSONException;
import org.json.JSONObject;

public class BieberTweet {
	String text = "";
	String creationTime = "";
	String user = "";
	String profilePictureLink="";
	
	

	public BieberTweet(JSONObject tweetData)
	{
		try {
			this.text = tweetData.getString("text");
			this.creationTime = tweetData.getString("created_at");
			this.user = tweetData.getString("from_user");
			this.profilePictureLink = tweetData.getString("profile_image_url");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public BieberTweet()
	{
		
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getProfilePictureLink() {
		return profilePictureLink;
	}

	public void setProfilePictureLink(String profilePictureLink) {
		this.profilePictureLink = profilePictureLink;
	}
	
}
