package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.luckycharms.vingps.R;

public class ClientDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        Intent intent = getIntent();
        TextView clientFirstName = ClientDetailActivity.this.findViewById(R.id.clientDetailFirstName);
        TextView clientLastName = ClientDetailActivity.this.findViewById(R.id.clientDetailLastName);
        TextView clientPhone = ClientDetailActivity.this.findViewById(R.id.clientDetailPhone);
        TextView clientEmail = ClientDetailActivity.this.findViewById(R.id.clientDetailEmail);
        TextView clientSalesPerson = ClientDetailActivity.this.findViewById(R.id.clientDetailLastSalesPerson);
        TextView clientLicenseNumber = ClientDetailActivity.this.findViewById(R.id.clientDetailLicense);
//        TextView clientFirstName = ClientDetailActivity.this.findViewById(R.id.clientDetailFirstName);

        clientFirstName.setText(intent.getExtras().getString("firstName"));
        clientLastName.setText(intent.getExtras().getString("lastName"));
        clientPhone.setText(intent.getExtras().getString("phone"));
        clientEmail.setText(intent.getExtras().getString("email"));
        clientLicenseNumber.setText(intent.getExtras().getString("license"));
        clientSalesPerson.setText(intent.getExtras().getString("lastSalesPerson"));
//        clientFirstName.setText(intent.getExtras().getString("firstName"));
    }
}