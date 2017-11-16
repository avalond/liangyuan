package com.xiaan.liangyuan.liangyuanapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MyBaseActivity extends AppCompatActivity {
	Toolbar toolbar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//        getWindow().setBackgroundDrawable(null);
	}


	protected void bindToolBar() {

		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);

		if (Build.VERSION.SDK_INT >= 23) {
			toolbar.setTitleTextColor(getColor(android.R.color.white));
		} else {
			toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_usr, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				System.out.println("--------------->home");
				menuHomeClick();
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	protected void menuHomeClick() {
		//默认返回上一层
		finish();
		overridePendingTransition(0, R.anim.slide_top_to_bottom);
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.slide_top_to_bottom);
	}

}
