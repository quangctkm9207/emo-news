package ss.passion.emonews.fragment;

import ss.passion.emonews.R;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("NewApi")
public class NewsFragment extends Fragment{
	private WebView webView;
	private String link;
	private ProgressDialog dialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container,false);
		return view;
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Bundle bundle= getArguments();
		link = bundle.getString("url");
		
		webView=(WebView)getView().findViewById(R.id.wvNews);

		webView.getSettings().setSupportZoom(true);
		webView.setInitialScale(1);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(false);
		
		webView.setWebViewClient(new DuckyWebViewClient());
		dialog = ProgressDialog.show(getActivity(), "", "Loading....");
		new LoadLinkTask().execute();
	}
	class LoadLinkTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			webView.loadUrl(link);
			return null;
		}

	}

	class DuckyWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			if (dialog.isShowing())
				dialog.dismiss();
		}

	}
	
}
