package ss.passion.emonews.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import com.squareup.picasso.Picasso;

import ss.passion.emonews.R;
import ss.passion.emonews.model.RssItem;
import ss.passion.emonews.utils.RssParser;
import ss.passion.emonews.utils.Variables;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsCategoryAdapter extends BaseAdapter {

	String[] categoryList;
	Context context;
	ImageView ivCategory;

	public NewsCategoryAdapter(Context _context, String[] _list) {
		this.categoryList = _list;
		this.context = _context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categoryList.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categoryList[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.news_category_item, parent,
					false);
		}
		ivCategory = (ImageView) convertView.findViewById(R.id.ivNewsCategory);
		TextView tvCategory = (TextView) convertView
				.findViewById(R.id.tvNewsCategory);
		TextView tvId = (TextView) convertView.findViewById(R.id.tvId);

		tvCategory.setText(Variables.CATEGORIES[Integer
				.parseInt(categoryList[position])]);
		tvId.setText(categoryList[position]);

		String url = Variables.newsMap
				.get(Integer.parseInt(categoryList[position])).get(0)
				.getImage();
		if ((url != null) && (url != "")) {
			url = url.replace("/100/100/", "/400/400/");
			Picasso.with(context).load(url).into(ivCategory);
		} else {
			// Load an default image

		}

		return convertView;
	}

}
