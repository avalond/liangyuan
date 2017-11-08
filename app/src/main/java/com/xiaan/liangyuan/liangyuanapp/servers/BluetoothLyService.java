package com.xiaan.liangyuan.liangyuanapp.servers;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.xiaan.liangyuan.liangyuanapp.LiangYuanApplication;
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
	


	private static void broadcastConnectionUpdate(String action) {
		Intent intent = new Intent(action);
		LiangYuanApplication.getContext().sendBroadcast(intent);
	}


	/**
	 * Implements callback methods for GATT events that the app cares about. For
	 * example,connection change and services discovered.
	 * 连接状态  已连接、断开等等
	 */
	private final static BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

		@Override public void onServicesDiscovered(BluetoothGatt gatt, int status) {
			super.onServicesDiscovered(gatt, status);
			//发现新的服务
			if (status == BluetoothGatt.GATT_SUCCESS) {
				LoggerUtils.d(TAG, "Bluetooth---------->发现服务");
				broadcastConnectionUpdate(Constants.ACTION_GATT_SERVICES_DISCOVERED);
			} else {
				LoggerUtils.d(TAG, "Bluetooth---------->未发现服务");
			}
		}


		@Override public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
			super.onDescriptorWrite(gatt, descriptor, status);
			if (status == BluetoothGatt.GATT_SUCCESS) {
				LoggerUtils.d(TAG, "BluetoothGatt onDescriptorWrite GATT_SUCCESS------------------->SUCCESS");

			} else if (status == BluetoothGatt.GATT_FAILURE) {
				LoggerUtils.d(TAG, "BluetoothGatt onDescriptorWrite GATT_FAIL------------------->FAILURE");
				Intent intent=new Intent(Constants.ACTION_GATT_DESCRIPTORWRITE_RESULT);
				intent.putExtra(Constants.)
			}
		}


		@Override public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
			super.onConnectionStateChange(gatt, status, newState);

			String mIntentAction;
			//Gatt server connected
			if (newState == BluetoothProfile.STATE_CONNECTED) {
				LoggerUtils.d(TAG, "bluetooth ---------> 已经连接");
				mIntentAction = Constants.ACTION_GATT_CONNECTED;
				mConnectionState = Constants.STATE_DISCONNECTED;
				broadcastConnectionUpdate(mIntentAction);
			} else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
				LoggerUtils.d(TAG, "bluetooth ---------> 连接断开");
				mIntentAction = Constants.ACTION_GATT_DISCONNECTED;
				mConnectionState = Constants.STATE_DISCONNECTED;
				broadcastConnectionUpdate(mIntentAction);
			} else if (newState == BluetoothProfile.STATE_DISCONNECTING) {
				LoggerUtils.d(TAG, "bluetooth ---------> 正在连接");
				// mIntentAction = Constants.ACTION_GATT_CONNECTED;
				// mConnectionState = Constants.STATE_DISCONNECTED;
				// broadcastConnectionUpdate(mIntentAction);
			}

		}
	};


	@Nullable @Override public IBinder onBind(Intent intent) {
		return null;
	}


	//binder
	public class LocalBinder extends Binder {
		public BluetoothLyService getLocalBluetoothServers() {
			return BluetoothLyService.this;
		}
	}
}
