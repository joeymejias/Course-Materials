package ly.generalassemb.drewmahrt.syncadapterexample;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import ly.generalassemb.drewmahrt.syncadapterexample.models.SearchResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by joey on 8/22/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = "Sync Adapter";
    private ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        mContentResolver.delete(NewsContentProvider.CONTENT_URI,null,null);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.nytimes.com/svc/news/v3/content/all/all/all.json?limit=20&api-key=f07deba78f60444ea0a67ad478c089f3")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            mContentResolver.delete(NewsContentProvider.CONTENT_URI,
                    null,
                    null);

            Gson gson = new Gson();
            SearchResult result = gson.fromJson(response.body().string(),SearchResult.class);

            for (int i = 0; i < result.getResults().size(); i++) {
                ContentValues values = new ContentValues();
                values.put(NewsDBHelper.COLUMN_TITLE,result.getResults().get(i).getTitle());
                mContentResolver.insert(NewsContentProvider.CONTENT_URI,values);
                Log.d(TAG,"Latest story: "+result.getResults().get(i).getTitle());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
