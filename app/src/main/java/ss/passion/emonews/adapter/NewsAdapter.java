package ss.passion.emonews.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import com.squareup.picasso.Picasso;

import ss.passion.emonews.R;
import ss.passion.emonews.model.RssItem;
import ss.passion.emonews.utils.Variables;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<RssItem> {
	private Context context;
	private List<RssItem> items;
	int ivNews;

	public NewsAdapter(Context context, int ivIcon, List<RssItem> objects) {
		super(context, ivIcon, objects);
		this.context = context;
		this.ivNews = ivIcon;
		this.items = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.news_item, parent, false);

		}
		ImageView ivIcon = (ImageView) convertView
				.findViewById(R.id.ivNewsIcon);
		TextView tvNews = (TextView) convertView.findViewById(R.id.tvNews);
		TextView tvTime=(TextView) convertView.findViewById(R.id.tvTime);
		TextView tvSource=(TextView)convertView.findViewById(R.id.tvSource);
		
		tvNews.setText(items.get(position).getTitle());
		tvTime.setText(items.get(position).getDate());
		tvSource.setText(items.get(position).getSource());
		
		
		String url = items.get(position).getImage();
		if((url!=null)&&(url!="")){
			url = url.replace("/100/100/", "/400/400/");
			Picasso.with(context).load(url).into(ivIcon);
		}else{
			// Load an default image
			
		}



		return convertView;
	}

}
