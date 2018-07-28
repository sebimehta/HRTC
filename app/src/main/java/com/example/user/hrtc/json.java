package com.example.user.hrtc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class json {

    public List<SuggestGetSet> getParseJsonWCF(String DataName)
    {
        List<SuggestGetSet> Listdata = new ArrayList<SuggestGetSet>();

        try {

            URL url= new URL("https://api.myjson.com/bins/lcwf2");   // Enter HRTC  webservice here
            URLConnection uc = url.openConnection();

               //    input = conn.getInputStream();
              // InputStreamReader reader =
             //     new InputStreamReader(input, "UTF-8");
            BufferedReader buffer = new BufferedReader(new
                    InputStreamReader(uc.getInputStream()));
            String line = buffer.readLine();

            try{
                line=line.toString().replace("\\","");
                line=line.toString().replace(":\"[",":[");
                line=line.toString().replace("]\"","]");

                JSONObject jsonResponse = new JSONObject(line);
                JSONArray jsonArray = jsonResponse.getJSONArray("GetLocationlistAsJSONResult");
                  //  temp = new String[jsonArray.length()];

             //   String abc=jsonArray.toString();
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject r = jsonArray.getJSONObject(i);

                    Listdata.add(new SuggestGetSet
                            (r.getString("Location_Id"),r.getString("Location_Name")));
                        //temp[i] = r.getString("Location_Name");
                }
            }
            catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Listdata;
    }
}
