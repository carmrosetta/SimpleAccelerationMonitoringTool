package ing.unipi.it.simpleaccelerationmonitoringtool;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.io.File;

public class AccelerationSamplingService extends Service  {

    public static boolean serviceRunning = false;

    public static final int SCREEN_OFF_RECEIVER_DELAY = 500;

    public static final String TAG = AccelerationSamplingService.class.getName();

    private PowerManager.WakeLock mWakeLock = null;

    private NotificationManager notificationManager;

    SensorManager sensorManager;







    public AccelerationSamplingService() {
    }


    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("service started", "service started");

        serviceRunning = true;

        SensorDataLogger sensorDataLogger = new SensorDataLogger();
        GaitRecognition gaitRecognition = new GaitRecognition();

        sensorManager.registerListener(sensorDataLogger, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(gaitRecognition, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);

        new Thread(sensorDataLogger).start();
        new Thread(gaitRecognition).start();

        mWakeLock.acquire();

        return START_REDELIVER_INTENT;
    }



    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        PowerManager manager =
                (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Utilities.showNotification(this, notificationManager, "Service running", MainActivity.class);


    }



    public void onDestroy() {

        unregisterReceiver(actionScreenOffReceiver);

        mWakeLock.release();

        notificationManager.cancelAll();

        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    public BroadcastReceiver actionScreenOffReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            if (!intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                return;
            }
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                   // unregisterListener();
                   // registerListeners();
                    notificationManager.cancelAll();
                    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Utilities.showNotification(getApplicationContext(), notificationManager, "Service running", ToolsActivity.class);

                }
            };

            new Handler().postDelayed(runnable, SCREEN_OFF_RECEIVER_DELAY);

        }
    };


}
