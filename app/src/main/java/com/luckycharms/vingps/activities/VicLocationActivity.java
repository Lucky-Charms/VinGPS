package com.luckycharms.vingps.activities;
// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

//package com.google.maps.example;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luckycharms.vingps.R;

import java.text.DecimalFormat;
import java.util.PrimitiveIterator;

public class VicLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient client;
    private GoogleMap mMap;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private TextView textLocation;
    private TextView distance_text;
    private double firstLat = 47.716638;
    private double firstLon = -122.296567;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vic_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        distance_text = findViewById(R.id.distance_text);
        Intent intent = getIntent();
//        distance_text.setText(String.format("Distance to car: %s meters.", CalculationByDistance(startLat, Double.parseDouble(intent.getExtras().getString("lat")), startLon, Double.parseDouble(intent.getExtras().getString("lon")))));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lat;
        double lon;

        mMap = googleMap;
        mMap.setMinZoomPreference(18.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Add a marker in Seattle and move the camera

//        Intent intent = getIntent();
//        lat = Double.parseDouble(intent.getExtras().getString("lat"));
//        lon = Double.parseDouble(intent.getExtras().getString("lon"));
//        Log.i("Latitude", intent.getExtras().getString("lat"));
//        Log.i("Longitude", intent.getExtras().getString("lon"));
        Intent intent = getIntent();
        LatLng location = new LatLng(Double.parseDouble(intent.getExtras().getString("lat")), Double.parseDouble(intent.getExtras().getString("lon")));
        mMap.addMarker(new MarkerOptions()
                .position(location)
                .title("Location of Car"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    public int CalculationByDistance(double startLat, double endLat, double startLon, double endLon) {
        int Radius = 6371;// radius of earth in Km
        double dLat = Math.toRadians(endLat - startLat);
        double dLon = Math.toRadians(endLon - startLon);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(startLat))
                * Math.cos(Math.toRadians(endLat)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.parseInt(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.parseInt(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        c = c * 1000;
        double lastThing = Radius * c;
        return (int) lastThing;
    }
}