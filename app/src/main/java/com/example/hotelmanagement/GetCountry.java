package com.example.hotelmanagement;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetCountry extends AsyncTask<Integer, Integer, String> {
    Double mlat,mlong;
    String country=null;
    public boolean workdone=false;
    public GetCountry(Double lat,Double longi){
        this.mlat=lat;
        this.mlong=longi;
    }


    @Override
    protected String doInBackground(Integer... integers) {
        try {
            String URLLogin = "https://nominatim.openstreetmap.org/reverse?format=json&lat="+this.mlat+"&lon="+this.mlong+"&zoom=18&addressdetails=1";
            URL url = new URL(URLLogin);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json + "\n");
            }
            this.country=parsing(sb.toString());
            return parsing(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        this.country=s;
        this.workdone=true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }



    private String parsing(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.getString("address"));
        String[] jsonArray =jsonObject.getString("address").split("country_code");
        System.out.println(jsonArray.length);

        String obj = jsonArray[1].replace("\"","").replace(":","").replace(",","").replace("}","");
        System.out.println(obj);


        //if(obj.equals("United States"))
        return obj;
        //return null;
        /*
        For the app purpose which is about covid-19 , we have only 3 hotels located in china, france and england . if the user is not located in one of those places, he wont be able to book"
         */





    }
}
