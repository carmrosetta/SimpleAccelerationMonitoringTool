package ing.unipi.it.simpleaccelerationmonitoringtool;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToolsActivity extends Activity implements View.OnClickListener {


    ListView mListView;

    Button btnShowCheckedItems;


    ArrayList<Tool> mTools;

    MultiSelectionAdapter<Tool> mAdapter;


    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tools);



        bindComponents();

        init();

        addListeners();

    }


    private void bindComponents() {

// TODO Auto-generated method stub

        mListView = (ListView) findViewById(android.R.id.list);

        btnShowCheckedItems = (Button) findViewById(R.id.btnShowCheckedItems);

    }



    private void init() {

// TODO Auto-generated method stub

        mTools = new ArrayList<Tool>();

        mTools.add(new Tool("Sensor Data Logger"));

        mTools.add(new Tool("Gait Recognition"));

        mTools.add(new Tool("Posture Detection"));

        mTools.add(new Tool("Impact Detection"));


        mAdapter = new MultiSelectionAdapter<Tool>(this, mTools);

        mListView.setAdapter(mAdapter);

    }



    private void addListeners() {

// TODO Auto-generated method stub

        btnShowCheckedItems.setOnClickListener(this);

    }


    @Override

    public void onClick(View v) {

// TODO Auto-generated method stub


        if(mAdapter != null) {

            ArrayList<Tool> mArrayTools = mAdapter.getCheckedItems();

            Log.d(MainActivity.class.getSimpleName(), "Selected Items: " + mArrayTools.toString());

            Toast.makeText(getApplicationContext(), "Selected Items: " + mArrayTools.toString(), Toast.LENGTH_LONG).show();

        }

    }


}
