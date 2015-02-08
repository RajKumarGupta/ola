package com.ola.insta;

import java.util.Locale;
import com.ola.insta.common.Constants;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CabDetailsActivity extends Activity implements TextToSpeech.OnInitListener {

	private static String mDriverMobile;
	private TextToSpeech mTextToSpeech;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Hide Actionbar
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_cab_details);
		mTextToSpeech = new TextToSpeech(this, this);

		if (savedInstanceState == null) {
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();           
			ft.replace(R.id.container, (Fragment)new PlaceholderFragment());
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cab_details, menu);
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

		private TextView driverNameTextView;
		private TextView driverMobileTextView;
		private TextView durationTextView;
		private TextView licenseNumberTextView;
		private TextView carModelTextView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_cab_details,
					container, false);

			driverNameTextView = (TextView) rootView
					.findViewById(R.id.driverNameValue);
			driverMobileTextView = (TextView) rootView
					.findViewById(R.id.driverMobileValue);
			durationTextView = (TextView) rootView.findViewById(R.id.durationValue);
			licenseNumberTextView = (TextView) rootView
					.findViewById(R.id.licenseNumberValue);
			carModelTextView = (TextView) rootView.findViewById(R.id.carModelValue);

			Intent intent = getActivity().getIntent();
			String driverName = intent.getStringExtra(Constants.DRIVER_NAME);
			String driverMobile = intent.getStringExtra(Constants.DRIVER_MOBILE);
			Long driverDuration = intent.getLongExtra(Constants.DRIVER_DURATION, 0);
			String carModel = intent.getStringExtra(Constants.DRIVER_CAR_MODEL);
			String carLicense = intent.getStringExtra(Constants.DRIVER_CAR_LICENSE);
			mDriverMobile = intent.getStringExtra(Constants.DRIVER_MOBILE);

			driverNameTextView.setText(driverName);
			driverMobileTextView.setText(driverMobile);
			durationTextView.setText(driverDuration+" Minutes");
			licenseNumberTextView.setText(carLicense);
			carModelTextView.setText(carModel);


			return rootView;
		}
	}

	public void onClickCallDriver(View view) {

		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+mDriverMobile));
		startActivity(callIntent);

	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = mTextToSpeech.setLanguage(Locale.US);
			// mTextToSpeech.setPitch(5); // set pitch level
			mTextToSpeech.setSpeechRate(0.8f); // set speech speed rate
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TextToSpeech", "Language is not supported");
			}
			speakOut("Congratulations! Your cab is booked successfully.");
		} else {
			Log.e("TextToSpeech", "Initilization Failed");
		}	
		
		
	}

	@Override
	public void onDestroy() {
		/*
		 *  Don't forget to shutdown!
		 */
		if (mTextToSpeech != null) {
			mTextToSpeech.stop();
			mTextToSpeech.shutdown();
		}
		super.onDestroy();
	}

	private void speakOut(String textToSpeak) {
		mTextToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
	}
}
