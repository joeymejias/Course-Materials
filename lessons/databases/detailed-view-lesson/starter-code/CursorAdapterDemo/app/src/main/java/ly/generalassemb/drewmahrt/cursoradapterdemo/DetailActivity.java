package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.simple_expandable_list_item_1);

        int listId = getIntent().getIntExtra(ExampleSQLiteOpenHelper.COL_ID, 0);

        ExampleSQLiteOpenHelper helper = ExampleSQLiteOpenHelper.getInstance(this);

        String description = helper.getColItemDescription(listId);

        TextView descriptionTextView = (TextView) findViewById(android.R.id.text1);

        descriptionTextView.setText(description);
    }
}
