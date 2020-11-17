package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.luckycharms.vingps.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button moveButton = MainActivity.this.findViewById(R.id.goToLocation);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLocationActivity = new Intent(MainActivity.this, VicLocationActivity.class);
                MainActivity.this.startActivity(goToLocationActivity);
            }
        });
    }
    
}
