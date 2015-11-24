package ss.passion.emonews.adapter;

import ss.passion.emonews.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {
	String[] itemTitles;
	int[] itemIcons;
	Context context;

	public DrawerAdapter(Context _context, String[] titles, int[] icons) {
		context = _context;
		itemTitles = titles;
		itemIcons = icons;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemTitles.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemTitles[position];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.navigation_drawer_item,
					container, false);
		}
		ImageView ivIcon = (ImageView) convertView
				.findViewById(R.id.ivDrawerItem);
		TextView tvItem = (TextView) convertView
				.findViewById(R.id.tvDrawerItem);
		ivIcon.setBackgroundResource(itemIcons[position]);
		tvItem.setText(itemTitles[position]);

		return convertView;
	}

}
