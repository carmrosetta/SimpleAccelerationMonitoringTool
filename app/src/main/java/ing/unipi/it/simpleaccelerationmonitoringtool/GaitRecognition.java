package ing.unipi.it.simpleaccelerationmonitoringtool;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.io.File;

/**
 * Created by carmen on 15/10/14.
 */
public class GaitRecognition  implements Runnable, SensorEventListener {

    long lastUpdate = 0l;
    long count = 0l;




    File directory, file;

    public GaitRecognition() {



        directory = Utilities.createDirectory("PROVE_CAMPIONI");
        file = Utilities.createFile(directory, "file_gait.txt");
    }

    @Override
    public void run() {



    }

    @Override
    public void onSensorChanged(SensorEvent event) {

       // Log.e("GaitRecognition", "Sample acquired at "+event.values[0]);
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


        // Log.e("Sensor data logger","Sample acquired at "+timeStamp+" " +event.values[0]);
       Utilities.writeData(file, Utilities.getTimeInSeconds(count) +sensedValues+"\n");
       // Utilities.writeData(file, event.timestamp +sensedValues+"\n");
       // Utilities.writeData(file, event.timestamp+sensedValues+"\n");



    }
}

