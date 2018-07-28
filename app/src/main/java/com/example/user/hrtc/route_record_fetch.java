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



public class route_record_fetch extends AsyncTask<Void ,Void ,Void>
{
    String info ="";

    String parsed1 = "";
    String parsed2 ="";
    String parsed3 ="";
    String parsed4="";
    String parsed5="";
    String parsed6="";
    String parsed7="";

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
            URL url=new URL("https://api.myjson.com/bins/1gcate");

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
                parsed3 = JO.get("from") + "\n";
                parsed4 = JO.get("to") + "\n";
                parsed5 = JO.get("bustype") + "\n";
                parsed6 = JO.get("vehicle") + "\n";
                parsed7 = JO.get("status") + "\n";
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
        Route_Record.info1.setText(this.parsed1);
        Route_Record.info2.setText(this.parsed2);
        Route_Record.info3.setText(this.parsed3);
        Route_Record.info4.setText(this.parsed4);
        Route_Record.info5.setText(this.parsed5);
        Route_Record.info6.setText(this.parsed6);
        Route_Record.info7.setText(this.parsed7);




    }

}
