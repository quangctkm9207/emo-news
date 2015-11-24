package ss.passion.emonews;

import com.squareup.picasso.Picasso;

import ss.passion.emonews.adapter.MyPagerAdapter;
import ss.passion.emonews.utils.Variables;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDisplayActivity extends ActionBarActivity {

	private ActionBar aBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_display_activity);

		aBar = getSupportActionBar();
		aBar.setTitle(Variables.CATEGORIES[Variables.CATEGORY_ID]);
		aBar.setIcon(R.drawable.back_icon);
		aBar.setHomeButtonEnabled(true);

		Bundle bundle = getIntent().getExtras();
		int newsID = bundle.getInt(Variables.NEWS_ID);
		MyPagerAdapter adapter = new MyPagerAdapter(this);
		final ViewPager myPager = (ViewPager) findViewById(R.id.home_pannels_pager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(newsID);

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
