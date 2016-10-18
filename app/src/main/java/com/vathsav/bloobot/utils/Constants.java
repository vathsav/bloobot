package com.vathsav.bloobot.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import io.palaima.smoothbluetooth.SmoothBluetooth;

/**
 * Created by vathsav on 10/10/16.
 */

public class Constants {

    public static SmoothBluetooth bluetoothDevice;

    // Bluetooth configuration variables
    public static final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    // Intents
    public static final Intent intentMainActivity = new Intent("com.vathsav.bloobot.MAIN");
    public static final Intent intentConfigurationActivity = new Intent("com.vathsav.bloobot.CONFIGURATION");
    public static final Intent intentEnableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

    // Activity result request codes
    public static final int requestCodeBluetoothEnable = 1000;

    // Arduino pins
    public static final int pinForward = 1;

    // Toasts
    public static final String toastDeviceDoesNotSupportBluetooth = "Device does not support bluetooth!";

    // Log tags
    public static final String logTag = "Bloobot";

    // Log content
    public static final String logVerboseBluetoothEnabled = "Bluetooth enabled";
    public static final String logVerboseBluetoothRequestCancelled = "Bluetooth request cancelled";
}
