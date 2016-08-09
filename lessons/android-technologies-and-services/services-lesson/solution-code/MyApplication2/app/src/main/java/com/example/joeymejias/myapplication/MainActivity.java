package com.example.joeymejias.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            Notification

            1. Intent to launch Activity we want to send the user to.
            2. PendingIntent so it can be launched later on in the Notification.
            3. Use NotificationCompat.Builder to build our notification.
            4. Get NotificationsManager to notify the user of our notification.

         */

        // 1.
        Intent intent = new Intent(MainActivity.this, MainActivity.class);

        // 2.
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1234, intent, 0);

        // 3.
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        notificationBuilder.setContentTitle("My Notification");
        notificationBuilder.setContentText("My Notification Text");
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(Notification.PRIORITY_MAX);
        notificationBuilder.setContentIntent(pendingIntent);
        // additional action does not auto cancel
        // notificationBuilder.addAction(android.R.drawable.btn_plus, "Ahaha!", pendingIntent);

        //BigPictureStyle Notification
        NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
        pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), android.R.drawable.sym_def_app_icon));

        //BigTextStyle Notification
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle.bigText(getString(R.string.herpderp));
        textStyle.setBigContentTitle("My Big Notification!");
        textStyle.setSummaryText("By: Felicia");

        notificationBuilder.setStyle(pictureStyle);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                // 4.
                NotificationManagerCompat.from(MainActivity.this).notify(9876, notificationBuilder.build());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
