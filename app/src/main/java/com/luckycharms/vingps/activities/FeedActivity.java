package com.luckycharms.vingps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.amplifyframework.core.Amplify;
import com.google.android.material.navigation.NavigationView;
import com.luckycharms.vingps.R;

public class FeedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new ProfileFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_profile);
//        }

//        Button logoutButton = FeedActivity.this.findViewById(R.id.logoutButton);
//        logoutButton.setOnClickListener((v -> {
//            Amplify.Auth.signOut(
//                    () -> Log.i("Amplify.logout", "Logged out Successfully"),
//                    error -> Log.e("Amplify.logout", error.toString())
//            );
//            startActivity(new Intent(FeedActivity.this, MainActivity.class));
//        }));
//
//        Button addClientButton = FeedActivity.this.findViewById(R.id.feedAddClientButton);
//        addClientButton.setOnClickListener((v -> {
//            System.out.println("Going to add client page");
//            startActivity(new Intent(FeedActivity.this, CreateClientActivity.class));
//        }));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_logout:
                Amplify.Auth.signOut(
                        () -> Log.i("Amplify.logout", "Logged out Successfully"),
                        error -> Log.e("Amplify.logout", error.toString())
                );
                startActivity(new Intent(FeedActivity.this, MainActivity.class));
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}