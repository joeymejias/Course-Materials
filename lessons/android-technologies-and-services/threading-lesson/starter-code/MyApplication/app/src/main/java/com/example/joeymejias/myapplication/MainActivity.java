package com.example.joeymejias.myapplication;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.textView);

        ConnectivityManager conMng = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMng.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            //Do internet stuff
            new DownloadTask().execute("https://api.github.com/users/yuliya-kaleda/repos");
        } else {
            //No network connection available
            Toast.makeText(MainActivity.this, "Internet is broken, damn Kardashians...", Toast.LENGTH_SHORT).show();
        }
    }

    public String downloadUrl(String myUrl) throws IOException, JSONException{
        InputStream is = null;
        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            is = conn.getInputStream();

            String contentAsString = readIt(is);
            String parsedJson = parseJson(contentAsString);
            return parsedJson;
        }finally {
            if(is != null){
                is.close();
            }
        }
    }

    private String readIt(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String read;

        while((read = br.readLine()) != null){
            sb.append(read);
        }
        return sb.toString();
    }

    private String performPost(String myUrl) throws IOException, JSONException{
        DataOutputStream os = null;
        InputStream is = null;

        try{
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String urlParameters = "param1=a&param2=b";
            byte[] postData = urlParameters.getBytes(Charset.forName("UTF-8"));
            int postDataLength = postData.length;

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","aplication/x-www.form-urlencoded");
            conn.setRequestProperty("charset","utf-8");
            conn.setRequestProperty("Content-Length",Integer.toString(postDataLength));

            os = new DataOutputStream(conn.getOutputStream());
            os.write(postData);
            os.flush();

            is = conn.getInputStream();
            return readIt(is);
        } finally{
            if(is != null){
                is.close();
            }
            if(os != null){
                os.close();
            }
        }
    }

    private String parseJson(String contentAsString) throws JSONException{
        String repoList = "";
        JSONArray array = new JSONArray(contentAsString);
        for (int i = 0; i < array.length(); i++) {
            JSONObject repo = array.getJSONObject(i);
            repoList += repo.get("name");
            repoList += "\n";
        }
        return repoList;
    }

    private class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                return downloadUrl(strings[0]);
                //return performPost(strings[0]);
            } catch (JSONException e) {
                e.printStackTrace();
                return "Unable to retrieve... Bad URL?";
            } catch (IOException e) {
                e.printStackTrace();
                return "JSON Parsing Issue";
            }
        }

        @Override
        protected void onPostExecute(String s){
            mText.setText(s);
        }
    }
}
