package com.vathsav.bloobot.model;

import android.bluetooth.BluetoothDevice;

/**
 * POJO for bluetooth device item
 */
public class BluetoothDeviceItem {
    public BluetoothDevice bluetoothDevice;

    public BluetoothDeviceItem(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public String getDeviceAddress() {
        return bluetoothDevice.getAddress();
    }

    public String getDeviceName() {
        return bluetoothDevice.getName();
    }
}
