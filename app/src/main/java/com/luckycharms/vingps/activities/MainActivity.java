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
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.CarClient;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

import java.util.ArrayList;
import java.util.Arrays;

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
        testDB();

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
//                        .client(client)
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
//                        .client(client1)
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
//                        .client(client2)
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
//                        .client(client3)
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
//                        .client(client4)
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
//                        .client(client5)
                        .build()),
                success -> Log.i("Amplify", "Car added"),
                error -> Log.e("Amplify", error.toString())
        );
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
                    .lat(Integer.toString(i))
                    .lon(Integer.toString(i))
                    .status(true)
//                    .client(client)
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

    public void testDB() {
        // Make and save a Car
        // Make and save a Client

        // Query and grab the car
        // Query and grab the client

        // Add the car to the client
        // Add the client to the car
        // Mutate the car
        // Mutate the client

        Car car = Car.builder()
                .year("Test")
                .make("Test")
                .model("Test")
                .color("Test")
                .price("Test")
                .vin("Test")
                .lat("47.126432")
                .lon("-122.456236")
                .status(true)
                .imageUrl("Test")
                .lastUserCheckedOut("Test")
                .build();

        Client client = Client.builder()
                .firstName("Test")
                .lastName("Test")
                .phone("Test")
                .email("Test")
                .license("Test")
                .licenseImageUrl("Test")
                .build();

        Amplify.API.mutate(
                ModelMutation.create(car),
                response -> {
//                    car.id = response.getData().getId();
                    Log.i("Amplify.Test", "Car created");
                },
                error -> Log.e("Amplify.Test", error.toString())
        );
        Amplify.API.mutate(
                ModelMutation.create(client),
                response -> {
//                    client.id = response.getData().getId();
                    Log.i("Amplify.Test", "Client created");
                },
                error -> Log.e("Amplify.Test", error.toString())
        );

        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

//        Amplify.API.query(
//                ModelQuery.list(Car.class),
//                response -> {
//                    int i = 0;
//                    for (Car car : response.getData()) {
//                        i++;
//                        cars.add(car);
//                    }
//                    Log.i("Amplify.Test", Integer.toString(i));
//                },
//                error -> Log.e("Amplify.Test", error.toString())
//        );
//        Amplify.API.query(
//                ModelQuery.list(Client.class),
//                response -> {
//                    int i = 0;
//                    for (Client client : response.getData()) {
//                        i++;
//                        clients.add(client);
//                    }
//                    Log.i("Amplify.Test", Integer.toString(i));
//                },
//                error -> Log.e("Amplify.Test", error.toString())
//        );
//
//        Car car = cars.get(0);
//        Client client = clients.get(0);

        CarClient connection = CarClient.builder()
                .car(car)
                .client(client)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(connection),
                response -> {
                    Log.i("Amplify.Test", "Connection created");
                },
                error -> Log.e("Amplify.Test", error.toString())
        );

        Amplify.API.query(
                ModelQuery.list(Client.class),
                response -> {
                    int i = 0;
                    for (Client item : response.getData()) {
                        i++;
                        clients.add(item);
                    }
                    Log.i("Amplify.Test", Integer.toString(i));
                },
                error -> Log.e("Amplify.Test", error.toString())
        );

        Client pleaseWork = clients.get(0);
        Log.i("Amplify.Test", pleaseWork.toString());
        Log.i("Amplify.Test", Arrays.toString(pleaseWork.getCars().toArray()));
    }
}

