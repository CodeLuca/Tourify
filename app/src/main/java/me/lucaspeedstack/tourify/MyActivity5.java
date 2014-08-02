package me.lucaspeedstack.tourify;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MyActivity5 extends ActionBarActivity {

    public static MapFragment myMap;

    LatLng fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
    LatLng toPosition = new LatLng(13.683660045847258, 100.53900808095932);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity5);
        GoogleMap myMap = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng one = new LatLng(-33.867, 151.203);
        LatLng two = new LatLng(-33.867, 151.201);
        LatLng three = new LatLng(-33.867, 151.209);
        myMap.addMarker(new MarkerOptions()
                .position(one));
        myMap.addMarker(new MarkerOptions()
                .position(two));
        myMap.addMarker(new MarkerOptions()
                .position(three));
        LatLng four = null;
        LatLng five = null;
        LatLng six = null;
        myMap.setMyLocationEnabled(true);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(one, 13));
        if (MyActivity4.nor > 3) {
            four = new LatLng(-33.862, 151.206);
            myMap.addMarker(new MarkerOptions()
                    .position(four));
            if (MyActivity4.nor > 4) {
                five = new LatLng(-33.869, 151.206);
                myMap.addMarker(new MarkerOptions()
                        .position(five));
                if (MyActivity4.nor > 5) {
                    six = new LatLng(-33.865, 151.206);
                    myMap.addMarker(new MarkerOptions()
                            .position(six));
                }
            }
        }
    }

}