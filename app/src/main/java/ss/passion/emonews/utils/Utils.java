package ss.passion.emonews.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
	private Context context;

	public Utils(Context _context) {
		context = _context;
	}

	/*
	 * Check network connection Return network status (connected=true;
	 * unconnected=false)
	 */
	public boolean isNetworkOnline() {
		boolean status = false;
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getNetworkInfo(0);
			if (netInfo != null
					&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			} else {
				netInfo = cm.getNetworkInfo(1);
				if (netInfo != null
						&& netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return status;

	}

	public String getDayOfWeek(String dateString) {
		String result = "";
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-M-dd").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			result = "Chủ nhật";
			break;
		case 2:
			result = "Thứ hai";
			break;
		case 3:
			result = "Thứ ba";
			break;
		case 4:
			result = "Thứ tư";
			break;
		case 5:
			result = "Thứ năm";
			break;
		case 6:
			result = "Thứ sáu";
			break;
		case 7:
			result = "Thứ bảy";
			break;

		default:
			break;
		}
		return result;
	}

}
