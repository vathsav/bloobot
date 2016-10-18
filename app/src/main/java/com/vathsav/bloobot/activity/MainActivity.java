package com.vathsav.bloobot.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vathsav.bloobot.R;
import com.vathsav.bloobot.model.BluetoothDeviceItem;
import com.vathsav.bloobot.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.palaima.smoothbluetooth.Device;
import io.palaima.smoothbluetooth.SmoothBluetooth;

public class MainActivity extends AppCompatActivity implements SmoothBluetooth.Listener {


    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 10/10/16 Include a Bluetooth state changed listener.
        Constants.bluetoothDevice = new SmoothBluetooth(
                getApplicationContext(),
                SmoothBluetooth.ConnectionTo.OTHER_DEVICE,
                SmoothBluetooth.Connection.INSECURE,
                this
        );

        Button buttonForward = (Button) findViewById(R.id.button_forward);
        Button buttonReverse = (Button) findViewById(R.id.button_reverse);
        Button buttonLeft = (Button) findViewById(R.id.button_left);
        Button buttonRight = (Button) findViewById(R.id.button_right);
        Button buttonConfigure = (Button) findViewById(R.id.button_configure);

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.bluetoothDevice.send("1", false);
                if (Constants.bluetoothDevice.isConnected()) {
                    Toast.makeText(getApplicationContext(), "Sending 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Bluetooth not connected!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.bluetoothDevice.send("0", false);
                Toast.makeText(getApplicationContext(), "Sending 0", Toast.LENGTH_SHORT).show();
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.bluetoothDevice.tryConnection();
//                Constants.bluetoothDevice.send("left", false);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.bluetoothDevice.send("right", false);
            }
        });

        buttonConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(Constants.intentConfigurationActivity);
                Constants.bluetoothDevice.tryConnection();

                Set<BluetoothDevice> pairedDevices = Constants.bluetoothAdapter.getBondedDevices();

                final ArrayList<BluetoothDeviceItem> listOfBluetoothDevices = new ArrayList<>();

                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice bluetoothDevice : pairedDevices) {
                        listOfBluetoothDevices.add(new BluetoothDeviceItem(bluetoothDevice));
                    }
                }

                new ConnectThread(listOfBluetoothDevices.get(1).bluetoothDevice).run();
            }
        });
    }

    @Override
    public void onBluetoothNotSupported() {
        Toast.makeText(getApplicationContext(), "Bluetooth not supported", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBluetoothNotEnabled() {
        Toast.makeText(getApplicationContext(), "Bluetooth not enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnecting(Device device) {
        Toast.makeText(getApplicationContext(), "Establishing bluetooth connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(Device device) {
        Toast.makeText(getApplicationContext(), "Connected to " + device.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(getApplicationContext(), "Bluetooth disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(Device device) {
        Toast.makeText(getApplicationContext(), "Bluetooth connection failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDiscoveryStarted() {
        Toast.makeText(getApplicationContext(), "Bluetooth discovery started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDiscoveryFinished() {
        Toast.makeText(getApplicationContext(), "Bluetooth discovery finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoDevicesFound() {
        Toast.makeText(getApplicationContext(), "No devices found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDevicesFound(List<Device> deviceList, SmoothBluetooth.ConnectionCallback connectionCallback) {
        Toast.makeText(getApplicationContext(), "something", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataReceived(int data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constants.bluetoothDevice.stop();
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            bluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                Toast.makeText(getApplicationContext(), "Connecting..", Toast.LENGTH_SHORT).show();
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                Log.e(Constants.logTag, connectException.getMessage());
                Toast.makeText(getApplicationContext(), "Ain't working!", Toast.LENGTH_SHORT).show();
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                    Toast.makeText(getApplicationContext(), closeException.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
//            manageConnectedSocket(mmSocket);
        }

        /**
         * Will cancel an in-progress connection, and close the socket
         */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
