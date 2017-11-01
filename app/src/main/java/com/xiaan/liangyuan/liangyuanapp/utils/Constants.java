package com.xiaan.liangyuan.liangyuanapp.utils;

/**
 * Created by kevin .
 */

public class Constants {
	/**
	 * Status constants
	 */
	//
	public final static String ACTION_GATT_CONNECTED = "com.liangyuan.bluetooth.le.ACTION_GATT_CONNECTED";
	public final static String ACTION_GATT_DISCONNECTED = "com.liangyuan.bluetooth.le.ACTION_GATT_DISCONNECTED";
	public final static String ACTION_GATT_DISCONNECTED_CAROUSEL = "com.liangyuan.bluetooth.le.ACTION_GATT_DISCONNECTED_CAROUSEL";
	public final static String ACTION_GATT_SERVICES_DISCOVERED = "com.liangyuan.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
	public final static String ACTION_DATA_AVAILABLE = "com.liangyuan.bluetooth.le.ACTION_DATA_AVAILABLE";

	public final static String ACTION_GATT_CHARACTERISTIC_ERROR = "com.liangyuan.bluetooth.le.ACTION_GATT_CHARACTERISTIC_ERROR";

	public final static String ACTION_GATT_DESCRIPTORWRITE_RESULT = "com.liangyuan.bluetooth.le.ACTION_GATT_DESCRIPTORWRITE_RESULT";
	public final static String ACTION_GATT_CHARACTERISTIC_WRITE_SUCCESS = "com.liangyuan.bluetooth.le.ACTION_GATT_CHARACTERISTIC_SUCCESS";

	private final static String ACTION_GATT_DISCONNECTING = "com.liangyuan.bluetooth.le.ACTION_GATT_DISCONNECTING";
	/**
	 * Connection status
	 */
	public static final int STATE_DISCONNECTED = 0;
	private static final int STATE_CONNECTING = 1;
	private static final int STATE_CONNECTED = 2;
	private static final int STATE_DISCONNECTING = 4;
	
}
