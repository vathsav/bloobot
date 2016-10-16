package com.vathsav.bloobot.model;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vathsav.bloobot.R;

/**
 * Created by vathsav on 11/10/16.
 */

public class BluetoothDeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    Context context;
    BluetoothDevice bluetoothDevice;
    TextView bluetoothDeviceName;
    TextView bluetoothDeviceAddress;
    Button bluetoothDeviceConnect;

    public BluetoothDeviceViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);
        bluetoothDeviceName = (TextView) itemView.findViewById(R.id.card_bluetooth_item_device_name);
        bluetoothDeviceAddress = (TextView) itemView.findViewById(R.id.card_bluetooth_item_device_address);
        bluetoothDeviceConnect = (Button) itemView.findViewById(R.id.card_bluetooth_item_connect);
    }

    @Override
    public void onClick(View view) {
        // TODO: 11/10/16 Attempt to connect to device as a client.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                } catch (Exception ex) {

                }
            }
        }).start();
    }
}