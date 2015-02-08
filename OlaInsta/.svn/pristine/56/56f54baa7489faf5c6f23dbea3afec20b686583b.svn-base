package com.ola.insta.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import com.ola.insta.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;

public class Utilities {
	private static Bitmap bgimage;
	private static Bitmap CallImage;
	private static Uri uri;
	private static int time;
	private static final String TAG = "Utilities";

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Utilities.time = time;
	}

	public static Uri getUri() {
		return uri;
	}

	public static void setUri(Uri uri) {
		Utilities.uri = uri;
	}

	public static Bitmap getCallImage() {
		return CallImage;
	}

	public static void setCallImage(Bitmap callImage) {
		CallImage = callImage;
	}

	public static Bitmap getBgimage() {
		return bgimage;

	}

	public static void setBgimage(Bitmap bgimage) {
		Utilities.bgimage = bgimage;
	}

	public static Bitmap getBitmapFromFile(File filePath) {
		Bitmap myBitmap;
		myBitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath());
		return myBitmap;
	}

	public static List<Activity> getCleanuplist() {
		return cleanupList;
	}

	public Utilities() {

	}

	public static final List<Activity> cleanupList = new ArrayList<Activity>();

	/**
	 * Gets the process dialog.
	 * 
	 * @param activity
	 *            the activity
	 * @return the progress dialog
	 */
	public ProgressDialog GetProcessDialog(Activity activity) {
		// prepare the dialog box
		ProgressDialog dialog = new ProgressDialog(activity);
		// make the progress bar cancel-able
		dialog.setCancelable(false);
		// set a message text
		dialog.setMessage("Please wait...");

		// show it
		return dialog;
	}

	/**
	 * Gets the resized bitmap.
	 * 
	 * @param bm
	 *            the bm
	 * @param newHeight
	 *            the new height
	 * @param newWidth
	 *            the new width
	 * @return the resized bitmap
	 */

	@SuppressWarnings("deprecation")
	public AlertDialog showDialogConfirm(final Activity activity, String title,
			String message, final boolean flag) {
		AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
		// activity.getWindow().setBackgroundDrawableResource(R.color.orange);
		// alertDialog.setIcon(R.drawable.dialog_icon);
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.setCancelable(false);
		return alertDialog;
	}

	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

		if (bm == null)
			return null;
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);
		// RECREATE THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	/**
	 * M lock screen rotation.
	 * 
	 * @param activity
	 *            the activity
	 */
	public void mLockScreenRotation(Activity activity) {
		// Stop the screen orientation changing during an event
		switch (activity.getResources().getConfiguration().orientation) {
		case Configuration.ORIENTATION_PORTRAIT:
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		case Configuration.ORIENTATION_LANDSCAPE:
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;
		}
	}

	/**
	 * Show progress dialog.
	 * 
	 * @param mContext
	 *            the m context
	 * @return the dialog
	 */
	public Dialog showProgressDialog(Activity mContext) {
		Dialog mDialog = new Dialog(mContext,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		LayoutInflater mInflater = LayoutInflater.from(mContext);
		View layout = mInflater.inflate(R.layout.popup_bg, null);
		mDialog.setContentView(layout);

		mDialog.setCancelable(false);
		// aiImage.post(new Starter(activityIndicator));
		return mDialog;
	}

	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager connectivity = null;
		boolean isNetworkAvail = false;

		try {
			connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivity != null) {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();

				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {

							return true;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connectivity != null) {
				connectivity = null;
			}
		}
		return isNetworkAvail;
	}

	public boolean isSdcardAvailable() {
		boolean flag = android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		return flag;
	}

	public String capitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	public static boolean isInternetAvalible(Context context) {
		final ConnectivityManager conMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
		if (activeNetwork != null
				&& activeNetwork.getState() == NetworkInfo.State.CONNECTED) {
			System.out.println("Connected...");
			return true;
		}
		return false;
	}

	public static void cleanUpActivity() {
		for (Activity activity : cleanupList) {
			activity.finish();
		}
		cleanupList.clear();
	}

	public static String convertURL(String str) {

		String url = null;
		try {
			url = new String(str.trim().replace(" ", "%20").replace("&", "%26")
					.replace(",", "%2c").replace("(", "%28")
					.replace(")", "%29").replace("!", "%21")
					.replace("=", "%3D").replace("<", "%3C")
					.replace(">", "%3E").replace("#", "%23")
					.replace("$", "%24").replace("'", "%27")
					.replace("*", "%2A").replace("-", "%2D")
					.replace(".", "%2E").replace("/", "%2F")
					.replace(":", "%3A").replace(";", "%3B")
					.replace("?", "%3F").replace("@", "%40")
					.replace("[", "%5B").replace("\\", "%5C")
					.replace("]", "%5D").replace("_", "%5F")
					.replace("`", "%60").replace("{", "%7B")
					.replace("|", "%7C").replace("}", "%7D"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String callhttpRequest(String url) {
		System.out.println("utility url..." + url);
		String resp = null;
		HttpGet httpRequest;
		try {
			httpRequest = new HttpGet(url);
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 60000;
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);
			int timeoutSocket = 60000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			HttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpResponse response = httpClient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			final long contentLength = bufHttpEntity.getContentLength();
			if ((contentLength >= 0)) {
				InputStream is = bufHttpEntity.getContent();
				int tobeRead = is.available();
				System.out.println("Utility callhttpRequest tobeRead.."
						+ tobeRead);
				ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
				int ch;

				while ((ch = is.read()) != -1) {
					bytestream.write(ch);
				}

				resp = new String(bytestream.toByteArray());
				System.out.println("Utility callhttpRequest resp.." + resp);
			}
		} catch (MalformedURLException e) {
			System.out.println("Utility callhttpRequest.." + e);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.out.println("Utility callhttpRequest.." + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Utility callhttpRequest.." + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Utility Exception.." + e);
		}
		return resp;
	}

	/**
	 * Do post.
	 * 
	 * @param url
	 *            the url
	 * @param kvPairs
	 *            the kv pairs
	 * @return the http response
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static HttpResponse doPost(String url, Map<String, String> kvPairs)
			throws ClientProtocolException, IOException {
		Log.d(TAG, "doPost  url " + url);
		Log.d(TAG, "doPost  kvPairs " + kvPairs);

		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		HttpClient httpclient = defaultHttpClient;
		Log.d(TAG, "doPost  httpclient " + httpclient);
		HttpPost httppost = new HttpPost(url);
		Log.d(TAG, "doPost  httppost " + httppost);

		if (kvPairs != null || kvPairs.isEmpty() == false) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					kvPairs.size());
			String k, v;
			Iterator<String> itKeys = kvPairs.keySet().iterator();

			while (itKeys.hasNext()) {
				k = itKeys.next();
				v = kvPairs.get(k);

				Log.d(TAG, "doPost  key  " + k);
				Log.d(TAG, "doPost  value  " + v);
				nameValuePairs.add(new BasicNameValuePair(k, v));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			Log.d(TAG, "doPost  httppost  " + httppost);
		}

		HttpResponse response;
		response = httpclient.execute(httppost);
		Log.i("TAG", "doPost response........." + response);
		return response;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static int getWidth(Context mContext) {
		int width = 0;
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= 13) {
			Point size = new Point();
			display.getSize(size);
			width = size.x;
		} else {
			width = display.getWidth(); // deprecated
		}
		return width;
	}

	@SuppressLint("NewApi")
	public static int getHeight(Context mContext) {
		int height = 0;
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= 13) {
			Point size = new Point();
			display.getSize(size);
			height = size.y;
		} else {
			height = display.getHeight(); // deprecated
		}
		return height;
	}

}
