package com.vathsav.bloobot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vathsav.bloobot.R;
import com.vathsav.bloobot.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 10/10/16 Include a Bluetooth state changed listener.

        Button buttonForward = (Button) findViewById(R.id.buttonForward);
        Button buttonReverse = (Button) findViewById(R.id.buttonReverse);
        Button buttonLeft = (Button) findViewById(R.id.buttonLeft);
        Button buttonRight = (Button) findViewById(R.id.buttonRight);
        Button buttonConfigure = (Button) findViewById(R.id.buttonConfigure);

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Text", Toast.LENGTH_SHORT).show();
            }
        });

        buttonReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Constants.intentConfigurationActivity);
            }
        });
    }
}
