package com.luckycharms.vingps.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.amplifyframework.datastore.generated.model.Car;
import com.google.android.gms.tasks.Task;
import com.luckycharms.vingps.R;

import static com.luckycharms.vingps.activities.MainActivity.currentClient;


public class ClientDetailActivity extends AppCompatActivity {

//    public void onClick(View view) {
//        TextView firstName = findViewById(R.id.first_name);
//        TextView lastName = findViewById(R.id.last_name);
//
//        Task taskToAdd = Task.builder()
//                .firstName(firstName.getText().toString())
//                .lastName(lastName.getText().toString())
//                .build();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

//
//        Button searchClient = ClientDetailActivity.this.findViewById(R.id.client_search);
//        searchClient.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               Intent allTasks = new Intent(ClientDetailActivity.this, ClientViewFragment.class);
//                ClientDetailActivity.this.startActivity(allTasks);
//            }
//        });
    }




//        currentClient.getCars().add(car);

}