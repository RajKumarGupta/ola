package com.ola.insta;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;
import com.ola.insta.common.Constants;
import com.ola.insta.common.Utilities;
import com.ola.insta.rest.model.CabAvailibilityResponse;
import com.ola.insta.rest.model.CabBookingResponse;
import com.ola.insta.rest.util.HttpUtil;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CabBookingActivity extends ActionBarActivity {

	private static final String TAG = "CabBooking";
	private ProgressDialog mProgressDialog;
	private Utilities mUtility;
	private String mCabBookingResponseJson;
	String mId = "34";
	String mLat = "77";
	String mLng = "66";
	String mAddress = "xyz";
	String mBookingTime = "3000";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUtility = new Utilities();
		mProgressDialog = mUtility.GetProcessDialog(this);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();

			if (Utilities.isNetworkAvailable(this)) {
				new GetCabBookingRequestTask().execute();
			} else {
				Utilities mUltilities = new Utilities();
				mUltilities.showDialogConfirm(CabBookingActivity.this,
						"Message", "Please check network connection", true)
						.show();
			}
		}
	}

	private class GetCabBookingRequestTask extends
			AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog.setMessage("Please Wait......");
			mProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> apiParams = new ArrayList<NameValuePair>(1);
			apiParams.add(new BasicNameValuePair("userId", mId));
			apiParams.add(new BasicNameValuePair("lat", mLat));
			apiParams.add(new BasicNameValuePair("lon", mLng));
			apiParams.add(new BasicNameValuePair("address", mAddress));
			apiParams.add(new BasicNameValuePair("bookingTime", mBookingTime));
			HttpUtil restAPIUtil = new HttpUtil(
					Constants.REST_URL_GET_CAB_BOOKING_REQUEST, "POST",
					apiParams);

			mCabBookingResponseJson = restAPIUtil.makeRESTCall();
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			if (mProgressDialog != null) {
				mProgressDialog.cancel();
			}
			Gson gson = new Gson();
			CabBookingResponse mCabBookingResponse = gson.fromJson(
					mCabBookingResponseJson, CabBookingResponse.class);
			Log.d(TAG,
					"CabBookingResponse: "
							+ mCabBookingResponse.getDriverName());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
