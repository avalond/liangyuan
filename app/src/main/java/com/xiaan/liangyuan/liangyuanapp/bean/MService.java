package com.xiaan.liangyuan.liangyuanapp.bean;

import android.bluetooth.BluetoothGattService;


public class MService {
    private String name;
    private BluetoothGattService service;


    public MService() {
    }

    public MService(String name, BluetoothGattService service) {
        this.name = name;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BluetoothGattService getService() {
        return service;
    }

    public void setService(BluetoothGattService service) {
        this.service = service;
    }
}
