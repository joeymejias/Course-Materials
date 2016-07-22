package com.example.joeymejias.toolbarpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_too);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.child_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onNavigateUp();
                return true;
            case R.id.action_share:
                Toast.makeText(ChildActivity.this, "Share Clicked in ChildActivity", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_map:
                startActivity(new Intent(this, AnotherChildActivity.class)); //Starts AnotherChildActivity when map button selected
                Toast.makeText(ChildActivity.this, "Map Clicked in ChildActivity..." +
                        "\nI'm a map!\n" +
                        "I'm a map!\n" +
                        "I'm a map!",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(ChildActivity.this, "Settings Clicked in ChildActivity", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
