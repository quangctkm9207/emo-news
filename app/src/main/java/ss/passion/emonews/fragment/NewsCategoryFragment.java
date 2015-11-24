package ss.passion.emonews.fragment;

import java.util.List;

import ss.passion.emonews.ChooseCategoryActivity;
import ss.passion.emonews.NewsListActivity;
import ss.passion.emonews.R;
import ss.passion.emonews.adapter.NewsCategoryAdapter;
import ss.passion.emonews.model.RssItem;
import ss.passion.emonews.utils.RssParser;
import ss.passion.emonews.utils.Utils;
import ss.passion.emonews.utils.Variables;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class NewsCategoryFragment extends Fragment implements OnRefreshListener {
	private GridView gvNewsCategories;
	private NewsCategoryAdapter adapter;
	private ProgressDialog dialog;

	private SwipeRefreshLayout mSwipeRefreshLayout;

	private Utils utils;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Configure the swipe refresh layout
		View view = inflater.inflate(R.layout.fragment_news_category,
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
		super.onStart();

		getWidgets();

	}

	void getWidgets() {
		utils = new Utils(getActivity());

		gvNewsCategories = (GridView) getView().findViewById(
				R.id.gvNewsCategory);
		if ((Variables.newsMap.get(Integer
				.parseInt(getCategories()[getCategories().length - 1])) == null)) {
			onRefresh();
		} else {
			adapter = new NewsCategoryAdapter(getActivity(), getCategories());
			gvNewsCategories.setAdapter(adapter);
		}

		gvNewsCategories.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {

				TextView tvId = (TextView) view.findViewById(R.id.tvId);
				Variables.CATEGORY_ID = Integer.parseInt(tvId.getText()
						.toString());
				Intent intent = new Intent(getActivity(),
						NewsListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

	}

	// Get all user's favorite categories
	public String[] getCategories() {
		String[] result = null;

		SharedPreferences pre = getActivity().getSharedPreferences(
				Variables.CATEGORY_PRE, 0);
		String categoryStr = pre.getString(Variables.CATEGORY_KEY, "0");
		result = categoryStr.trim().split(" ");
		return result;
	}

	// Using AsyncTask to get all RSS
	class GetItemsTask extends AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			// Start showing the refresh animation
			mSwipeRefreshLayout.setRefreshing(true);
			gvNewsCategories.setEnabled(false);
			// dialog = new ProgressDialog(getActivity());
			// dialog.setMessage("Waiting...");
			// dialog.setCancelable(false);
			// dialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {

			for (int i = 0; i < params.length; i++) {
				RssParser parser = new RssParser();
				List<RssItem> items = parser
						.getNewsList(Variables.LINKS[Integer
								.parseInt(params[i])][0]);
				Variables.newsMap.put(Integer.parseInt(params[i]), items);
				// if (isCancelled()) {
				// break;
				// }
			}
			adapter = new NewsCategoryAdapter(getActivity(), getCategories());

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// if (dialog.isShowing())
			// dialog.dismiss();

			gvNewsCategories.setEnabled(true);
			gvNewsCategories.setAdapter(adapter);
			mSwipeRefreshLayout.setRefreshing(false);

		}
	}

	@Override
	public void onRefresh() {
		if (utils.isNetworkOnline()) {
			// Simulate a long running activity
			new GetItemsTask().execute(getCategories());

		} else {
			mSwipeRefreshLayout.setRefreshing(false);
			Toast.makeText(getActivity(), "No internet connection!",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.action_add) {
			Intent intent = new Intent(getActivity(),
					ChooseCategoryActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("edit_category", true);
			getActivity().startActivity(intent);
			getActivity().overridePendingTransition(R.animator.activity_in,
					R.animator.activity_out);
			return true;
		}
		return super.onOptionsItemSelected(item);

	}

}