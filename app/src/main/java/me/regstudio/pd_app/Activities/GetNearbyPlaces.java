package me.regstudio.pd_app.Activities;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

import me.regstudio.pd_app.Fragments.MapFragment;


public class GetNearbyPlaces extends AsyncTask<Object,String,String>
{
    GoogleMap mMap;
    String url;
    InputStream is;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;

    public GetNearbyPlaces(MapFragment mapFragment) {

    }

    @Override
    protected String doInBackground(Object... params) {
        mMap = (GoogleMap) params[0];
        url = (String)params[1];

        try{
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) myurl.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            stringBuilder = new StringBuilder();

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

            data = stringBuilder.toString();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected  void onPostExecute(String s)
    {
        try{
            JSONObject parentObject = new JSONObject(s);
            JSONArray resultsArray = parentObject.getJSONArray("results");

            for(int i = 0; i< resultsArray.length(); i++){
                JSONObject jsonObject = resultsArray.getJSONObject(i);
                JSONObject locationOB = jsonObject.getJSONObject("geometry").getJSONObject("location");

                String latitude = locationOB.getString("lat");
                String longitude = locationOB.getString("lng");

                JSONObject nameObject = resultsArray.getJSONObject(i);

                String name_hospital = nameObject.getString("name");
                String vicinity = nameObject.getString("vicinity");


                LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(name_hospital);
                markerOptions.position(latLng);
                markerOptions.snippet(vicinity);

                mMap.addMarker(markerOptions);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
