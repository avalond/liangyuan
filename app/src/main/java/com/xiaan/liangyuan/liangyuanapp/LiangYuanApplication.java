package com.xiaan.liangyuan.liangyuanapp;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.xiaan.liangyuan.liangyuanapp.model.LiangYuanService;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin.
 */

public class LiangYuanApplication extends MultiDexApplication {
	private static final String TAG = LiangYuanApplication.class.getSimpleName();

	public static Context mContext;
	private String versionName;
	private int versionCode;

	//Represents a Bluetooth GATT Characteristic
	//表示蓝牙GATT特性
	private BluetoothGattCharacteristic mBluetoothGattCharacteristic;


	public static Context getContext() {
		return mContext;
	}


	private List<LiangYuanService> mLiangYuanServicesList;
	private List<BluetoothGattCharacteristic> mBluetoothGattCharacteristicsList;


	public List<BluetoothGattCharacteristic> getBluetoothGattCharacteristics() {
		return mBluetoothGattCharacteristicsList;
	}


	//set servers
	public void setServers(List<LiangYuanService> serviceList) {
		this.mLiangYuanServicesList.clear();
		this.mLiangYuanServicesList.addAll(serviceList);
	}


	//set Bluetooth Gatt Characteristic list
	public void setBluetoothGattCharacteristic(List<BluetoothGattCharacteristic> characteristicsList) {
		this.mBluetoothGattCharacteristicsList.clear();
		this.mBluetoothGattCharacteristicsList.addAll(characteristicsList);
	}


	// set Bluetooth Gatt Characteristic
	public void setBluetoothGattCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
		this.mBluetoothGattCharacteristic = bluetoothGattCharacteristic;
	}


	// get  Bluetooth Gatt Characteristic
	public BluetoothGattCharacteristic getBluetoothGattCharacteristic() {
		return mBluetoothGattCharacteristic;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		LoggerUtils.i(TAG, "application is on created");
		mContext = getApplicationContext();
		versionCode = 0;
		versionName = null;
		//
		mLiangYuanServicesList = new ArrayList<>();
		//
		mBluetoothGattCharacteristicsList = new ArrayList<>();

		// database
		Realm.init(this);
	}

}
