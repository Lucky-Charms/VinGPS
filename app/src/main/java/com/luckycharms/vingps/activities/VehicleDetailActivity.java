package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        TextView carMake = VehicleDetailActivity.this.findViewById(R.id.carDetailMake);
        TextView carModel = VehicleDetailActivity.this.findViewById(R.id.carDetailModel);
        TextView carColor = VehicleDetailActivity.this.findViewById(R.id.carDetailColor);
        TextView carPrice = VehicleDetailActivity.this.findViewById(R.id.carDetailPrice);
        TextView carVin = VehicleDetailActivity.this.findViewById(R.id.carDetailVIN);
        TextView carStatus = VehicleDetailActivity.this.findViewById(R.id.carDetailStatus);

//        carMake.setText(intent.getExtras().toString("make"));
//        carModel.setText(intent.getExtras().toString("model"));
//        carColor.setText(intent.getExtras().toString("color"));
//        carPrice.setText(intent.getExtras().toString("price"));
//        carVin.setText(intent.getExtras().toString("vin"));
//        carStatus.setText(intent.getExtras().toString("status"));

        Button checkCarLocationButton = this.findViewById(R.id.locateCarButton);
        checkCarLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Checking location");
            }
        });

    }
}