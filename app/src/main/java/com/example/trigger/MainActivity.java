package com.example.trigger;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView phno;
    Button triggerbutton;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LocationManager locationManager =
                (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        phno = findViewById(R.id.Phone);
        triggerbutton = findViewById(R.id.button);
        triggerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("MissingPermission")
                Location myloc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (phno.getText() != null && myloc != null) {
                    Intent sms = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + phno.getText().toString()));
                    double lat = myloc.getLatitude();
                    double longi = myloc.getLongitude();
                    double h = myloc.getAltitude();
                    sms.putExtra("sms_body", "I might need help.\nCurrent location:\n" + "http://maps.google.com/maps?saddr=" + lat + "," + longi + "\nAt height: " + h);
                    startActivity(sms);
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

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