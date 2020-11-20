package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

import org.w3c.dom.Text;

public class VehicleDetailActivity extends AppCompatActivity {

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
        String clientId = intent.getExtras().getString("client");
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
                Amplify.API.query(
                        ModelQuery.get(Car.class, itemId),
                        response -> {
                            Log.i("Success: Grabbing car item", response.getData().toString());
                            Car carItem = response.getData().copyOfBuilder()
                                    .status(false)
                                    .id(itemId).build();
                            Amplify.API.mutate(ModelMutation.update(carItem),
                                    result -> Log.i("Success: Updating car status", result.getData().toString()),
                                    error -> Log.e("Updating car status", error.toString()));
                        },
                        error -> Log.e("Updating car status", error.toString())
                );
            }
        });
    }
}