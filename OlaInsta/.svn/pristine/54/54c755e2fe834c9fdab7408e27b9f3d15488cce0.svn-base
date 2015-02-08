package com.ola.insta.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocationManager {

	private Location mNewLocation, mOldLocation;
	private LocationManager mLocationManager;
	private String mProvider;
	private Context mContext;
	private LocationCallback mLocationCallback;
	private static final int TIME_DIFFERENCE_THRESHOLD = 1 * 60 * 1000;
	private static final int DISTANCE_DIFFERENCE_THRESHOLD = 10;

	public MyLocationManager(Context context, LocationCallback callback) {

		this.mContext = context;
		this.mLocationCallback = callback;

		mProvider = getProviderName();
		mLocationManager.requestLocationUpdates(mProvider,
				DISTANCE_DIFFERENCE_THRESHOLD, TIME_DIFFERENCE_THRESHOLD,
				new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						// TODO Auto-generated method stub
						doWorkWithNewLocation(location);
						Toast.makeText(
								mContext,
								"Lat: " + location.getLatitude() + " Lon: "
										+ location.getLongitude(),
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
					}
				});

		mNewLocation = mLocationManager.getLastKnownLocation(mProvider);
		setOldLocation(mNewLocation);
		if (mNewLocation != null) {
			Log.i("Location",
					"Provider " + mProvider + " Lat: "
							+ mNewLocation.getLatitude() + " Lon: "
							+ mNewLocation.getLongitude());
			doWorkWithNewLocation(mNewLocation);
		} else
			Log.i("Location", "Provider " + mProvider + " null");
	}

	private void setNewLocation(Location location2) {
		// TODO Auto-generated method stub
		mNewLocation = location2;
	}

	/**
	 * Get provider name.
	 * 
	 * @return Name of best suiting provider.
	 * */
	String getProviderName() {
		mLocationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setPowerRequirement(Criteria.POWER_LOW); // Chose your desired
															// power consumption
															// level.
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy
														// requirement.
		criteria.setSpeedRequired(true); // Chose if speed for first location
											// fix is required.
		criteria.setAltitudeRequired(false); // Choose if you use altitude.
		criteria.setBearingRequired(false); // Choose if you use bearing.
		criteria.setCostAllowed(false); // Choose if this provider can waste
										// money :-)

		return mLocationManager.getBestProvider(criteria, true);
	}

	/**
	 * Make use of location after deciding if it is better than previous one.
	 * 
	 * @param location
	 *            Newly acquired location.
	 */
	void doWorkWithNewLocation(Location location) {
		if (isBetterLocation(getOldLocation(), location)) {
			setNewLocation(location);

			// If location is better, do some user preview.
			Toast.makeText(mContext, "Better location found: " + mProvider,
					Toast.LENGTH_SHORT).show();
		}
		mLocationCallback.onConnect(location);
		setOldLocation(location);
	}

	/**
	 * Decide if new location is better than older by following some basic
	 * criteria. This algorithm can be as simple or complicated as your needs
	 * dictate it. Try experimenting and get your best location strategy
	 * algorithm.
	 * 
	 * @param oldLocation
	 *            Old location used for comparison.
	 * @param newLocation
	 *            Newly acquired location compared to old one.
	 * @return If new location is more accurate and suits your criteria more
	 *         than the old one.
	 */
	boolean isBetterLocation(Location oldLocation, Location newLocation) {
		// If there is no old location, of course the new location is better.
		if (oldLocation == null) {
			return true;
		}

		// Check if new location is newer in time.
		boolean isNewer = newLocation.getTime() > oldLocation.getTime();

		// Check if new location more accurate. Accuracy is radius in meters, so
		// less is better.
		boolean isMoreAccurate = newLocation.getAccuracy() < oldLocation
				.getAccuracy();
		if (isMoreAccurate && isNewer) {
			// More accurate and newer is always better.
			return true;
		} else if (isMoreAccurate && !isNewer) {
			// More accurate but not newer can lead to bad fix because of user
			// movement.
			// Let us set a threshold for the maximum tolerance of time
			// difference.
			long timeDifference = newLocation.getTime() - oldLocation.getTime();

			// If time difference is not greater then allowed threshold we
			// accept it.
			if (timeDifference > -TIME_DIFFERENCE_THRESHOLD) {
				return true;
			}
		}

		return false;
	}

	private Location getOldLocation() {
		// TODO Auto-generated method stub
		return mOldLocation;
	}

	private void setOldLocation(Location location2) {
		// TODO Auto-generated method stub
		mOldLocation = location2;
	}

	private Location getNewLocation() {
		// TODO Auto-generated method stub
		return mNewLocation;
	}

	public interface LocationCallback {
		void onConnect(Location location);

		void onError();
	}
}
