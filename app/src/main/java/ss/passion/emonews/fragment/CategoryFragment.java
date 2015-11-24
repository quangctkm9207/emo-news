package ss.passion.emonews.fragment;

import java.util.Arrays;
import java.util.HashMap;

import ss.passion.emonews.MainScreenActivity;
import ss.passion.emonews.R;
import ss.passion.emonews.adapter.CategoryAdapter;
import ss.passion.emonews.utils.Variables;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CategoryFragment extends Fragment {
	private GridView gvCategory;
	private CategoryAdapter adapter;
	HashMap<Integer, Boolean> categoryMap = new HashMap<Integer, Boolean>();
	boolean checked = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choose_category,
				container, false);
		return view;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void onStart() {
		super.onStart();
		initializeMap();
		if (isEditMode()) {
			// Show saved status of user's settings
			getCurrentMap();
		}
		getWidgets();

	}

	void getWidgets() {
		gvCategory = (GridView) getView().findViewById(R.id.gvCategory);
		adapter = new CategoryAdapter(getActivity(), Variables.CATEGORIES);
		gvCategory.setAdapter(adapter);
		gvCategory
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int position, long arg3) {
						ImageView ivCategory = (ImageView) view
								.findViewById(R.id.ivCategory);
						TextView tvCategory = (TextView) view
								.findViewById(R.id.tvCategory);

						if (categoryMap.get(position)) {
							ivCategory
									.setBackgroundResource(Variables.CATEGORY_BLACK_IMAGES[position]);
							checked = false;
							tvCategory.setBackgroundDrawable(new ColorDrawable(
									Color.parseColor("#80000000")));
							tvCategory.setTextColor(Color.parseColor("#ffffff"));
						} else {
							ivCategory
									.setBackgroundResource(Variables.CATEGORY_COLOR_IMAGES[position]);
							checked = true;
							tvCategory.setBackgroundDrawable(new ColorDrawable(
									Color.parseColor("#90ffffff")));
							tvCategory.setTextColor(Color.parseColor("#000000"));
						}
						categoryMap.put(position, checked);
					}
				});
	}

	// Set all categories unchecked
	void initializeMap() {

		for (int i = 0; i < Variables.CATEGORIES.length; i++) {
			categoryMap.put(i, false);
		}
	}

	// Save choosen categories
	public void saveCategory() {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < Variables.CATEGORIES.length; i++) {
			if (categoryMap.get(i)) {
				sBuilder.append(" " + i);
			}
		}
		// Toast.makeText(getActivity(), sBuilder.toString(),
		// Toast.LENGTH_SHORT).show();

		SharedPreferences pre = getActivity().getSharedPreferences(
				Variables.CATEGORY_PRE, 0);
		SharedPreferences.Editor editor = pre.edit();
		editor.putString(Variables.CATEGORY_KEY, sBuilder.toString());
		editor.commit();
	}

	public boolean isEditMode() {
		return getActivity().getIntent()
				.getBooleanExtra("edit_category", false);
	}

	public void getCurrentMap() {
		for (int i = 0; i < Variables.CATEGORIES.length; i++) {
			if (Arrays.asList(getCategories()).contains(String.valueOf(i))) {
				categoryMap.put(i, true);
			} else
				categoryMap.put(i, false);
		}
	}

	// Get all saved user's favorite categories
	public String[] getCategories() {
		String[] result = null;

		SharedPreferences pre = getActivity().getSharedPreferences(
				Variables.CATEGORY_PRE, 0);
		String categoryStr = pre.getString(Variables.CATEGORY_KEY, "0");
		result = categoryStr.trim().split(" ");
		return result;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.action_done){
			saveCategory();
			Variables.newsMap.clear();
			
			getActivity().finish();
			Intent intent = new Intent(getActivity(),
					MainScreenActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}

}
