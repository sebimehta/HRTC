package com.example.user.hrtc;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class stop_fetch extends AsyncTask<Void ,Void ,Void>
{
    String info ="";

    String parsed1 = "";
    String parsed2 ="";
    String parsed3 ="";
    String parsed4 ="";

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        // Showing progress dialog

    }
    @Override
    protected Void doInBackground(Void... params)
    {
        try {
            // URL is created
            // To fetch the data
           URL url=new URL("https://api.myjson.com/bins/zckfu");

            // Create HTTP  URL Connection here
            // Opening connecting using this URL
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

            // Create a Input stream
            // Stream is provided in connection
            // stream lets us read or write data
            // Here we are only Reading the data
            InputStream inputStream=httpURLConnection.getInputStream();

            //Create BufferReader
            // Buffer is now connected to Stream
            // Reads data from Input Stream
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            //for Reading data only
            String line="";

            while (line!=null)
            {
                // Read each and every line of JSON File
                line=bufferedReader.readLine();
                info=info+line;
               // abc=abc+line;

            }

            // For parsing DATA
            JSONArray JA=new JSONArray(info);
            for(int i =0 ;i <JA.length(); i++)
            {
                JSONObject JO = (JSONObject) JA.get(i);

                parsed1 =JO.get("BusNumber") + "\n";
                parsed2 = JO.get("RouteNo") + "\n";
                parsed3 = JO.get("Destination") + "\n";
                parsed4 = JO.get("ExpTime") + "\n";
            }

            // For parsing DATA
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    // Can change the UI here
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Stop_Bus.data1.setText(this.parsed1);
        Stop_Bus.data2.setText(this.parsed2);
        Stop_Bus.data3.setText(this.parsed3);
        Stop_Bus.data4.setText(this.parsed4);


    }

}
