package ly.generalassemb.jobschedulerlesson;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
<<<<<<< HEAD

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by joey on 8/12/16.
 */
public class MyJobService extends JobService {

    private AsyncTask <Void, Void, String> mTask;
=======
import android.os.PersistableBundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by charlie on 8/12/16.
 */
public class MyJobService extends JobService {

    private AsyncTask<Void, Void, String> mTask;
>>>>>>> upstream/master

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

<<<<<<< HEAD
        jobParameters.getExtras();

        mTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(System.currentTimeMillis());

                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

                String newText = "Time " + timeFormat.format(calendar.getTime());

                return newText;
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                DataSingleton.getInstance().updateMyText(string);
                jobFinished(jobParameters, false);
            }
        };
        mTask.execute();
        return true;
=======
        mTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                PersistableBundle bundle = jobParameters.getExtras();
                int periodicTime = bundle.getInt(MainActivity.PERIODIC_TIME_KEY);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());

                SimpleDateFormat timeFormat;

                switch (periodicTime) {
                    default:
                    case 5:
                        timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
                        break;
                    case 7:
                        timeFormat = new SimpleDateFormat("MMM d, yyyy hh:mm:ss", Locale.getDefault());
                        break;
                }

                return "Time: " + timeFormat.format(calendar.getTime());
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                DataSingleton.getInstance().updateMyText(s);

                jobFinished(jobParameters, false);
            }
        };

        mTask.execute();

        return true; // true = I will cancel the service; false = system should cancel it
>>>>>>> upstream/master
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
<<<<<<< HEAD
        if(mTask.getStatus().equals(AsyncTask.Status.RUNNING)){
            mTask.cancel(false);
        }
        return false;
=======

        if (mTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            mTask.cancel(false);
        }
        return false; // false = done; true = need to be rescheduled
>>>>>>> upstream/master
    }
}
