package com.vathsav.bloobot.activity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.vathsav.bloobot.R;
import com.vathsav.bloobot.utils.Constants;

import java.util.Set;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        // Query paired devices
        Set<BluetoothDevice> pairedDevices = Constants.bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : pairedDevices) {
                Toast.makeText(getApplicationContext(), bluetoothDevice.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
