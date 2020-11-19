
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

        // Adding Event Listeners
        addLoginListener();
        addMocks();
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

    public void addMocks() {
        Client client = Client.builder().firstName("Ted")
                .lastName("Talks")
                .phone("206-234-6231")
                .email("thisplace@gmail.com")
                .license("temp")
                .licenseImageUrl("temp")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .make("Ford")
                        .model("Escort")
                        .color("Blue")
                        .price("$13,140")
                        .vin("AFGERGAEfFG235WEF5DB43")
                        .lat("43.126326")
                        .lon("-122.456123")
                        .status(false)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .client(client)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Client client1 = Client.builder().firstName("Ted")
                .lastName("Talks")
                .phone("206-234-6231")
                .email("thisplace@gmail.com")
                .license("temp")
                .licenseImageUrl("temp")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .make("Toyota")
                        .model("Tacoma")
                        .color("Red")
                        .price("$23,530")
                        .vin("AFGERGAWCFG235TTFFK53G")
                        .lat("43.126327")
                        .lon("-122.456765")
                        .status(false)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .client(client1)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Client client2 = Client.builder().firstName("Ted")
                .lastName("Talks")
                .phone("206-234-6231")
                .email("thisplace@gmail.com")
                .license("temp")
                .licenseImageUrl("temp")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .make("Mercedes-Benz")
                        .model("C100")
                        .color("Silver")
                        .price("$44,650")
                        .vin("AFHYNGAED54335WEF34KL6")
                        .lat("43.126545")
                        .lon("-122.456767")
                        .status(false)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .client(client2)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Client client3 = Client.builder().firstName("Ted")
                .lastName("Talks")
                .phone("206-234-6231")
                .email("thisplace@gmail.com")
                .license("temp")
                .licenseImageUrl("temp")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .make("Tesla")
                        .model("Model S")
                        .color("Black")
                        .price("$33,245")
                        .vin("AFGERGAAFFG235WEF3465Y")
                        .lat("43.126561")
                        .lon("-122.456321")
                        .status(false)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .client(client3)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Client client4 = Client.builder().firstName("Ted")
                .lastName("Talks")
                .phone("206-234-6231")
                .email("thisplace@gmail.com")
                .license("temp")
                .licenseImageUrl("temp")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .make("Tesla")
                        .model("Model X")
                        .color("White")
                        .price("$31,000")
                        .vin("AFGGSQAEDFG235WEF65KO0")
                        .lat("43.126432")
                        .lon("-122.456236")
                        .status(true)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .client(client4)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );

        Client client5 = Client.builder().firstName("Ted")
                .lastName("Talks")
                .phone("206-234-6231")
                .email("thisplace@gmail.com")
                .license("temp")
                .licenseImageUrl("temp")
                .build();
        Amplify.API.mutate(
                ModelMutation.create(Car.builder()
                        .make("Mazda")
                        .model("Miata")
                        .color("Red")
                        .price("$11,000")
                        .vin("AFGERGAEDFG235WEF983HFG")
                        .lat("43.126209")
                        .lon("-122.456943")
                        .status(true)
                        .imageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.motortrend.com%2Fcars%2Fford%2Ffocus%2F&psig=AOvVaw1q4jRkkNhTzSyZXDEg6a-s&ust=1605738298957000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLDMqbrPiu0CFQAAAAAdAAAAABAD")
                        .lastUserCheckedOut("Bill")
                        .client(client5)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );
    }

    public void createDummyCars() {
//        int i = 1;
        for (int i = 11; i <= 100; i++) {
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
                    .lat(Integer.toString(i))
                    .lon(Integer.toString(i))
                    .status(true)
                    .client(client)
                    .build();

            int num = i;
            Amplify.API.mutate(
                    ModelMutation.create(car),
                    response -> Log.i("Amplify.Dummy", Integer.toString(num)),
                    error -> Log.e("Amplify.Dummy", Integer.toString(num))
            );
        }
    }
}

