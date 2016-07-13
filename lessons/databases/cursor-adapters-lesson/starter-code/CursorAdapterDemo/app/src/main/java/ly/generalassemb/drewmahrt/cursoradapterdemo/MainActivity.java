package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ly.generalassemb.drewmahrt.cursoradapterdemo.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.example_list_view);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        Cursor cursor = ExampleSQLiteOpenHelper
                .getInstance(MainActivity.this)
                .getExampleList();

        CursorAdapter adapter = new CursorAdapter(MainActivity.this, cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context)
                        .inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                int index = cursor.getColumnIndex(ExampleSQLiteOpenHelper.COL_ITEM_NAME);

                textView.setText(cursor.getString(index));
            }
        };

        CursorAdapter customAdapter = new CursorAdapter(MainActivity.this, cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context)
                        .inflate(R.layout.list_item_layout, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView nameTextView = (TextView)
                        view.findViewById(R.id.textview_name);
                TextView descriptionTextView = (TextView)
                        view.findViewById(R.id.textview_description);

                int nameIndex = cursor.getColumnIndex(ExampleSQLiteOpenHelper.COL_ITEM_NAME);
                int descriptionIndex = cursor.getColumnIndex(ExampleSQLiteOpenHelper.COL_ITEM_DESCRIPTION);

                nameTextView.setText(cursor.getString(nameIndex));
                descriptionTextView.setText(cursor.getString(descriptionIndex));

            }
        };

        String[] columnNames = new String[]{ExampleSQLiteOpenHelper.COL_ITEM_NAME};
        int[] viewNames = new int[]{android.R.id.text1};

        SimpleCursorAdapter simpleCursorAdapter =
                new SimpleCursorAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        cursor,
                        columnNames,
                        viewNames,
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        String[] customColumnNames = new String[]{
                ExampleSQLiteOpenHelper.COL_ITEM_NAME,
                ExampleSQLiteOpenHelper.COL_ITEM_DESCRIPTION
        };

        int[] customViewNames = new int[]{R.id.textview_name, R.id.textview_description};

        SimpleCursorAdapter customSimpleCursorAdapter =
                new SimpleCursorAdapter(MainActivity.this,
                        R.layout.list_item_layout,
                        cursor,
                        customColumnNames,
                        customViewNames,
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

//      mListView.setAdapter(adapter); //First Adapter
//      mListView.setAdapter(customAdapter); //Second Adapter
        mListView.setAdapter(customSimpleCursorAdapter);

    }
}
