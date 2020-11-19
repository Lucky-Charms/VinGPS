package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luckycharms.vingps.R;

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

        String status = intent.getExtras().getBoolean("status") ? "Checked out" : "Available";

        carYear.setText(intent.getExtras().getString("year"));
        carMake.setText(intent.getExtras().getString("make"));
        carModel.setText(intent.getExtras().getString("model"));
        carColor.setText(intent.getExtras().getString("color"));
        carPrice.setText(intent.getExtras().getString("price"));
        carVin.setText(intent.getExtras().getString("vin"));
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

    }
}