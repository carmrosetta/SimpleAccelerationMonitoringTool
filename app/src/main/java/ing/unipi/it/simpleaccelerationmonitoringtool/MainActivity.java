package ing.unipi.it.simpleaccelerationmonitoringtool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

    Spinner phonePosition, samplingSpeed;
    Button nextBtn;
    String smartphonePosition = "";
    String speed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGui();


    }

    public void initGui() {
        phonePosition = (Spinner) findViewById(R.id.spinnerPosition);
        samplingSpeed = (Spinner) findViewById(R.id.spinnerSpeed);
        nextBtn = (Button) findViewById(R.id.buttonNext);

        fillSpinner(phonePosition, R.array.smart_phone_position_array);
        fillSpinner(samplingSpeed, R.array.delay_rates_array);


        samplingSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String[] speeds = getResources().getStringArray(R.array.delay_rates_array);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                speed = speeds[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        phonePosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String[] positions = getResources().getStringArray(R.array.smart_phone_position_array);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                smartphonePosition = positions[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), smartphonePosition, Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), speed, Toast.LENGTH_LONG).show();
                //TODO far partire l'activity successiva
                Intent intent = new Intent(getApplicationContext(), ToolsActivity.class);
                startActivity(intent);
            }
        });



    }

    public void fillSpinner(Spinner spinner, int stringArrayId) {

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,  stringArrayId , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }




//  da questi menu potrei andare all'inserimento dei dati da parte dell'utente
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
