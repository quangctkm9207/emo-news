package ss.passion.emonews;

import ss.passion.emonews.adapter.MyPagerAdapter;
import ss.passion.emonews.utils.Variables;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class HelpActivity extends ActionBarActivity implements OnClickListener {
	private ActionBar aBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		aBar = getSupportActionBar();
		aBar.setTitle(getResources().getString(R.string.help));
		aBar.setIcon(R.drawable.back_icon);
		aBar.setHomeButtonEnabled(true);

		ImageButton ibRate = (ImageButton) findViewById(R.id.ibRate);
		ibRate.setOnClickListener(this);
		ImageButton ibSend = (ImageButton) findViewById(R.id.ibFeedback);
		ibSend.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibRate:
			try {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("market://details?id=ss.passion.emonews")));

			} catch (android.content.ActivityNotFoundException anfe) {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://play.google.com/store/apps/details?id=ss.passion.emonews")));
			}
			break;
		case R.id.ibFeedback:
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "quang.bme.hust.55@gmail.com" });
			i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Lật đật app");
			i.putExtra(Intent.EXTRA_TEXT, "");
			try {
				startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this,
						"There are no email clients installed.",
						Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}

	}

}
