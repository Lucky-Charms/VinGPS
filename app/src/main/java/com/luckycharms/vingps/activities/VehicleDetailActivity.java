package com.luckycharms.vingps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.Client;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.luckycharms.vingps.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VehicleDetailActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    public String latString;
    public String lonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        Intent intent = getIntent();
        TextView carYear = VehicleDetailActivity.this.findViewById(R.id.carDetailYear);
        TextView carMake = VehicleDetailActivity.this.findViewById(R.id.carDetailMake);
        TextView carModel = VehicleDetailActivity.this.findViewById(R.id.carDetailModel);
        TextView carColor = VehicleDetailActivity.this.findViewById(R.id.carDetailColor);
        TextView carPrice = VehicleDetailActivity.this.findViewById(R.id.carDetailPrice);
        TextView carVin = VehicleDetailActivity.this.findViewById(R.id.carDetailVIN);
        TextView carStatus = VehicleDetailActivity.this.findViewById(R.id.carDetailStatus);
        TextView carLastCheckoutBy = VehicleDetailActivity.this.findViewById(R.id.lastCheckedOutCar);

        String status = intent.getExtras().getBoolean("status") ? "Checked out" : "Available";
        String vin = "VIN: " + intent.getExtras().getString("vin");

        String itemId = intent.getExtras().getString("id");
        carYear.setText(intent.getExtras().getString("year"));
        carMake.setText(intent.getExtras().getString("make"));
        carModel.setText(intent.getExtras().getString("model"));
        carColor.setText(intent.getExtras().getString("color"));
        carPrice.setText(intent.getExtras().getString("price"));
        carVin.setText(vin);
        carLastCheckoutBy.setText(intent.getExtras().getString("lastUserCheckedOut"));
        carStatus.setText(status);

        Button checkCarLocationButton = this.findViewById(R.id.locateCarButton);
        checkCarLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat;
                String lon;

                Intent intent = getIntent();
                lat = intent.getExtras().getString("lat");
                lon = intent.getExtras().getString("lon");

                Intent goToLocation = new Intent(VehicleDetailActivity.this, VicLocationActivity.class);
                goToLocation.putExtra("lat", lat);
                goToLocation.putExtra("lon", lon);
                VehicleDetailActivity.this.startActivity(goToLocation);

                System.out.println("Checking location");
            }
        });

        Button startTestDriveButton = this.findViewById(R.id.startTestDriveButton);
        Button endTestDriveButton = this.findViewById(R.id.endTestDriveButton);

        startTestDriveButton.setVisibility(View.VISIBLE);
        startTestDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestDriveButton.setVisibility(View.GONE);
                endTestDriveButton.setVisibility(View.VISIBLE);
                Amplify.API.query(
                        ModelQuery.get(Car.class, itemId),
                        response -> {
                            Log.i("Success: Grabbing car item", response.getData().toString());
                            Car carItem = response.getData().copyOfBuilder()
                                    .status(true)
                                    .id(itemId).build();
                            Amplify.API.mutate(ModelMutation.update(carItem),
                                    result -> Log.i("Success: Updating car status", result.getData().toString()),
                                    error -> Log.e("Updating car status", error.toString()));
                        },
                        error -> Log.e("Updating car status", error.toString())
                );
            }
        });

        endTestDriveButton.setVisibility(View.VISIBLE);
        endTestDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTestDriveButton.setVisibility(View.GONE);
                startTestDriveButton.setVisibility(View.VISIBLE);

                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("AHHHHHHHHH", latString + lonString);
                    ActivityCompat.requestPermissions(
                            VehicleDetailActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                } else {
                    getCurrentLocation(itemId);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation2();
            } else {
                Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation(String itemId) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.getFusedLocationProviderClient(VehicleDetailActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        double startLat;
                        double startLon;
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(VehicleDetailActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();

                            latString = Double.toString(latitude);
                            lonString = Double.toString(longitude);

                            Log.i("Coords", latString + lonString);

                            Amplify.API.query(
                                    ModelQuery.get(Car.class, itemId),
                                    response -> {

                                        Log.i("Success: Grabbing car item", response.getData().toString());
                                        Car carItem = response.getData();
                                        carItem.status = false;
                                        Log.i("latLon", latString + lonString);
                                        carItem.lat = latString;
                                        carItem.lon = lonString;
                                        Amplify.API.mutate(ModelMutation.update(carItem),
                                                result -> {
                                                    Log.i("Success: Updating car status", result.toString());
                                                },
                                                error -> Log.e("Updating car status", error.toString()));
                                    },
                                    error -> Log.e("Updating car status", error.toString())
                            );
                        }

                    }
                }, Looper.getMainLooper());
    }

    private void getCurrentLocation2() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.getFusedLocationProviderClient(VehicleDetailActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        double startLat;
                        double startLon;
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(VehicleDetailActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();

                            latString = Double.toString(latitude);
                            lonString = Double.toString(longitude);

                            Log.i("Coords", latString + lonString);


                        }

                    }
                }, Looper.getMainLooper());
    }
}
