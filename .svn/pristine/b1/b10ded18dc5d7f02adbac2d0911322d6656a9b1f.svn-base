package org.gtri.helper;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.gtri.espn.activities.main.FanbookApplication;
import org.gtri.objects.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.facebook.android.FacebookError;
import com.facebook.android.Util;

public class FacebookHelper {

	/*
	 * Help extract information form facebook
	 */

	private FanbookApplication context;

	public FacebookHelper(FanbookApplication context) {
		this.context = context;
	}

	public String getName() {
		String _error = null;
		try {
			JSONObject json = Util.parseJson(context.getFacebook().request("me"));
			Log.v("mine", json.toString());
			String firstName = json.getString("first_name");
			String lastName = json.getString("last_name");
			return firstName + " " + lastName;
		} catch (Exception error) {
			_error = "Error retrieving data.";
		}
		if (_error != null) {
			Log.d("Facebook Login error", _error.toString());
		}
		return null;
	}
	
	public String getPicture() {
		String _error = null;
		Bundle params = new Bundle();
		params.putString("fields", "picture");
		try {
			JSONObject json = Util.parseJson(context.getFacebook().request("me", params));
			//Log.v("mine", json.toString());
			String pictureUrl = json.getJSONObject("picture").getJSONObject("data").getString("url");
			return pictureUrl;
		} catch (Exception error) {
			_error = "Error retrieving picture url.";
		}
		if (_error != null) {
			Log.d("Facebook Login error", _error.toString());
		}
		return null;
	}

	public ArrayList<User> getFriends() {
		ArrayList<User> list = new ArrayList<User>();
		Bundle params = new Bundle();
		params.putString( "fields", "name");
		params.putString("limit", "10");
		String _error = null;
		try {
			JSONObject json;
			try {
				json = Util.parseJson(context.getFacebook().request("me/friends", params));
				final JSONArray friends = json.getJSONArray("data");
				for (int i = 0; i < friends.length(); i++) {
					String name = friends.getJSONObject(i).getString("name");
					User temp = new User(name);
					list.add(temp);
				}
			} catch (MalformedURLException e) {
				_error = "JSON Error in response";
			} catch (IOException e) {
				_error = "JSON Error in response";
			}
		} catch (JSONException e) {
			_error = "JSON Error in response";
		} catch (FacebookError e) {
			_error = "Facebook Error: " + e.getMessage();
		}
		if (_error != null) {
			Log.d("Facebook Login error", _error.toString());
		}
		return list;
	}
	
	public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(new FlushedInputStream(is));
            bis.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
	
	static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0L;
            while (totalBytesSkipped < n) {
                long bytesSkipped = in.skip(n - totalBytesSkipped);
                if (bytesSkipped == 0L) {
                    int b = read();
                    if (b < 0) {
                        break; // we reached EOF
                    } else {
                        bytesSkipped = 1; // we read one byte
                    }
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }
}
