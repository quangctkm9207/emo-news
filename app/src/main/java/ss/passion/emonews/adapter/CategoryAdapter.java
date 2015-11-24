package ss.passion.emonews.adapter;

import java.util.Arrays;

import ss.passion.emonews.R;
import ss.passion.emonews.utils.Variables;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
	String[] categoryList;
	Context context;

	public CategoryAdapter(Context _context, String[] _list) {
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
			convertView = inflater.inflate(R.layout.category_item, parent,
					false);
		}
		ImageView ivCategory = (ImageView) convertView
				.findViewById(R.id.ivCategory);
		TextView tvCategory = (TextView) convertView
				.findViewById(R.id.tvCategory);
		if(getCategories().length!=0){
			if (Arrays.asList(getCategories()).contains(String.valueOf(position))) {
				ivCategory
						.setBackgroundResource(Variables.CATEGORY_COLOR_IMAGES[position]);
			}else{
				ivCategory
				.setBackgroundResource(Variables.CATEGORY_BLACK_IMAGES[position]);
			}
		}else{
			ivCategory
			.setBackgroundResource(Variables.CATEGORY_BLACK_IMAGES[position]);
		}
		

		
		tvCategory.setText(Variables.CATEGORIES[position]);
		return convertView;
	}

	// Get all saved user's favorite categories
	public String[] getCategories() {
		String[] result = null;

		SharedPreferences pre = context.getSharedPreferences(
				Variables.CATEGORY_PRE, 0);
		String categoryStr = pre.getString(Variables.CATEGORY_KEY, "");
		result = categoryStr.trim().split(" ");
		return result;
	}

}
