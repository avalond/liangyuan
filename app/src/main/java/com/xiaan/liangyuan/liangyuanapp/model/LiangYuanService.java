package com.xiaan.liangyuan.liangyuanapp.model;

import android.bluetooth.BluetoothGattServer;

/**
 * Created by kevin .
 */

public class LiangYuanService {

	private String name;
	private BluetoothGattServer mBluetoothGattServer;


	public LiangYuanService() {

	}


	public LiangYuanService(String name, BluetoothGattServer bluetoothGattServer) {
		this.name = name;
		this.mBluetoothGattServer = bluetoothGattServer;
	}


	//get set function
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BluetoothGattServer getBluetoothGattServer() {
		return mBluetoothGattServer;
	}


	public void setBluetoothGattServer(BluetoothGattServer bluetoothGattServer) {
		mBluetoothGattServer = bluetoothGattServer;
	}

}
