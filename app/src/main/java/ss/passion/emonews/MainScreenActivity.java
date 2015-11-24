package ss.passion.emonews;

import ss.passion.emonews.fragment.NavigationDrawerFragment;
import ss.passion.emonews.fragment.NewsCategoryFragment;
import ss.passion.emonews.fragment.WeatherFragment;
import ss.passion.emonews.utils.LocationTracker;
import ss.passion.emonews.utils.Variables;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainScreenActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private ActionBar aBar;
	int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		rate_app();

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getResources().getString(R.string.app_name);
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new NewsCategoryFragment();
			break;
		case 1:
			fragment = new WeatherFragment();
			break;
		case 2:
			Intent intent = new Intent(MainScreenActivity.this,
					HelpActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case 3:
			fragment = new WeatherFragment();
			break;
		}
		if (fragment != null) {
			onSectionAttached(position);

			FragmentManager fragmentManager = getSupportFragmentManager();
			android.support.v4.app.FragmentTransaction transaction = fragmentManager
					.beginTransaction();
			transaction.replace(R.id.container, fragment);
			transaction.commit();
		}

	}

	public void onSectionAttached(int number) {

		// if (number == 1) {
		// LocationTracker tracker = new LocationTracker(
		// getApplicationContext());
		// tracker.getLocation();
		// mTitle = tracker.getLocationName();

		// } else
		mTitle = Variables.DRAWER_TITLES[number];

	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main_screen, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		FragmentManager fm = getSupportFragmentManager();
		if (fm.getBackStackEntryCount() > 0) {
			Log.i("MainActivity", "popping backstack");
			fm.popBackStackImmediate();
		} else {
			Log.i("MainActivity", "nothing on backstack, calling super");
			super.onBackPressed();
		}
	}

	void rate_app() {

		SharedPreferences pre = getSharedPreferences("rate_app", 0);
		final SharedPreferences.Editor edit = pre.edit();
		 count = pre.getInt("count", 0);
//		 Toast.makeText(getApplicationContext(), String.valueOf(count), Toast.LENGTH_SHORT).show();
		if (count < 2) {
			count++;
			edit.putInt("count", count);
			edit.commit();
		} else if (count == 2) {
			AlertDialog.Builder ab = new AlertDialog.Builder(this);
			ab.setTitle("Đánh giá");
			ab.setMessage("Bạn có thể dành 5s đánh giá ứng dụng này được không? Cảm ơn bạn!");
			ab.setNegativeButton("Để lần sau",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.dismiss();
						}
					});
			ab.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							try {
								startActivity(new Intent(
										Intent.ACTION_VIEW,
										Uri.parse("market://details?id=ss.passion.emonews")));

							} catch (android.content.ActivityNotFoundException anfe) {
								startActivity(new Intent(
										Intent.ACTION_VIEW,
										Uri.parse("https://play.google.com/store/apps/details?id=ss.passion.emonews")));
							}
							edit.putInt("count", 3);
							edit.commit();
							dialog.dismiss();
						}
					});
			ab.show();
		}else{
			
		}
		
	}

}
