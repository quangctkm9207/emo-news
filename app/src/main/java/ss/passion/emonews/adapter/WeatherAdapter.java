package ss.passion.emonews.adapter;

import java.util.List;

import com.squareup.picasso.Picasso;

import ss.passion.emonews.R;
import ss.passion.emonews.model.WeatherItem;
import ss.passion.emonews.utils.Utils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {
	private List<WeatherItem> weatherItems;
	private Context context;

	public WeatherAdapter(Context _context, List<WeatherItem> _list) {
		context = _context;
		weatherItems = _list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return weatherItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return weatherItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		WeatherItem item = weatherItems.get(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.weather_item, container,
					false);
		}
		TextView tvDate = (TextView) convertView
				.findViewById(R.id.tvWeatherDate);
		TextView tvMax = (TextView) convertView.findViewById(R.id.tvWeatherMax);
		TextView tvMin = (TextView) convertView.findViewById(R.id.tvWeatherMin);
		ImageView ivIcon=(ImageView)convertView.findViewById(R.id.ivWeatherImage);
		
		Utils utils= new Utils(context);
		
		String dayOfWeek = utils.getDayOfWeek(item.getDate());
		tvDate.setText(dayOfWeek);
		tvMax.setText(item.getTemp_max()+"\u00B0");
		tvMin.setText(item.getTemp_min()+"\u00B0");
		
		Picasso.with(context).load(item.getIcon()).into(ivIcon);

		return convertView;
	}

}
