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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity5);
        GoogleMap myMap = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();


        LatLng one = new LatLng(50.3710, -4.1402);
        LatLng two = new LatLng(50.3718, -4.1420);
        LatLng three = new LatLng(50.3714, -4.1427);
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
            four = new LatLng(50.3782, -4.1439);
            myMap.addMarker(new MarkerOptions()
                    .position(four));
            if (MyActivity4.nor > 4) {
                five = new LatLng(50.3704, -4.1423);
                myMap.addMarker(new MarkerOptions()
                        .position(five));
                if (MyActivity4.nor > 5) {
                    six = new LatLng(50.3765, -4.145);
                    myMap.addMarker(new MarkerOptions()
                            .position(six));
                }
            }
        }
    }

}