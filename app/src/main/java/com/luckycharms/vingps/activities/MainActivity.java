package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

public class MainActivity extends AppCompatActivity {

    Handler handleCheckedLoggedIn;

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

    public void createDummyCars() {
        for (int i = 1; i <= 1000; i++) {
            Client client = Client.builder()
                    .firstName("")
                    .lastName("")
                    .phone("")
                    .email("")
                    .license("")
                    .licenseImageUrl("")
                    .build();

            Car car = Car.builder()
                    .make("Make: " + Integer.toString(i))
                    .model("Model: " + Integer.toString(i))
                    .color("Color: " + Integer.toString(i))
                    .price("Price: " + Integer.toString(i))
                    .vin("VIN: " + Integer.toString(i))
                    .lat("Latitude: " + Integer.toString(i))
                    .lon("Longitude: " + Integer.toString(i))
                    .status(i % 2 == 0)
                    .client(client)
                    .build();

            int num = i;
            Amplify.API.mutate(
                    ModelMutation.create(car),
                    response -> Log.i("Amplify.DummyCar", Integer.toString(num)),
                    error -> Log.e("Amplify.DummyCar", Integer.toString(num))
            );
        }
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
}
