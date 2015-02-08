package com.ola.insta.rest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class RestAPITask extends AsyncTask<String, String, String> {

	public static final String TAG = "RestAPITask";
	List<NameValuePair> postparams = new ArrayList<NameValuePair>();
	String URL = null;
	String method = null;
	static InputStream is = null;
	static String json = "";

	public RestAPITask(String url, String method, List<NameValuePair> params) {
		this.URL = url;
		this.postparams = params;
		this.method = method;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			if (method.equals("POST")) {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(URL);
				httpPost.setEntity(new UrlEncodedFormEntity(postparams));
				Log.d(TAG, "httpPost : " + httpPost);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} else if (method == "GET") {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils
						.format(postparams, "utf-8");
				URL += "?" + paramString;
				HttpGet httpGet = new HttpGet(URL);
				Log.d(TAG, "httpGet : " + httpGet);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		return json;

	}
}