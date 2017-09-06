package com.example.dawid.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;

public class Zblizenie extends AppCompatActivity implements SensorEventListener  {
    SensorManager manager;
    private ImageView iv;
    private Sensor mSensor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zblizenie);

        manager=(SensorManager)getSystemService(SENSOR_SERVICE);


        TextView textViev=(TextView) findViewById(R.id.pole);
        textViev.setText(manager.getSensorList(Sensor.TYPE_ALL).toString());


        /// Wybieramy czujnik zbliżeniowy
        mSensor= manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        iv = (ImageView)findViewById(R.id.imageView1);
        /// ustawiamy słuchacza
        manager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.values[0] == 0){
            iv.setImageResource(R.drawable.blisko);
        }else{
            iv.setImageResource(R.drawable.daleko);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
