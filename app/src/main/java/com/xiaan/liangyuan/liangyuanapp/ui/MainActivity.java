package com.xiaan.liangyuan.liangyuanapp.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaan.liangyuan.liangyuanapp.BlueToothLeService.BluetoothLeService;
import com.xiaan.liangyuan.liangyuanapp.LiangYuanApplication;
import com.xiaan.liangyuan.liangyuanapp.R;
import com.xiaan.liangyuan.liangyuanapp.Utils.AnimateUtils;
import com.xiaan.liangyuan.liangyuanapp.Utils.GattAttributes;
import com.xiaan.liangyuan.liangyuanapp.Utils.LoggerUtils;
import com.xiaan.liangyuan.liangyuanapp.Utils.Utils;
import com.xiaan.liangyuan.liangyuanapp.adapter.DevicesAdapter;
import com.xiaan.liangyuan.liangyuanapp.bean.MDevice;
import com.xiaan.liangyuan.liangyuanapp.bean.MService;
import com.xiaan.liangyuan.liangyuanapp.ui.base.BaseActivity;
import com.xiaan.liangyuan.liangyuanapp.views.RevealBackgroundView;
import com.xiaan.liangyuan.liangyuanapp.views.RevealSearchView;
import java.util.ArrayList;
import java.util.List;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends BaseActivity implements View.OnClickListener {
	private static final String TAG = MainActivity.class.getSimpleName();
	private RecyclerView recyclerView;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	private FloatingActionButton fabSearch;
	private RevealSearchView revealSearchView;
	private RevealBackgroundView revealBackgroundView;
	private RelativeLayout rlSearchInfo;
	private TextView tvSearchDeviceCount;
	private Button mStopSeacheBt;

	CoordinatorLayout clContent;

	private final List<MDevice> list = new ArrayList<>();
	private DevicesAdapter adapter;

	// Stops scanning after 10 seconds.
	private static final long SCAN_PERIOD = 10000;
	// Blue tooth adapter for BLE device scan
	private static BluetoothAdapter mBluetoothAdapter;
	private boolean scaning;
	private BluetoothLeScanner bleScanner;

	private Handler hander;

	private String currentDevAddress;
	private String currentDevName;
	private int[] fabStartPosition;

	// private MaterialDialog alarmDialog;
	// private MaterialDialog progressDialog;

	boolean isShowingDialog = false;

	private Runnable dismssDialogRunnable = new Runnable() {
		@Override
		public void run() {
			// if (progressDialog != null)
			//     progressDialog.dismiss();
			disconnectDevice();
			Snackbar.make(clContent, R.string.connect_fail, Snackbar.LENGTH_LONG).show();
		}
	};

	private Runnable stopScanRunnable = new Runnable() {
		@Override
		public void run() {
			if (mBluetoothAdapter != null) {
				mBluetoothAdapter.startLeScan(mLeScanCallback);
			}
		}
	};


	@SuppressLint("ClickableViewAccessibility") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//必须调用，其在setContentView后面调用

		recyclerView = findViewById(R.id.recycleview);
		collapsingToolbarLayout = findViewById(R.id.coll_toolbar);
		fabSearch = findViewById(R.id.fab_search);
		fabSearch.setOnClickListener(this);
		revealSearchView = findViewById(R.id.realsearchiew);
		revealBackgroundView = findViewById(R.id.reveal_background_view);
		rlSearchInfo = findViewById(R.id.rl_search_info);
		tvSearchDeviceCount = findViewById(R.id.tv_search_device_count);
		clContent = findViewById(R.id.cl_content);
		mStopSeacheBt = findViewById(R.id.btn_stop_searching);
		mStopSeacheBt.setOnClickListener(this);

		bindToolBar();
		//toolbar.setNavigationIcon(R.mipmap.ic_bluetooth_disabled_white_48dp);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayShowTitleEnabled(false);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
		collapsingToolbarLayout.setTitle(getString(R.string.devices));

		hander = new Handler();
		LinearLayoutManager llm = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(llm);

		adapter = new DevicesAdapter(list, this);
		recyclerView.setAdapter(adapter);

		recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
			@Override
			public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
				adapter.setDelayStartAnimation(false);
				return false;
			}


			@Override
			public void onTouchEvent(RecyclerView rv, MotionEvent e) {

			}


			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}
		});

		adapter.setOnItemClickListener(new DevicesAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View itemView, int position) {
				if (!scaning) {
					isShowingDialog = true;
					//  showProgressDialog();
					hander.postDelayed(dismssDialogRunnable, 20000);
					connectDevice(list.get(position).getDevice());
				}
			}
		});

		//设置一个监听，否则会报错，support library design的bug
		collapsingToolbarLayout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		revealSearchView.setOnStateChangeListener(new RevealSearchView.OnStateChangeListener() {
			@Override
			public void onStateChange(int state) {
				if (state == RevealSearchView.STATE_FINISHED) {
					revealSearchView.setVisibility(View.GONE);
					revealBackgroundView.endFromEdge();
				}
			}
		});

		revealBackgroundView.setOnStateChangeListener(new RevealBackgroundView.OnStateChangeListener() {
			@Override
			public void onStateChange(int state) {

				if (state == RevealBackgroundView.STATE_FINISHED) {
					revealSearchView.setVisibility(View.VISIBLE);
					revealSearchView.startFromLocation(fabStartPosition);

					tvSearchDeviceCount.setText(getString(R.string.search_device_count, 0));
					rlSearchInfo.setVisibility(View.VISIBLE);
					rlSearchInfo.setTranslationY(Utils.dpToPx(70));
					rlSearchInfo.setAlpha(0);
					AnimateUtils.translationY(rlSearchInfo, 0, 300, 0);
					AnimateUtils.alpha(rlSearchInfo, 1.0f, 300, 0);

					onRefresh();
				}

				if (state == RevealBackgroundView.STATE_END_FINISHED) {
					revealBackgroundView.setVisibility(View.GONE);
					rlSearchInfo.setVisibility(View.GONE);
					scaning = false;
					adapter.notifyDataSetChanged();
				}
			}
		});

		checkBleSupportAndInitialize();

		//注册广播接收者，接收消息
		registerReceiver(mGattUpdateReceiver, Utils.makeGattUpdateIntentFilter());

		Intent gattServiceIntent = new Intent(getApplicationContext(),
				BluetoothLeService.class);
		startService(gattServiceIntent);

		collapsingToolbarLayout.setTranslationY(160f);
		toolbar.setTranslationY(-Utils.dpToPx(60));
		collapsingToolbarLayout.setAlpha(0.0f);
		//        fabSearch.setRotation(360f);
		//        fabSearch.setTranslationX(280);

		AnimateUtils.translationY(collapsingToolbarLayout, 0, 400, 100);
		AnimateUtils.translationY(toolbar, 0, 400, 200);
		//        AnimateUtils.translationX(fabSearch, 0, 400, 300);
		//        AnimateUtils.rotation(fabSearch, 0, 400, 300, null);
		AnimateUtils.alpha(collapsingToolbarLayout, 1f, 400, 100);

	}


	@Override
	protected void onResume() {
		super.onResume();
		//如果有连接先关闭连接
		disconnectDevice();
	}


	/**
	 * 获得蓝牙适配器
	 */
	private void checkBleSupportAndInitialize() {
		// Use this check to determine whether BLE is supported on the device.
		if (!getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH_LE)) {
			Toast.makeText(this, R.string.device_ble_not_supported,
					Toast.LENGTH_SHORT).show();
			return;
		}
		// Initializes a Blue tooth adapter.
		final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();

		if (mBluetoothAdapter == null) {
			// Device does not support Blue tooth
			Toast.makeText(this,
					R.string.device_ble_not_supported, Toast.LENGTH_SHORT)
					.show();
			return;
		}

		//打开蓝牙
		if (!mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.enable();
		}

	}


	@Override public void onClick(View v) {
		switch (v.getId()) {
			case R.id.fab_search:
				searchDevice();
				break;
			case R.id.btn_stop_searching:
				stopSearching();
				break;
			default:
				break;
		}
	}


	private void searchDevice() {
		if (!scaning) {
			scaning = true;
			//如果有连接先关闭连接
			disconnectDevice();
			searchAnimate();
		}
	}


	private void stopSearching() {
		scaning = false;
		stopScan();
	}


	private void searchAnimate() {
		revealBackgroundView.setVisibility(View.VISIBLE);

		int[] position1 = new int[2];
		fabSearch.getLocationOnScreen(position1);

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			fabStartPosition = new int[] { (position1[0] + fabSearch.getWidth() / 2),
					(position1[1] + fabSearch.getHeight() / 4) };
		} else {
			fabStartPosition = new int[] { (position1[0] + fabSearch.getWidth() / 2),
					position1[1] };
		}

		revealBackgroundView.startFromLocation(fabStartPosition);
	}


	public void onRefresh() {
		// Prepare list view and initiate scanning
		if (adapter != null) {
			adapter.clear();
			adapter.notifyDataSetChanged();
		}
		startScan();
	}


	private void startScan() {
		//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
		scanPrevious21Version();
		//        } else {
		//            scanAfter21Version();
		//        }
	}


	/**
	 * 版本号21之前的调用该方法搜索
	 */
	private void scanPrevious21Version() {
		//10秒后停止扫描
		hander.postDelayed(stopScanRunnable, 10000);
		mBluetoothAdapter.startLeScan(mLeScanCallback);
	}


	private void stopScan() {
		revealSearchView.setVisibility(View.GONE);
		//停止雷达动画
		revealSearchView.stopAnimate();
		//涟漪动画回缩
		revealBackgroundView.endFromEdge();
		mBluetoothAdapter.stopLeScan(mLeScanCallback);
		hander.removeCallbacks(stopScanRunnable);
	}


	/**
	 * Call back for BLE Scan
	 * This call back is called when a BLE device is found near by.
	 * 发现设备时回调
	 */
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

		@Override
		public void onLeScan(final BluetoothDevice device, final int rssi,
		                     byte[] scanRecord) {

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					MDevice mDev = new MDevice(device, rssi);
					if (list.contains(mDev)) {
						return;
					}
					list.add(mDev);
					tvSearchDeviceCount.setText(getString(R.string.search_device_count, list.size()));
				}
			});
		}
	};


	/**
	 * 版本号21及之后的调用该方法扫描，该方法不是特别管用,因此demo中不再使用，仅供参考
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void scanAfter21Version() {

		hander.postDelayed(new Runnable() {
			@Override
			public void run() {
				bleScanner.stopScan(new ScanCallback() {
					@Override
					public void onScanResult(int callbackType, ScanResult result) {
						super.onScanResult(callbackType, result);
					}
				});
			}
		}, 10000);

		if (bleScanner == null) {
			bleScanner = mBluetoothAdapter.getBluetoothLeScanner();
		}

		bleScanner.startScan(new ScanCallback() {
			@Override
			public void onScanResult(int callbackType, ScanResult result) {
				super.onScanResult(callbackType, result);
				MDevice mDev = new MDevice(result.getDevice(), result.getRssi());
				if (list.contains(mDev)) {
					return;
				}
				list.add(mDev);
				tvSearchDeviceCount.setText(getString(R.string.search_device_count, list.size()));
				//                adapter.notifyDataSetChanged();
			}
		});
	}


	private void connectDevice(BluetoothDevice device) {
		currentDevAddress = device.getAddress();
		currentDevName = device.getName();
		//如果是连接状态，断开，重新连接
		if (BluetoothLeService.getConnectionState() != BluetoothLeService.STATE_DISCONNECTED) {
			BluetoothLeService.disconnect();
		}

		BluetoothLeService.connect(currentDevAddress, currentDevName, this);
	}


	private void disconnectDevice() {
		isShowingDialog = false;
		BluetoothLeService.disconnect();
	}


	/**
	 * BroadcastReceiver for receiving the GATT communication status
	 */
	private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			// Status received when connected to GATT Server
			//连接成功
			if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
				LoggerUtils.d(TAG, "--------------------->连接成功");

				//搜索服务
				BluetoothLeService.discoverServices();
			}
			// Services Discovered from GATT Server
			else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED
					.equals(action)) {
				hander.removeCallbacks(dismssDialogRunnable);
				// progressDialog.dismiss();
				prepareGattServices(BluetoothLeService.getSupportedGattServices());
			} else if (action.equals(BluetoothLeService.ACTION_GATT_DISCONNECTED)) {
				//progressDialog.dismiss();
				//connect break (连接断开)
			LoggerUtils.d(TAG,"------------> Connect disconnect,please reconnect");
//				showDialog(getString(R.string.conn_disconnected_home));
			}

		}
	};


	/**
	 * Getting the GATT Services
	 * 获得服务
	 */
	private void prepareGattServices(List<BluetoothGattService> gattServices) {
		prepareData(gattServices);

		Intent intent = new Intent(this, ServicesActivity.class);
		intent.putExtra("dev_name", currentDevName);
		intent.putExtra("dev_mac", currentDevAddress);
		startActivity(intent);
		overridePendingTransition(0, 0);
	}


	/**
	 * Prepare GATTServices data.
	 */
	private void prepareData(List<BluetoothGattService> gattServices) {

		if (gattServices == null) {
			return;
		}

		List<MService> list = new ArrayList<>();

		for (BluetoothGattService gattService : gattServices) {
			String uuid = gattService.getUuid().toString();
			if (uuid.equals(GattAttributes.GENERIC_ACCESS_SERVICE) || uuid.equals(GattAttributes.GENERIC_ATTRIBUTE_SERVICE)) {
				continue;
			}
			String name = GattAttributes.lookup(gattService.getUuid().toString(), "UnkonwService");
			MService mService = new MService(name, gattService);
			list.add(mService);
		}

		((LiangYuanApplication) getApplication()).setServices(list);
	}


	private void showDialog(String info) {
		final AlertDialog.Builder normalDialog =
				new AlertDialog.Builder(this);

		normalDialog.setTitle(getString(R.string.alert));
		normalDialog.setMessage(info);

		normalDialog.setNegativeButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		// 显示
		normalDialog.show();
	}

	// private void showProgressDialog() {
	//     progressDialog = new MaterialDialog(this);
	//     View view = LayoutInflater.from(this)
	//             .inflate(R.layout.progressbar_item,
	//                     null);
	//     progressDialog.setView(view).show();
	// }


	@Override
	protected void menuHomeClick() {
		return;
	}


	@Override
	public void onBackPressed() {
		finish();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mGattUpdateReceiver);
	}

}
