package ss.passion.emonews;

import ss.passion.emonews.utils.Variables;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FullNewsDisplayActivity extends ActionBarActivity implements
		OnRefreshListener {
	private WebView webView;
	private String link;
	private ActionBar aBar;
	private SwipeRefreshLayout mSwipeRefreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article);

		aBar = getSupportActionBar();
		aBar.setTitle("");
		aBar.setIcon(R.drawable.back_icon);
		aBar.setHomeButtonEnabled(true);

		getWidgets();
	}

	void getWidgets() {

		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorScheme(R.color.swipe_color_1,
				R.color.swipe_color_2, R.color.swipe_color_3,
				R.color.swipe_color_4);

		Bundle bundle = getIntent().getExtras();
		link = bundle.getString(Variables.NEWS_URL);

		webView = (WebView) findViewById(R.id.wbArticle);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		// webView.getSettings().setDisplayZoomControls(true);
		// webView.setInitialScale(1);
		// webView.getSettings().setLoadWithOverviewMode(true);
		// webView.getSettings().setUseWideViewPort(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(false);

		mSwipeRefreshLayout.setRefreshing(true);
		webView.setWebViewClient(new DuckyWebViewClient());

		webView.loadUrl(link);
		// new LoadLinkTask().execute();
	}

	// class LoadLinkTask extends AsyncTask<Void, Void, Void> {
	//
	// @Override
	// protected void onPreExecute() {
	// // TODO Auto-generated method stub
	// super.onPreExecute();
	//
	// mSwipeRefreshLayout.setRefreshing(true);
	// }
	//
	// @Override
	// protected Void doInBackground(Void... params) {
	//
	// return null;
	// }
	//
	// }

	class DuckyWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			mSwipeRefreshLayout.setRefreshing(false);
		}

	}

	@Override
	public void onRefresh() {
		mSwipeRefreshLayout.setRefreshing(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.fullnew, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.shareNews:
			Intent sharingIntent = new Intent(
					android.content.Intent.ACTION_SEND);
			sharingIntent
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_SUBJECT,
					"Đọc báo");
			sharingIntent
					.putExtra(
							Intent.EXTRA_TEXT,
							link);
			try {
				startActivity(Intent.createChooser(sharingIntent,
						"Chia sẻ"));
				// db.close();

			} catch (android.content.ActivityNotFoundException ex) {

			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
