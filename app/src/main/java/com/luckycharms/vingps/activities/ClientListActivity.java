package com.luckycharms.vingps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;
import com.luckycharms.vingps.adapters.CarSearchRecyclerViewAdapter;
import com.luckycharms.vingps.adapters.ClientSearchRecyclerViewAdapter;

import java.util.ArrayList;

public class ClientListActivity extends AppCompatActivity implements ClientSearchRecyclerViewAdapter.ClientFragmentOnClickListener {

    RecyclerView recyclerView;
    Handler handler;
    ArrayList<Client> clients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        // Setup
        initializeRecyclerView();

        // Add Listeners
        addSearchListener();
    }

    public void addSearchListener() {
        Button searchClient = ClientListActivity.this.findViewById(R.id.clientSearchButton);
        searchClient.setOnClickListener(view -> {
            searchForClients();
        });
    }

    public void searchForClients() {
        TextView firstName = findViewById(R.id.clientListFirstName);
        TextView lastName = findViewById(R.id.clientListLastName);

        String firstNameString = firstName.getText().toString();
        String lastNameString = lastName.getText().toString();

        // Format the search query by capitalizing the first letter in the string
        if (firstNameString.length() == 1)
            firstNameString = firstNameString.toUpperCase();
        else if (firstNameString.length() > 1)
            firstNameString = firstNameString.substring(0, 1).toUpperCase() + firstNameString.substring(1);

        if (lastNameString.length() == 1)
            lastNameString = lastNameString.toUpperCase();
        else if (lastNameString.length() > 1)
            lastNameString = lastNameString.substring(0, 1).toUpperCase() + lastNameString.substring(1);

        clients.clear();

        String queryFirstName = firstNameString;
        String queryLastName = lastNameString;
        Log.i("Amplify.ClientSearch", queryFirstName + " " + queryLastName);

        Amplify.API.query(
                ModelQuery.list(Client.class),
                response -> {
                    for(Client client : response.getData()){
                        if (client.getFirstName().contains(queryFirstName) || client.getLastName().contains(queryLastName))
                            clients.add(client);
                    }
                    Log.i("Amplify.ClientSearch", Integer.toString(clients.size()) + " Items Returned");
                },
                error -> Log.e("Amplify.ClientSearch", error.toString())
        );

        handler.sendEmptyMessage(1);
    }

    public void initializeRecyclerView() {
        recyclerView = findViewById(R.id.clientSearchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ClientSearchRecyclerViewAdapter(clients, this));
    }

    @Override
    public void ClientFragmentListener(Client client) {
        Intent intent = new Intent(ClientListActivity.this, ClientDetailActivity.class);
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
    }
}