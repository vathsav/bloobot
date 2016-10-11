package com.vathsav.bloobot.activity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vathsav.bloobot.R;
import com.vathsav.bloobot.model.BluetoothDeviceAdapter;
import com.vathsav.bloobot.model.BluetoothDeviceItem;
import com.vathsav.bloobot.utils.Constants;

import java.util.ArrayList;
import java.util.Set;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_bluetooth_device_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Query paired devices
        Set<BluetoothDevice> pairedDevices = Constants.bluetoothAdapter.getBondedDevices();

        final ArrayList<BluetoothDeviceItem> listOfBluetoothDevices = new ArrayList<>();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : pairedDevices) {
                Toast.makeText(getApplicationContext(), bluetoothDevice.getName(), Toast.LENGTH_SHORT).show();
                listOfBluetoothDevices.add(new BluetoothDeviceItem(bluetoothDevice));
            }
        }

        recyclerView.setAdapter(new BluetoothDeviceAdapter(listOfBluetoothDevices, getApplicationContext()));
    }
}
