package com.vathsav.bloobot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.vathsav.bloobot.R;
import com.vathsav.bloobot.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (Constants.bluetoothAdapter != null) {
                        // Check if bluetooth is enabled
                        if (!Constants.bluetoothAdapter.isEnabled()) {
                            startActivityForResult(Constants.intentEnableBluetooth, Constants.requestCodeBluetoothEnable);
                        } else {
                            startActivity(Constants.intentMainActivity);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), Constants.toastDeviceDoesNotSupportBluetooth, Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.requestCodeBluetoothEnable) {
            if (resultCode == RESULT_OK) {
                Log.v(Constants.logTag, Constants.logVerboseBluetoothEnabled);
                startActivity(Constants.intentMainActivity);
            } else if (resultCode == RESULT_CANCELED) {
                Log.v(Constants.logTag, Constants.logVerboseBluetoothRequestCancelled);
            }
        }
    }
}
