package ss.passion.emonews.utils;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationTracker implements LocationListener {
	private double longitude;
	private double latitude;
	private boolean gps_enabled, network_enabled;
	private LocationManager locationManager;
	private Context context;

	public LocationTracker(Context _context) {
		context = _context;
	}

	public Location getLocation() {
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, this);
		Location location = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		longitude = location.getLongitude();
		latitude = location.getLatitude();

		return location;
	}

	public String getLocationName() {
		String name = "";

		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		try {
			List<Address> addressList = geocoder.getFromLocation(latitude,
					longitude, 1);
			if (null != addressList && addressList.size() > 0) {
				name = addressList.get(0).getSubAdminArea();
				if(name.toLowerCase().contains("district")){
					name=name.replace("District", "");
					name="Quận "+name;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return name;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}


}
