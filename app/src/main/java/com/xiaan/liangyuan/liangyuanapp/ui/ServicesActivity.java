package com.xiaan.liangyuan.liangyuanapp.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaan.liangyuan.liangyuanapp.LiangYuanApplication;
import com.xiaan.liangyuan.liangyuanapp.R;
import com.xiaan.liangyuan.liangyuanapp.Utils.AnimateUtils;
import com.xiaan.liangyuan.liangyuanapp.Utils.GattAttributes;
import com.xiaan.liangyuan.liangyuanapp.Utils.LoggerUtils;
import com.xiaan.liangyuan.liangyuanapp.Utils.Utils;
import com.xiaan.liangyuan.liangyuanapp.adapter.ServicesAdapter;
import com.xiaan.liangyuan.liangyuanapp.bean.MService;
import com.xiaan.liangyuan.liangyuanapp.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends BaseActivity {
	private final String TAG=ServicesActivity.class.getSimpleName();
	private final List<MService> list = new ArrayList<>();
	private RelativeLayout rlTop;
	private View filterView;
	private View viewShadow;
	private ListView lvServices;
	private ImageView ivBle;
	private TextView tvServiceName;
	private TextView tvServiceMac;
	private TextView tvServiceCount;

	private ServicesAdapter adapter;
	private LiangYuanApplication myApplication;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services);
		rlTop = findViewById(R.id.rl_top);
		filterView = findViewById(R.id.view_filter);
		viewShadow = findViewById(R.id.view_shadow);
		lvServices = findViewById(R.id.lv_services);
		ivBle = findViewById(R.id.iv_ble);
		tvServiceName = findViewById(R.id.tv_service_name);
		tvServiceMac = findViewById(R.id.tv_service_mac);
		tvServiceCount = findViewById(R.id.tv_service_count);

		bindToolBar();
		myApplication = (LiangYuanApplication) getApplication();
		List<MService> services = myApplication.getServices();
		list.addAll(services);
		adapter = new ServicesAdapter(this, list);
		lvServices.setAdapter(adapter);

		Intent intent = getIntent();
		tvServiceName.setText("NAME:" + intent.getStringExtra("dev_name"));
		tvServiceMac.setText("MAC:" + intent.getStringExtra("dev_mac"));
		tvServiceCount.setText("SERVICES:" + String.valueOf(list.size()));

		lvServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				MService mService = list.get(position);
				BluetoothGattService service = mService.getService();

				LoggerUtils.d(TAG,"service---------------->" + service.getUuid().toString());

				myApplication.setCharacteristics(service.getCharacteristics());

				Intent intent = new Intent(ServicesActivity.this, CharacteristicsActivity.class);
				if (service.getUuid().toString().equals(GattAttributes.USR_SERVICE)) {
					intent.putExtra("is_usr_service", true);

					//这里为了方便暂时直接用Application serviceType 来标记当前的服务，应该是和上面的代码合并
					LiangYuanApplication.serviceType = LiangYuanApplication.SERVICE_TYPE.TYPE_USR_DEBUG;
				} else if (service.getUuid().toString().equals(GattAttributes.BATTERY_SERVICE) ||
						service.getUuid().toString().equals(GattAttributes.RGB_LED_SERVICE_CUSTOM)) {
					LiangYuanApplication.serviceType = LiangYuanApplication.SERVICE_TYPE.TYPE_NUMBER;
				} else {
					LiangYuanApplication.serviceType = LiangYuanApplication.SERVICE_TYPE.TYPE_OTHER;
				}

				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		if (savedInstanceState == null) {
			filterView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					filterView.getViewTreeObserver().removeOnPreDrawListener(this);
					startAnimation();
					return true;
				}
			});
		}

	}


	private void startAnimation() {
		rlTop.setAlpha(0.0f);
		rlTop.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		ObjectAnimator animator1 = ObjectAnimator.ofInt(rlTop, "backgroundColor",
				Color.parseColor("#0277bd"), Color.parseColor("#009688"));
		animator1.setDuration(700);
		animator1.setStartDelay(100);
		animator1.setEvaluator(new ArgbEvaluator());
		animator1.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				viewShadow.setVisibility(View.VISIBLE);
				rlTop.setLayerType(View.LAYER_TYPE_NONE, null);
			}
		});

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			toolbar.setAlpha(0.0f);
			toolbar.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			ObjectAnimator animator0 = ObjectAnimator.ofInt(toolbar, "backgroundColor",
					Color.parseColor("#0277bd"), Color.parseColor("#009688"));
			animator0.setDuration(700);
			animator0.setStartDelay(100);
			animator0.setEvaluator(new ArgbEvaluator());
			animator0.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					toolbar.setLayerType(View.LAYER_TYPE_NONE, null);
				}
			});
			animator0.start();
			AnimateUtils.alpha(toolbar, 1.0f, 400, 0);
		}

		animator1.start();
		AnimateUtils.alpha(rlTop, 1.0f, 400, 0);
		startIntroAnimator();
	}


	private void startIntroAnimator() {
		ivBle.setVisibility(View.VISIBLE);
		tvServiceName.setVisibility(View.VISIBLE);
		tvServiceMac.setVisibility(View.VISIBLE);
		tvServiceCount.setVisibility(View.VISIBLE);
		ivBle.setTranslationX(-Utils.dpToPx(100));
		ivBle.setRotation(-360f);
		ivBle.setAlpha(0f);
		tvServiceName.setTranslationX(Utils.dpToPx(300));
		tvServiceName.setAlpha(0f);
		tvServiceMac.setTranslationX(Utils.dpToPx(300));
		tvServiceMac.setAlpha(0f);
		tvServiceCount.setTranslationX(Utils.dpToPx(300));
		tvServiceCount.setAlpha(0f);

		lvServices.setTranslationY(Utils.dpToPx(300));

		AnimateUtils.translationX(ivBle, 0, 400, 400);
		AnimateUtils.rotation(ivBle, 0f, 400, 400, null);
		AnimateUtils.alpha(ivBle, 1.0f, 400, 400);
		AnimateUtils.translationX(tvServiceName, 0, 400, 400);
		AnimateUtils.alpha(tvServiceName, 1.0f, 400, 400);
		AnimateUtils.translationX(tvServiceMac, 0, 400, 500);
		AnimateUtils.alpha(tvServiceMac, 1.0f, 400, 500);
		AnimateUtils.translationX(tvServiceCount, 0, 400, 600);
		AnimateUtils.alpha(tvServiceCount, 1.0f, 400, 600);

		AnimateUtils.alpha(lvServices, 1.0f, 200, 400);
		AnimateUtils.translationY(lvServices, 0, 400, 400);
	}

}
