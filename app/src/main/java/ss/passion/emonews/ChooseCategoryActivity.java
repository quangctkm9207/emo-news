package ss.passion.emonews;

import ss.passion.emonews.fragment.CategoryFragment;
import ss.passion.emonews.utils.Variables;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class ChooseCategoryActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_category);
		
		getSupportActionBar().setTitle("Chọn chủ đề yêu thích");
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new CategoryFragment()).commit();
		}
		if(!isFirstTime()&&!isEditMode()){
			finish();
			Intent intent = new Intent(ChooseCategoryActivity.this,MainScreenActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			overridePendingTransition(R.animator.activity_in, R.animator.activity_out);
		}
	}
	public boolean isFirstTime(){
		SharedPreferences pre = getSharedPreferences(Variables.CATEGORY_PRE, 0);
		String categoryStr= pre.getString(Variables.CATEGORY_KEY, "");
		if(categoryStr.equals("")){
			return true;
		}else{
			return false;
		}
	}
	public boolean isEditMode(){
		return getIntent().getBooleanExtra("edit_category", false);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.choose_category, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.action_done){
			
		}
		
		return super.onOptionsItemSelected(item);
	}
}
