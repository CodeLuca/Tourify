package me.lucaspeedstack.tourify;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.maps.model.LatLng;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static me.lucaspeedstack.tourify.R.drawable.*;

public class MyActivity4 extends ActionBarActivity {

    boolean done = false;

    TextView uno;
    ImageView view;
    ImageView white;
    TextView rating;
    TextView where;
    Button next;
    Button back;

    LatLng latLngone;
    LatLng latLngtwo;

    String sv;
    String one;
    String two;

    public static ArrayList<LatLng> sPoints = new ArrayList<LatLng>();

    int max;
    int slide = 0;
    double lat1, lon1, lat2, lon2;
    public static int nor;

    private float x1, x2;
    static final int MIN_DISTANCE = 150;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity4);

        uno = (TextView) findViewById(R.id.uno);
        view = (ImageView) findViewById(R.id.imageView);
        rating = (TextView) findViewById(R.id.textView);
        next = (Button) findViewById(R.id.next);
        next.setAlpha(.0f);
        back = (Button) findViewById(R.id.back);
        back.setAlpha(.0f);
        white = (ImageView) findViewById(R.id.awf);
        where = (TextView) findViewById(R.id.textView2);

        white.setAlpha(.4f);

        final JSONObject ret = giveInfo(MyActivity2.lat, MyActivity2.lng, MyActivity2.text);
        JSONObject location;
        String name;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            if(done == true){
                return;
            }
            //Get JSON Array called "results" and then get the 0th complete object as JSON

            nor = ret.getInt("numberOfResults");
            location = ret.getJSONArray("results").getJSONObject(0);
            // Get the value of the attribute whose name is "formatted_string"
            name = location.getString("name");
            uno.setText(name);
            sv = location.getString("image");
            one = "Rating: " + location.getString("rating");
            two = location.getString("vicinity");
            rating.setText(one);
            where.setText(two);
            lat1 = location.getDouble("lat");
            lon1 = location.getDouble("lon");
            latLngone = new LatLng(lat1, lon1);
            UrlImageViewHelper.setUrlDrawable(view, sv, ok);
            done = true;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject location2;
                String name2;
                String sv2;
                try {
                    if(nor == slide || nor == slide + 1){
                        if(nor == slide + 1){
                            startActivity(new Intent(getApplicationContext(), MyActivity5.class));

                        }
                        uno.setText("No More Places!");
                        rating.setText("Press Next");
                        where.setText("To Show Directions!");
                        view.setAlpha(0f);
                        slide = nor;
                        return;
                    }
                    view.setAlpha(100f);
                    view.setImageResource(R.drawable.ok);
                    location2 = ret.getJSONArray("results").getJSONObject(slide + 1);
                    name2 = location2.getString("name");
                    where.setText(location2.getString("vicinity"));
                    uno.setText(name2);
                    sv2 = location2.getString("image");
                    lat2 = location2.getDouble("lat");
                    lon2 = location2.getDouble("lon");
                    latLngtwo = new LatLng(lat2, lon2);
                    UrlImageViewHelper.setUrlDrawable(view, sv2, ok);
                    slide++;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject location2;
                String name3;
                String sv3;
                try {
                    if(slide - 1 == -1 || slide - 1 == -2){
                        uno.setText("No More Places!");
                        rating.setText("Press Next");
                        where.setText("To Show Directions!");
                        view.setAlpha(0f);
                        slide = -1;
                        return;
                    }
                    view.setAlpha(100f);
                    view.setImageResource(R.drawable.ok);
                    location2 = ret.getJSONArray("results").getJSONObject(slide - 1);
                    name3 = location2.getString("name");

                    where.setText(location2.getString("vicinity"));
                    uno.setText(name3);
                    sv3 = location2.getString("image");
                    UrlImageViewHelper.setUrlDrawable(view, sv3, ok);
                    slide--;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public JSONObject giveInfo(double lat, double lng, String formated_address) {

        formated_address = formated_address.replaceAll(" ", "%20");

        HttpGet httpGet = new HttpGet("http://tourify.herokuapp.com/json/?lat="+ lat + "&lng=" + lng + "&address=" + formated_address);
        //HttpGet httpGet = new HttpGet("http://fast-sierra-5505.herokuapp.com/?lat=51.500756&lng=-0.124661&address=53%20Bridge%20Street,%20Westminster,%20London%20SW1A,%20Uz");
        Log.d("test", httpGet.getURI().toString());
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
            Log.e("A4Error", e.getMessage());
        } catch (IOException e) {
            Log.e("A4Error", e.getMessage());
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
         } catch (JSONException e) {
            e.printStackTrace();
            uno.setText("Not Working");

        }
        return jsonObject;
    }
}
