package ss.passion.emonews.adapter;

import ss.passion.emonews.FullNewsDisplayActivity;
import ss.passion.emonews.R;
import ss.passion.emonews.utils.Variables;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyPagerAdapter extends PagerAdapter {

	private Context context;

	private TextView tvTitle;
	private TextView tvSourceTime;
	private TextView tvDesc;
	private ImageView ivNewsImage;
	private Button btViewfull;

	public MyPagerAdapter(Context _context) {
		context = _context;

	}

	// State number of pages
	public int getCount() {
		return Variables.newsMap.get(Variables.CATEGORY_ID).size();
	}

	// Set each screen's content
	@Override
	public Object instantiateItem(View container, int position) {
		final Context context = container.getContext();
		LinearLayout layout = new LinearLayout(context);
		LayoutInflater li = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) li.inflate(R.layout.news_display, null);

		getWidgets(layout);
		setData(position);
		((ViewPager) container).addView(layout, 0);
		return layout;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	void getWidgets(LinearLayout layout) {
		tvTitle = (TextView) layout.findViewById(R.id.tvNewsTitle);
		tvSourceTime = (TextView) layout.findViewById(R.id.tvNewsSourceTime);
		tvDesc = (TextView) layout.findViewById(R.id.tvNewsDesc);
		ivNewsImage = (ImageView) layout.findViewById(R.id.ivNewsImage);

		btViewfull = (Button) layout.findViewById(R.id.btViewFull);

	}

	void setData(final int newsID) {

		String title = Variables.newsMap.get(Variables.CATEGORY_ID).get(newsID)
				.getTitle();
		String source = Variables.newsMap.get(Variables.CATEGORY_ID)
				.get(newsID).getSource();
		String time = Variables.newsMap.get(Variables.CATEGORY_ID).get(newsID)
				.getDate();
		String desc = Variables.newsMap.get(Variables.CATEGORY_ID).get(newsID)
				.getDescription();
		String imageUrl = Variables.newsMap.get(Variables.CATEGORY_ID)
				.get(newsID).getImage();

		tvTitle.setText(title);
		tvSourceTime.setText(source + " / " + time);
		tvDesc.setText(desc);

		if ((imageUrl != null) && (imageUrl != "")) {
			imageUrl = imageUrl.replace("/100/100/", "/400/400/");
			Picasso.with(context).load(imageUrl).into(ivNewsImage);
		} else {
			// Load an default image

		}

		btViewfull.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// View full news in WebView
				Intent intent = new Intent(context,
						FullNewsDisplayActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(Variables.NEWS_URL,
						Variables.newsMap.get(Variables.CATEGORY_ID)
								.get(newsID).getLink());
				context.startActivity(intent);
			}
		});
	}
}
