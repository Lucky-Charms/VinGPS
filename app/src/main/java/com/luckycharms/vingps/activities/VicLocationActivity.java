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


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luckycharms.vingps.R;

public class VicLocationActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vic_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * <p>
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MockCar car1 = new MockCar("Ford", "Siesta", "2015", 45.12489, -122.34566);
        mMap = googleMap;
        mMap.setMinZoomPreference(18.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Add a marker in Seattle and move the camera
        LatLng seattle = new LatLng(car1.lat, car1.lon);
        mMap.addMarker(new MarkerOptions()
                .position(seattle)
                .title("Marker in Seattle"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seattle));
    }

// car location in dealership
    public void onCarMapReady(GoogleMap googleMap) {
        MockCar car1 = new MockCar("Ford", "Siesta", "2015", 45.12489, -122.34566);

        mMap = googleMap;
        mMap.setMinZoomPreference(18.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Add a marker for car
        LatLng car = new LatLng(car1.lat, car1.lon);
        mMap.addMarker(new MarkerOptions()
                .position(car)
                .title("Marker in dealership"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(car));
    }

    public class MockCar{
        String make;
        String model;
        String year;
        double lat;
        double lon;

        public MockCar(String make, String model, String year, double lat, double lon) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.lat = lat;
            this.lon = lon;
        }
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
