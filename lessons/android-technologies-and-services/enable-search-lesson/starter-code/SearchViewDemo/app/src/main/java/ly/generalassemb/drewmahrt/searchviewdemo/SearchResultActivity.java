package ly.generalassemb.drewmahrt.searchviewdemo;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            int num = NumbersSQLiteHelper.getInstance(this).searchNumbers(query).getCount();
            String finalString = "Number of " + query + "s in the database: " + num;
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(finalString);
        }
    }
}
