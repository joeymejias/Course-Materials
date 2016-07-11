package ly.generalassemb.drewmahrt.listviewsandadapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList<String> mNameList;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNameList = new LinkedList<>();
        mNameList.add("Adam");
        mNameList.add("Brad");
        mNameList.add("Charlie");
        mNameList.add("David");
        mNameList.add("Frank");


        //Create an ArrayAdapter
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mNameList);
        TextView textView1 = (TextView) findViewById(android.R.id.text1);

        //Get the reference to the ListView and Set the adapter of the ListView
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);


        //In the onClick method, remove the first item from the list if any exist and refresh the updated list on the screen
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = (TextView) findViewById(android.R.id.text1);
                if(!mAdapter.isEmpty()){

                    mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Nothing here, bro.", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}
