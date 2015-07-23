package org.gtri.helper;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.gtri.objects.User;

import android.util.Log;

public class DatabaseHandler {
	private static DefaultHttpClient client = new DefaultHttpClient();
	
	public static ArrayList<User> getFriends(String email) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
		pairs.add(new BasicNameValuePair("email",email));
		HttpResponse response = executeHttpPost("http://130.207.195.175:80/espn/getFriends.php", pairs);
		try {
			Log.v("test",EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static String addFriend(String email1, String email2) {
		  List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);  
			nameValuePairs.add(new BasicNameValuePair("email1", email1));
			nameValuePairs.add(new BasicNameValuePair("email2", email2));
			HttpResponse response = DatabaseHandler.executeHttpPost("http://130.207.195.175:80/espn/addFriend.php", nameValuePairs);
			String message = null;
			try {
				message = EntityUtils.toString(response.getEntity());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return message;
	}
	
	private static HttpResponse executeHttpPost(String uri, List<NameValuePair> nameValuePairs) {
		
		HttpResponse response = null;
		
		try {
			HttpPost post = new HttpPost(new URI(uri));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = client.execute(post);
		}
		
		catch (Exception exception) {
			Log.v("Error", exception.getMessage());
		}
		
		return response;
	}
}
