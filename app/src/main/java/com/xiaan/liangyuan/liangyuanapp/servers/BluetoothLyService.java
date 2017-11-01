package com.xiaan.liangyuan.liangyuanapp.servers;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.xiaan.liangyuan.liangyuanapp.utils.Constants;
import com.xiaan.liangyuan.liangyuanapp.utils.LoggerUtils;

/**
 * Service for managing connection and data communication with a GATT server
 * hosted on a given BlueTooth LE device.
 * Created by kevin .
 */

public class BluetoothLyService extends Service {

	private static final String TAG = BluetoothLyService.class.getSimpleName();

	/**
	 * BluetoothAdapter for handling connections
	 * 连接蓝牙都需要，用来管理手机上的蓝牙
	 */
	public static BluetoothAdapter mBluetoothAdapter;

	/**
	 * a) BluetoothGattServer作为周边来提供数据；BluetoothGattServerCallback返回周边的状态。
	 * b) BluetoothGatt作为中央来使用和处理数据；BluetoothGattCallback返回中央的状态和周边提供的数据
	 */
	public static BluetoothGatt mBluetoothGatt;
	private static int mConnectionState = Constants.STATE_DISCONNECTED;

	//Device address
	private static String mBluetoothDeviceAddress;
	//Devices name
	private static String mBluetoothDeviceName;
	private static Context mContext;
	//Intent action with bluetooth gatt connection state change
	public static String mIntentAction;

	/**
	 * Implements callback methods for GATT events that the app cares about. For
	 * example,connection change and services discovered.
	 * 连接状态  已连接、断开等等
	 */
	private final static BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
		@Override public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
			super.onConnectionStateChange(gatt, status, newState);
			//Gatt server connected
			if (newState == BluetoothProfile.STATE_CONNECTED) {
				LoggerUtils.d(TAG, "bluetooth is connected");
				mIntentAction =Constants.ACTION_GATT_CONNECTED;
				mConnectionState=Constants.STATE_DISCONNECTED;

			}
		}
	};


	@Nullable @Override public IBinder onBind(Intent intent) {
		return null;
	}
}
