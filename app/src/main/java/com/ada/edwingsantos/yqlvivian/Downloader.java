package com.ada.edwingsantos.yqlvivian;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by edwingsantos on 3/14/16.
 */
public class Downloader extends AsyncTask<String, Integer, ArrayList> {

    ResultsActivity activity;

    public Downloader(ResultsActivity activity){
        this.activity = activity;
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        String yqlURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%27"+params[1]+"%27%20and%20query%3D%27"+params[0]+"%27&format=json&callback=";
        ArrayList<Results> resultsArrayList = new ArrayList<>();
        try {
            URL theUrl = new URL(yqlURL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(theUrl.openConnection().getInputStream(), "UTF-8"));
            String json = bufferedReader.readLine();

            JSONObject jsonObject = new JSONObject(json);
            JSONObject queryObj = jsonObject.getJSONObject("query");
            JSONObject resultsObject = queryObj.getJSONObject("results");
            JSONArray resultsArray = resultsObject.getJSONArray("Result");

            for(int i =0; i < resultsArray.length(); i++){
                JSONObject singleObj = resultsArray.getJSONObject(i);

                String title = singleObj.getString("Title");
                String address = singleObj.getString("Address");
                String city = singleObj.getString("City");
                String state = singleObj.getString("State");
                String phone = singleObj.getString("Phone");
                double latitude = Double.parseDouble(singleObj.getString("Latitude"));
                double longitude = Double.parseDouble(singleObj.getString("Longitude"));
                String distance = singleObj.getString("Distance");
                String businessUrl = singleObj.getString("Url");

                Results results = new Results(title,  address,  businessUrl,  city,  distance,  latitude,  longitude,  phone,  state);

                resultsArrayList.add(results);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultsArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        activity.drawListView(arrayList);
        //Log.d("List: ", "" + arrayList.toString());
    }
}
