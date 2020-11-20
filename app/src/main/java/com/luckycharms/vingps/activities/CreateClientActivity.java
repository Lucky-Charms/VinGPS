package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

import java.io.File;

public class CreateClientActivity extends AppCompatActivity {
    File attachedFile;
    String globalKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        Button addClientButton = CreateClientActivity.this.findViewById(R.id.createClientButton);
        addClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText clientFirstNameInput = CreateClientActivity.this.findViewById(R.id.clientFirstNameInput);
                EditText clientLastNameInput = CreateClientActivity.this.findViewById(R.id.clientLastNameInput);
                EditText clientEmailInput = CreateClientActivity.this.findViewById(R.id.clientEmailInput);
                EditText clientPhoneInput = CreateClientActivity.this.findViewById(R.id.clientPhoneInput);
                EditText clientSalesPersonInput = CreateClientActivity.this.findViewById(R.id.clientSalesPersonInput);
                EditText clientLicenseInput = CreateClientActivity.this.findViewById(R.id.clientLicenseInput);
                String clientFirstName = clientFirstNameInput.getText().toString();
                String clientLastName = clientLastNameInput.getText().toString();
                String clientEmail = clientEmailInput.getText().toString();
                String clientPhone = clientPhoneInput.getText().toString();
                String clientSalesPerson = clientSalesPersonInput.getText().toString();
                String clientLicense = clientLicenseInput.getText().toString();

                System.out.println(String.format("Client Submitted! %s %s has been added to the client list", clientFirstName, clientLastName));

                Client client = Client.builder()
                        .firstName(clientFirstName).lastName(clientLastName).email(clientEmail)
                        .lastSalesPerson(clientSalesPerson).phone(clientPhone).license(clientLicense)
                        .licenseImageUrl("Empty").build();
                Amplify.API.mutate(ModelMutation.create(client),
                        response -> Log.i("CreateClientActivityAmplify", "Successfully created new client"),
                        error -> Log.e("CreateClientActivityAmplify", error.toString()));

                Intent intent = new Intent(CreateClientActivity.this, ClientDetailActivity.class);
                intent.putExtra("id", client.getId());
                intent.putExtra("firstName", client.getFirstName());
                intent.putExtra("lastName", client.getLastName());
                intent.putExtra("email", client.getEmail());
                intent.putExtra("phone", client.getPhone());
                intent.putExtra("license", client.getLicense());
                intent.putExtra("licenseImageURL", client.getLicenseImageUrl());
                intent.putExtra("lastSalesPerson", client.getLastSalesPerson());
                Log.i("Amplify.SearchClient", "You are trying to view client: " + client.getFirstName());
                startActivity(intent);
                finish();
            }
        });

    }


}