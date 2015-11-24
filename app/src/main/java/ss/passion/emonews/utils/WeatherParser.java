package ss.passion.emonews.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ss.passion.emonews.model.WeatherItem;

public class WeatherParser {

	public List<WeatherItem> getWeatherList(String link) {
		List<WeatherItem> items = new ArrayList<WeatherItem>();

		ServiceHandler handler = new ServiceHandler();
		// Making service and getting response from weather api
		String response = handler.makeServiceCall(link, ServiceHandler.POST);
		if (response != null) {
			try {
				String date = "";
				String temp = "";
				String temp_max = "";
				String temp_min = "";
				String humidity = "";
				String pressure = "";
				String wind = "";
				String sunset = "";
				String sunrise = "";
				String description = "";
				String icon="";
				JSONObject mainData = new JSONObject(response);
				JSONObject data = mainData.getJSONObject("data");
				JSONArray current_array = data
						.getJSONArray("current_condition");
				JSONObject current_data = current_array.getJSONObject(0);
				// date = "";
				temp = current_data.getString("temp_C");
				// temp_max = "";
				// temp_min = "";
				humidity = current_data.getString("humidity");

				pressure = current_data.getString("pressure");
				wind = current_data.getString("windspeedKmph");
				
				//Getting current description
				JSONArray descArray = current_data.getJSONArray("weatherDesc");
				description = descArray.getJSONObject(0).getString("value");
				
				//Getting current icon
				JSONArray iconArray = current_data.getJSONArray("weatherIconUrl");
				icon = iconArray.getJSONObject(0).getString("value");
				
				items.add(new WeatherItem(date, temp, temp_max, temp_min,
						humidity, pressure, wind, sunset, sunrise,
						description,icon));
				
				//Getting information for 5 days
				JSONArray weather_array = data.getJSONArray("weather");
				for (int i = 0; i < weather_array.length(); i++) {

					JSONObject object = weather_array.getJSONObject(i);
					date = object.getString("date");
					temp_max = object.getString("maxtempC");
					temp_min = object.getString("mintempC");
					
					// Getting sunset and sunrise time
					JSONArray object_astronomy = object
							.getJSONArray("astronomy");
					JSONObject objectAs = object_astronomy.getJSONObject(0);
					sunset = objectAs.getString("sunset");
					sunrise = objectAs.getString("sunrise");
					
					
					//Getting icon daily
					
					JSONArray array_hourly= object
							.getJSONArray("hourly");
					JSONObject object_hourly = array_hourly.getJSONObject(0);
					JSONArray array_weatherIconUrl= object_hourly
							.getJSONArray("weatherIconUrl");
					
					JSONObject object_Icon=array_weatherIconUrl.getJSONObject(0);
					icon=object_Icon.getString("value");

					items.add(new WeatherItem(date, temp, temp_max, temp_min,
							humidity, pressure, wind, sunset, sunrise,
							description,icon));
				}

			} catch (Exception e) {

			}

		}

		return items;
	}

}
