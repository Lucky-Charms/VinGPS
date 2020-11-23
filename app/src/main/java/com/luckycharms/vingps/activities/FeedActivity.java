package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.core.Amplify;
import com.luckycharms.vingps.R;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Button gothere = findViewById(R.id.button3);
        gothere.setOnClickListener((view) -> {
            Intent intent = new Intent(FeedActivity.this, VicLocationActivity.class);
            FeedActivity.this.startActivity(intent);
        });
    }
}