package ss.passion.emonews;

import ss.passion.emonews.adapter.NewsAdapter;
import ss.passion.emonews.utils.Variables;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ss.passion.emonews.R;
public class NewsListActivity extends ActionBarActivity {
	private ListView lvNews;
	private NewsAdapter adapter;
	private ActionBar aBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_news_list);
		// Set title for action bar

		getWidgets();
		aBar = getSupportActionBar();
		aBar.setTitle(Variables.CATEGORIES[Variables.CATEGORY_ID]);
		aBar.setIcon(R.drawable.back_icon);
		aBar.setHomeButtonEnabled(true);

	}

	void getWidgets() {
		lvNews = (ListView) findViewById(R.id.lvNews);
		NewsAdapter adapter = new NewsAdapter(this, R.id.ivNewsIcon,
				Variables.newsMap.get(Variables.CATEGORY_ID));
		lvNews.setAdapter(adapter);

		lvNews.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				// Show the article
				Intent intent = new Intent(NewsListActivity.this,
						NewsDisplayActivity.class);
				intent.putExtra(Variables.NEWS_ID, position);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				overridePendingTransition(R.animator.activity_in, R.animator.activity_out);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
