package com.example.kevin.comp3074_assignment3;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    TextView textView,sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView = findViewById(R.id.txtReading);

        Button btn = findViewById(R.id.btnGoMaps);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        List<Sensor> allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);




    }

    @Override
    protected void onResume() {
        super.onResume();


        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] v = event.values;
        float temp = v[0];


        if(temp <= 0) {
            textView.setBackgroundColor(Color.BLUE);
            textView.setText(""+temp);
        }
        else if(temp > 10 && temp < 50) {
            textView.setBackgroundColor(Color.YELLOW);
            textView.setText(""+temp);

        }
        else if(temp >= 50) {
            textView.setBackgroundColor(Color.RED);
            textView.setText(""+temp);

        }

        textView.setText(""+temp);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
