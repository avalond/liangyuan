package com.xiaan.liangyuan.liangyuanapp.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import com.xiaan.liangyuan.liangyuanapp.LiangYuanApplication;
import com.xiaan.liangyuan.liangyuanapp.R;
import com.xiaan.liangyuan.liangyuanapp.Utils.GattAttributes;
import com.xiaan.liangyuan.liangyuanapp.adapter.CharacteristicsAdapter;
import com.xiaan.liangyuan.liangyuanapp.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CharacteristicsActivity extends BaseActivity {
	private View filterView;
	private ListView lvCharacteristics;
	private View viewShadow;

	private final List<BluetoothGattCharacteristic> list = new ArrayList<>();
	private CharacteristicsAdapter adapter;

	private LiangYuanApplication myApplication;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_characteristics);
		filterView = findViewById(R.id.view_filter);
		lvCharacteristics = findViewById(R.id.lv_characteristics);
		viewShadow = findViewById(R.id.view_shadow);
		bindToolBar();

		myApplication = (LiangYuanApplication) getApplication();

		List<BluetoothGattCharacteristic> characteristics = ((LiangYuanApplication) getApplication()).getCharacteristics();
		list.addAll(characteristics);

		//we create a virtual BluetoothGattCharacteristic just for debug
		if (getIntent().getBooleanExtra("is_usr_service", false)) {
			BluetoothGattCharacteristic usrVirtualCharacteristic =
					new BluetoothGattCharacteristic(UUID.fromString(GattAttributes.USR_SERVICE), -1, -1);
			list.add(usrVirtualCharacteristic);
		}

		adapter = new CharacteristicsAdapter(this, list);
		lvCharacteristics.setAdapter(adapter);

		lvCharacteristics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				myApplication.setCharacteristic(list.get(position));
				Intent intent = new Intent(CharacteristicsActivity.this, GattDetailActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		if (savedInstanceState == null) {
			filterView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					filterView.getViewTreeObserver().removeOnPreDrawListener(this);
					startEndAnimation();
					return true;
				}
			});
		}
	}


	private void startEndAnimation() {
		filterView.setAlpha(0.0f);
		filterView.setVisibility(View.VISIBLE);
		filterView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		filterView.animate()
				.alpha(0.6f)
				.setDuration(200)
				.setInterpolator(new AccelerateInterpolator())
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						viewShadow.setVisibility(View.VISIBLE);
						lvCharacteristics.setVisibility(View.VISIBLE);
						animate2();
					}
				})
				.start();

	}


	private void animate2() {
		filterView.animate()
				.alpha(0.0f)
				.setDuration(200)
				.setInterpolator(new AccelerateInterpolator())
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						filterView.setLayerType(View.LAYER_TYPE_NONE, null);
						filterView.setVisibility(View.GONE);
					}
				})
				.start();
	}

}
