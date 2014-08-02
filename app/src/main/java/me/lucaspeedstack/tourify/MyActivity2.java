package me.lucaspeedstack.tourify;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MyActivity2 extends ActionBarActivity implements LocationListener{

    //TODO ._.
    public static Double lat;
    public static Double lng;
    public static String text;
    public static Boolean lbool = false;

    TextView where;
    ImageButton ib;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);


        ib = (ImageButton) findViewById(R.id.imageButton);
        where = (TextView)findViewById(R.id.placename);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyActivity4.class));
            }
        });

    }

    public JSONObject getLocationInfo(double lat, double lng) {

        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng="+lat+","+lng);
       // HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng=51.4346502,-0.1020349");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    @Override
    public void onLocationChanged(Location loc) {
         lat = (loc.getLatitude());
         lng = (loc.getLongitude());

        where.setText(lat + " " + lng);

        // get lat and lng value
        if(lbool  == true){
            return;
        }
        else {
            JSONObject ret = getLocationInfo(loc.getLatitude(), loc.getLongitude());
            JSONObject location;
            String location_string;
            try {
                //Get JSON Array called "results" and then get the 0th complete object as JSON
                location = ret.getJSONArray("results").getJSONObject(0);
                // Get the value of the attribute whose name is "formatted_string"

                location_string = location.getString("formatted_address");
                Log.d("test", "formattted address:" + location_string);
                text = location_string;
                where.setText(location_string);
                lbool = true;
            } catch (JSONException e1) {
                e1.printStackTrace();

            }


        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        where.setText("No GPS");
    }
}
