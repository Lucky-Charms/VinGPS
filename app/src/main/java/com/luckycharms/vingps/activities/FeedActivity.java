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

        Button logoutButton = FeedActivity.this.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener((v -> {
            Amplify.Auth.signOut(
                    () -> Log.i("Amplify.logout", "Logged out Successfully"),
                    error -> Log.e("Amplify.logout", error.toString())
            );
            startActivity(new Intent(FeedActivity.this, MainActivity.class));
        }));
    }
}