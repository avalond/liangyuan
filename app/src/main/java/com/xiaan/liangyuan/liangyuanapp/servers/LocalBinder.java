package com.xiaan.liangyuan.liangyuanapp.servers;

import android.os.Binder;

/**
 * Created by kevin .
 */

public class LocalBinder extends Binder {
	public BluetoothLyService getServers(){
		return BluetoothLyService;
	}
}
