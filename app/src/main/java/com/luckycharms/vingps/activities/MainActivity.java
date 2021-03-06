package com.luckycharms.vingps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

public class MainActivity extends AppCompatActivity {

    Handler handleCheckedLoggedIn;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleCheckedLoggedIn = new Handler(Looper.getMainLooper(), message -> {
            if (message.arg1 == 0) {
                Log.i("Amplify.login", "Handler: They are not logged in");
            } else if (message.arg1 == 1) {
                Log.i("Amplify.login", "Handler: They were logged in");
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            } else {
                Log.i("Amplify.login", "Send true or false");
            }
            return false;
        });

        configureAws();

        // Hardcoding Dummy Data
//        addMocks();

//        createDummyCars();
//        createDummyClients();

        // Adding Event Listeners
        addLoginListener();
        getIsSignedIn();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        setContentView(R.layout.activity_main);
//
//    }

    public void configureAws() {
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MainActivityAmplify", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("MainActivityAmplify", "Could not initialize Amplify", e);
        }
    }

    public void getIsSignedIn() {
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("Amplify.login", result.toString());
                    Message message = new Message();

                    if (result.isSignedIn()) {
                        message.arg1 = 1;
                    } else {
                        message.arg1 = 0;
                    }
                    handleCheckedLoggedIn.sendMessage(message);
                },
                error -> Log.e("Amplify.login", error.toString())
        );
    }

    public void addLoginListener() {
        ((Button) findViewById(R.id.userLoginButton)).setOnClickListener(view -> {
            String username = ((TextView) findViewById(R.id.emailLoginInput)).getText().toString();
            String password = ((TextView) findViewById(R.id.passwordLoginInput)).getText().toString();

            Amplify.Auth.signIn(
                    username,
                    password,
                    result -> {
                        Log.i("Amplify.login", result.isSignInComplete() ? "Login succeeded" : "Login not complete");
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    },
                    error -> Log.e("Amplify.login", error.toString())
            );
        });
    }

    public void getLocation() {

    }

    public void createDummyClients() {
        for (int i = 1; i <= 1000; i++) {
            Client client = Client.builder()
                    .firstName("First Name: " + Integer.toString(i))
                    .lastName("Last Name: " + Integer.toString(i))
                    .phone("Phone: " + Integer.toString(i))
                    .email("Email: " + Integer.toString(i))
                    .license("License Number: " + Integer.toString(i))
                    .licenseImageUrl("License Image URL: " + Integer.toString(i))
                    .build();

            int num = i;
            Amplify.API.mutate(
                    ModelMutation.create(client),
                    response -> Log.i("AmplifyDummyClient", Integer.toString(num)),
                    error -> Log.e("Amplify.DummyClient", Integer.toString(num))
            );
        }
    }

    public void addMocks() {
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .year("1969")
                        .make("Ford")
                        .model("Escort")
                        .year("2018")
                        .color("Blue")
                        .price("$13,140")
                        .vin("AFGERGAEfFG235WEF5DB43")
                        .lat("47.126326")
                        .lon("-122.456123")
                        .status(false)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .year("2000")
                        .make("Toyota")
                        .model("Tacoma")
                        .year("2018")
                        .color("Red")
                        .price("$23,530")
                        .vin("AFGERGAWCFG235TTFFK53G")
                        .lat("47.126327")
                        .lon("-122.456765")
                        .status(false)
                        .imageUrl("https://img2.carmax.com/img/vehicles/19496963/1/385.jpg")
                        .lastUserCheckedOut("Bill")
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .year("2020")
                        .make("Mercedes-Benz")
                        .model("C100")
                        .color("Silver")
                        .price("$44,650")
                        .vin("AFHYNGAED54335WEF34KL6")
                        .lat("47.126545")
                        .lon("-122.456767")
                        .status(false)
                        .imageUrl("https://static.tcimg.net/vehicles/primary/6d0377b69398fa6b/2020-Mercedes-Benz-C-Class-white-full_color-driver_side_front_quarter.png")
                        .lastUserCheckedOut("Bill")
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .year("2050")
                        .make("Tesla")
                        .model("Model S")
                        .color("Black")
                        .price("$33,245")
                        .vin("AFGERGAAFFG235WEF3465Y")
                        .lat("47.126561")
                        .lon("-122.456321")
                        .status(false)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .year("2018")
                        .make("Tesla")
                        .model("Model X")
                        .color("White")
                        .price("$31,000")
                        .vin("AFGGSQAEDFG235WEF65KO0")
                        .lat("47.126432")
                        .lon("-122.456236")
                        .status(true)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .year("1993")
                        .make("Mazda")
                        .model("Miata")
                        .color("Red")
                        .price("$11,000")
                        .vin("AFGERGAEDFG235WEF983HFG")
                        .lat("47.126209")
                        .lon("-122.456943")
                        .status(true)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

    }
}

