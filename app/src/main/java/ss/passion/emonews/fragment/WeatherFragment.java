package ss.passion.emonews.fragment;

import java.util.ArrayList;
import java.util.List;

import ss.passion.emonews.R;
import ss.passion.emonews.adapter.WeatherAdapter;
import ss.passion.emonews.model.WeatherItem;
import ss.passion.emonews.utils.LocationTracker;
import ss.passion.emonews.utils.Utils;
import ss.passion.emonews.utils.Variables;
import ss.passion.emonews.utils.WeatherParser;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class WeatherFragment extends Fragment implements OnRefreshListener {
	private TextView tvTemp, tvTempMax, tvTempMin;
	private TextView tvHumidity;
	private TextView tvPressure;
	private TextView tvSunset;
	private TextView tvSunrise;
	private TextView tvWind;
	private TextView tvDesc;
	private ImageView ivIcon;
	private ListView lvWeather;
	
	private LinearLayout llError;
	private LinearLayout llWeather;
	private WeatherAdapter adapter;
	List<WeatherItem> items = new ArrayList<WeatherItem>();
	private String url_start = "http://api.worldweatheronline.com/free/v2/weather.ashx?q=";
	private String url_end = "&format=json&num_of_days=5&fx24=no&tp=24&key=22ab0439b76ced62a784b541b2a85";
	private String fullUrl;
	String temp, tempMax, tempMin, humidity, pressure, sunset, sunrise, wind,
			desc, icon;

	private SwipeRefreshLayout mSwipeRefreshLayout;

	private Utils utils;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_weather_display,
				container, false);
		mSwipeRefreshLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.container);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorScheme(R.color.swipe_color_1,
				R.color.swipe_color_2, R.color.swipe_color_3,
				R.color.swipe_color_4);
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(true);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// Getting location
		LocationTracker tracker = new LocationTracker(getActivity());
		Location location = tracker.getLocation();

		// Passing longitude and latitude to URL
		fullUrl = url_start + location.getLatitude() + "%2C"
				+ location.getLongitude() + url_end;
		getWidgets();

	}

	private void getWidgets() {
		utils = new Utils(getActivity());
		
		llError=(LinearLayout)getView().findViewById(R.id.llError);
		llWeather=(LinearLayout)getView().findViewById(R.id.llWeather);
		
		
		tvTemp = (TextView) getActivity().findViewById(R.id.tvWeatherTemp);
		tvTempMax = (TextView) getActivity()
				.findViewById(R.id.tvWeatherTempMax);
		tvTempMin = (TextView) getActivity()
				.findViewById(R.id.tvWeatherTempMin);
		tvHumidity = (TextView) getActivity().findViewById(R.id.tvWeatherHumi);
		tvPressure = (TextView) getActivity().findViewById(
				R.id.tvWeatherPressure);
		tvWind = (TextView) getActivity().findViewById(R.id.tvWeatherWind);
		tvSunset = (TextView) getActivity().findViewById(R.id.tvWeatherSunset);

		tvSunrise = (TextView) getActivity()
				.findViewById(R.id.tvWeatherSunrise);
		tvDesc = (TextView) getActivity().findViewById(R.id.tvWeatherDesc);
		lvWeather = (ListView) getView().findViewById(R.id.lvWeather);
		ivIcon = (ImageView) getView().findViewById(R.id.ivWeatherIcon);
		if(Variables.weatherList.size()==0){
			onRefresh();
		}else{
			items=Variables.weatherList;
			WeatherItem current_data = items.get(0);
			temp = current_data.getTemp();
			tempMax = items.get(1).getTemp_max();
			tempMin = items.get(1).getTemp_min();
			humidity = current_data.getHumidity();
			pressure = current_data.getPressure();
			wind = current_data.getWind();
			sunrise = items.get(1).getSunrise();
			sunset = items.get(1).getSunset();
			desc = current_data.getDescription();
			icon = current_data.getIcon();

			adapter = new WeatherAdapter(getActivity(), items);
			
			tvTemp.setText(temp + "\u2103");
			tvTempMax.setText(tempMax + "\u00B0");
			tvTempMin.setText(tempMin + "\u00B0");
			tvHumidity.setText(humidity + "%");
			tvPressure.setText(pressure + "hPa");
			tvWind.setText(wind + " Kmph");
			tvSunrise.setText(sunrise);
			tvSunset.setText(sunset);
			tvDesc.setText(desc);
			Picasso.with(getActivity()).load(icon).into(ivIcon);

			lvWeather.setAdapter(adapter);

			// Setting background based on weather current condition
			setBackgroundWeather(desc);
			
		}
		

	}

	class GetWeatherData extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mSwipeRefreshLayout.setRefreshing(true);
			llWeather.setVisibility(View.GONE);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			WeatherParser parser = new WeatherParser();
			
			Variables.weatherList=parser.getWeatherList(fullUrl);
			items=Variables.weatherList;
			
			WeatherItem current_data = items.get(0);
			temp = current_data.getTemp();
			tempMax = items.get(1).getTemp_max();
			tempMin = items.get(1).getTemp_min();
			humidity = current_data.getHumidity();
			pressure = current_data.getPressure();
			wind = current_data.getWind();
			sunrise = items.get(1).getSunrise();
			sunset = items.get(1).getSunset();
			desc = current_data.getDescription();
			icon = current_data.getIcon();

			// Remove current information item from list
			items.remove(0);
			adapter = new WeatherAdapter(getActivity(), items);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			llWeather.setVisibility(View.VISIBLE);
			mSwipeRefreshLayout.setRefreshing(false);
			tvTemp.setText(temp + "\u2103");
			tvTempMax.setText(tempMax + "\u00B0");
			tvTempMin.setText(tempMin + "\u00B0");
			tvHumidity.setText(humidity + "%");
			tvPressure.setText(pressure + "hPa");
			tvWind.setText(wind + " Kmph");
			tvSunrise.setText(sunrise);
			tvSunset.setText(sunset);
			tvDesc.setText(desc);
			Picasso.with(getActivity()).load(icon).into(ivIcon);

			lvWeather.setAdapter(adapter);

			// Setting background based on weather current condition
			setBackgroundWeather(desc);

		}
	}

	@Override
	public void onRefresh() {
		if (utils.isNetworkOnline()) {
			// Simulate a long running activity
			showNormalView();
			new GetWeatherData().execute();

		} else {
			showError();
			mSwipeRefreshLayout.setRefreshing(false);
			Toast.makeText(getActivity(), "No internet connection!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void setBackgroundWeather(String desc) {

		for (int i = 0; i < Variables.WEATHER_CHECK.length; i++) {
			if (desc.toLowerCase().contains(
					Variables.WEATHER_CHECK[i].toLowerCase())) {
				mSwipeRefreshLayout
						.setBackgroundResource(Variables.WEATHER_BACK[i]);
			}
		}

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.action_add) {
			/////////
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}
	public void showError(){
		mSwipeRefreshLayout.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
		llError.setVisibility(View.VISIBLE);
		llWeather.setVisibility(View.GONE);
	}
	public void showNormalView(){
		
		llError.setVisibility(View.GONE);
		llWeather.setVisibility(View.VISIBLE);
	}

}
