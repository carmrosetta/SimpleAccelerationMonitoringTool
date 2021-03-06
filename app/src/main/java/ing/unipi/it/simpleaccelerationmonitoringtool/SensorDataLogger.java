package ing.unipi.it.simpleaccelerationmonitoringtool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.io.File;

/**
 * Created by carmen on 14/10/14.
 */
public class SensorDataLogger implements Runnable, SensorEventListener {

    long lastUpdate = 0l ;
    long count = 0l;


    File directory, file;

    public SensorDataLogger() {

        directory = Utilities.createDirectory("PROVE_CAMPIONI");
        file = Utilities.createFile(directory, "file_logger.txt");
    }

    @Override
    public void run() {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

      //  Log.d("SensorDataLogger","Sample acquired  "+event.values[0]);
       long sampleTime = System.currentTimeMillis();
        getData(event, sampleTime);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void getData(SensorEvent event, long sampleTime) {
        float[] values = event.values;

        if(lastUpdate == 0) {
            lastUpdate = sampleTime;
        }
        long diff = sampleTime - lastUpdate;
        count+=diff;
        lastUpdate = sampleTime;
        String timestamp =  Utilities.getTimeInSeconds(count);

        String sensedValues = "";

        for(int j = 0; j < values.length; j++) {
            sensedValues += ", "+values[j];
        }

        //Utilities.writeData(file, timestamp+sensedValues+"\n");
        //Utilities.writeData(file, event.timestamp+sensedValues+"\n");
       // Utilities.writeData(file, event.timestamp +sensedValues+"\n");
        Utilities.writeData(file, Utilities.getTimeInSeconds(count) +sensedValues+"\n");

    }
}
