package com.amati.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private ImageView image;
    private float currentDgree = 0f;
    private SensorManager sensorManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageViewCompass);
        textView = findViewById(R.id.tvHeading);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float degree = Math.round(sensorEvent.values[0]);

        textView.setText("Heading: " + Float.toString(degree) + " degrees");

        RotateAnimation ra = new RotateAnimation(
                currentDgree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        ra.setDuration(210);
        ra.setFillAfter(true);

        image.setAnimation(ra);
        currentDgree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}