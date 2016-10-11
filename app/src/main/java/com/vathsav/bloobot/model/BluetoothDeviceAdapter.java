package com.vathsav.bloobot.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vathsav.bloobot.R;

import java.util.List;

/**
 * Created by vathsav on 11/10/16.
 */
public class BluetoothDeviceAdapter extends RecyclerView.Adapter<BluetoothDeviceViewHolder> {
    private final List<BluetoothDeviceItem> listOfBluetoothDevices;
    private final Context context;

    public BluetoothDeviceAdapter(List<BluetoothDeviceItem> listOfBluetoothDevices, Context context) {
        this.listOfBluetoothDevices = listOfBluetoothDevices;
        this.context = context;
    }

    @Override
    public BluetoothDeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bluetooth_device, null);
        return new BluetoothDeviceViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(BluetoothDeviceViewHolder holder, int position) {
        holder.bluetoothDeviceName.setText(listOfBluetoothDevices.get(position).getDeviceName());
        holder.bluetoothDeviceAddress.setText(listOfBluetoothDevices.get(position).getDeviceAddress());
        holder.bluetoothDevice = listOfBluetoothDevices.get(position).bluetoothDevice;
    }

    @Override
    public int getItemCount() {
        return listOfBluetoothDevices.size();
    }
}
