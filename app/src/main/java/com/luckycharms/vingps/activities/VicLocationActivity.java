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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vic_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        distance_text = findViewById(R.id.distance_text);
        textLocation = findViewById(R.id.gpsCoord);
        findViewById(R.id.getGPS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            VicLocationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                } else {
                    getCurrentLocation();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    ;

    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.getFusedLocationProviderClient(VicLocationActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        double startLat;
                        double startLon;
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(VicLocationActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            Intent intent = getIntent();
                            startLat = Double.parseDouble(intent.getExtras().getString("lat"));
                            startLon = Double.parseDouble(intent.getExtras().getString("lon"));
                            distance_text.setText(String.format("Distance to car: %s meters.", CalculationByDistance(startLat, latitude, startLon, longitude)));
                            textLocation.setText(String.format("Latitude: %s Longitude: %s", latitude, longitude));
                        }

                    }
                }, Looper.getMainLooper());
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

        Intent intent = getIntent();
        lat = Double.parseDouble(intent.getExtras().getString("lat"));
        lon = Double.parseDouble(intent.getExtras().getString("lon"));
        Log.i("Latitude", intent.getExtras().getString("lat"));
        Log.i("Longitude", intent.getExtras().getString("lon"));
        LatLng location = new LatLng(lat, lon);
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
        return (int)lastThing;
    }

//    public class Tracking extends MapActivity implements LocationListener {
//
//        LocationManager locman;
//        LocationListener loclis;
//        Location Location;
//        private MapView map;
//
//        List<GeoPoint> geoPointsArray = new ArrayList<GeoPoint>();
//        private MapController controller;
//        String provider = LocationManager.GPS_PROVIDER;
//        double lat;
//        double lon;
//
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.map);
//            initMapView();
//            initMyLocation();
//            locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            // locman.requestLocationUpdates(provider,60000, 100,loclis);
//            // Location = locman.getLastKnownLocation(provider);
//
//        }
//
//        /** Find and initialize the map view. */
//        private void initMapView() {
//            map = (MapView) findViewById(R.id.map);
//            controller = map.getController();
//            map.setSatellite(false);
//            map.setBuiltInZoomControls(true);
//        }
//
//        /** Find Current Position on Map. */
//        private void initMyLocation() {
//            final MyLocationOverlay overlay = new MyLocationOverlay(this, map);
//            overlay.enableMyLocation();
//            overlay.enableCompass(); // does not work in emulator
//            overlay.runOnFirstFix(new Runnable() {
//                public void run() {
//                    // Zoom in to current location
//                    controller.setZoom(24);
//                    controller.animateTo(overlay.getMyLocation());
//                }
//            });
//            map.getOverlays().add(overlay);
//        }
//
//        @Override
//        public void onLocationChanged(Location location) {
//            if (Location != null) {
//                lat = Location.getLatitude();
//                lon = Location.getLongitude();
//                GeoPoint New_geopoint = new GeoPoint((int) (lat * 1e6),
//                        (int) (lon * 1e6));
//                controller.animateTo(New_geopoint);
//
//            }
//
//        }
//
//        class MyOverlay extends Overlay {
//            public MyOverlay() {
//            }
//
//            public void draw(Canvas canvas, MapView mapv, boolean shadow) {
//                super.draw(canvas, mapv, shadow);
//
//                Projection projection = map.getProjection();
//                Path p = new Path();
//                for (int i = 0; i < geoPointsArray.size(); i++) {
//                    if (i == geoPointsArray.size() - 1) {
//                        break;
//                    }
//                    Point from = new Point();
//                    Point to = new Point();
//                    projection.toPixels(geoPointsArray.get(i), from);
//                    projection.toPixels(geoPointsArray.get(i + 1), to);
//                    p.moveTo(from.x, from.y);
//                    p.lineTo(to.x, to.y);
//                }
//                Paint mPaint = new Paint();
//                mPaint.setStyle(Style.STROKE);
//                mPaint.setColor(0xFFFF0000);
//                mPaint.setAntiAlias(true);
//                canvas.drawPath(p, mPaint);
//                super.draw(canvas, map, shadow);
//            }
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        protected boolean isRouteDisplayed() {
//            // TODO Auto-generated method stub
//            return false;
//        }
    }
