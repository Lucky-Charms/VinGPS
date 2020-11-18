package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

import java.util.ArrayList;

public class ClientListActivity extends AppCompatActivity {

    ArrayList<Client> clients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        addSearchListener();
    }

    public void addSearchListener() {
        Button searchClient = ClientListActivity.this.findViewById(R.id.clientSearchButton);
        searchClient.setOnClickListener(view -> {
            search();
        });
    }

    public void search() {
        TextView firstName = findViewById(R.id.clientListFirstName);
        TextView lastName = findViewById(R.id.clientListLastName);

        Amplify.API.query(
                ModelQuery.list(Client.class),
                response -> {
                    for(Client client : response.getData()){
                        clients.add(client);
                    }
                    Log.i("Amplify.ClientSearch", Integer.toString(clients.size()) + " Items Returned");
                },
                error -> Log.e("Amplify.ClientSearch", error.toString())
        );
    }

}