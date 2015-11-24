package ss.passion.emonews.model;

public class WeatherItem {
	private String date;
	private String temp;
	private String temp_max;
	private String temp_min;
	private String humidity;
	private String pressure;
	private String wind;
	private String sunset;
	private String sunrise;
	private String description;
	private String icon;

	public WeatherItem(String _date, String _temp, String _temp_max,
			String _temp_min, String _humidity, String _pressure, String _wind,
			String _sunset, String _sunrise,String _description,String _icon) {
		date = _date;
		temp = _temp;
		temp_max = _temp_max;
		temp_min = _temp_min;
		humidity = _humidity;
		pressure = _pressure;
		wind = _wind;
		sunset = _sunset;
		sunrise = _sunrise;
		description=_description;
		icon=_icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}

	public String getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

}
