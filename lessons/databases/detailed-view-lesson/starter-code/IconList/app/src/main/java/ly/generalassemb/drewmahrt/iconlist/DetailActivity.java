package ly.generalassemb.drewmahrt.iconlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setContentView(android.R.layout.simple_expandable_list_item_1);

        int listId = getIntent().getIntExtra(IconSQLiteOpenHelper.COL_ID, 0);

        IconSQLiteOpenHelper helper = IconSQLiteOpenHelper.getInstance(this);

        String description = helper.getColItemDescription(listId);

        TextView descriptionTextView = (TextView) findViewById(android.R.id.text1);

        descriptionTextView.setText(description);
    }
}
