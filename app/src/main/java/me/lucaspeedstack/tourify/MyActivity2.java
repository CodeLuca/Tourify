package me.lucaspeedstack.tourify;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import me.lucaspeedstack.tourify.R;

public class MyActivity2 extends ActionBarActivity implements LocationListener{
    private LocationManager locationManager;
    private static final long MIN_TIME = 1 * 60 * 1000; //1 minute

    TextView where;
    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        where = (TextView) findViewById(R.id.placename);
        ib = (ImageButton) findViewById(R.id.imageButton);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0, this);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyActivity.class));


            }
        });

    }


    @Override
    public void onLocationChanged(Location loc) {
        int lat = (int) (loc.getLatitude());
        int lng = (int) (loc.getLongitude());

        where.setText(lat + " " + lng);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
