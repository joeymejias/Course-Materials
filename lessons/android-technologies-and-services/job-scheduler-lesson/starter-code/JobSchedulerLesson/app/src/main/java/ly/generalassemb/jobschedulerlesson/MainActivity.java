package ly.generalassemb.jobschedulerlesson;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DataSingleton.DataChangeListener{

    TextView oldText, newText;
    private JobScheduler scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataSingleton.getInstance().setListener(this);

        oldText = (TextView) findViewById(R.id.textview_1);
        newText = (TextView) findViewById(R.id.textview_2);

        //This is where we will instantiate our JobScheduler and JobInfo objects
        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                MyJobService.class.getName()))
                .setExtras(new PersistableBundle())
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .setPeriodic(5_000) //This ensures the job runs every 5 seconds
                .build();

        JobInfo jobInfo2 = new JobInfo.Builder(2, new ComponentName(getPackageName(),
                MyJobService2.class.getName()))
                .setExtras(new PersistableBundle())
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .setPeriodic(7_000) //This ensures the job runs every 5 seconds
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(jobInfo);
        jobScheduler.schedule(jobInfo2);

    }

    @Override
    public void onDataChanged(String oldTextString) {
        if(newText.getText().length() != 0){
            oldText.setText(oldTextString);
        }
        newText.setText(DataSingleton.getInstance().getMyText());
    }
}
