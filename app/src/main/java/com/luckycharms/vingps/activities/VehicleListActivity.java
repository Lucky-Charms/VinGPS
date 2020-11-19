package com.luckycharms.vingps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.luckycharms.vingps.R;
import com.luckycharms.vingps.adapters.CarSearchRecyclerViewAdapter;

import java.util.ArrayList;

public class VehicleListActivity extends AppCompatActivity implements CarSearchRecyclerViewAdapter.CarFragmentOnClickListener {

    RecyclerView recyclerView;
    Handler handler;
    ArrayList<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        // Setup
        initializeCarSearchSpinner();
        initializeRecyclerView();

        // Add Listeners
        addCarSearchButtonListener();
    }

    public void initializeCarSearchSpinner() {
        String[] properties = {
                "Make",
                "Model",
//                "Price",
                "Color",
                "VIN",
//                "Location"
        };
        Spinner spinner = (Spinner) findViewById(R.id.carSearchSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, properties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void addCarSearchButtonListener() {
        ((Button) findViewById(R.id.carSearchButton)).setOnClickListener(view -> {
            searchForCars();
        });
    }

    public void searchForCars() {
        EditText searchBar = findViewById(R.id.carSearchBar);
        Spinner searchSpinner = findViewById(R.id.carSearchSpinner);

        // TODO: some cleansing is needed for differing inputs (i.e. Price, Location, anything with odd characters)
        String inputQuery = searchBar.getText().toString();
        String searchedProperty = searchSpinner.getSelectedItem().toString();

        GraphQLRequest<PaginatedResult<Car>> request;
        
        // Format the search query by capitalizing the first letter in the string
        if (inputQuery.length() == 1)
            inputQuery.toUpperCase();
        else if (inputQuery.length() > 1)
            inputQuery = inputQuery.substring(0, 1).toUpperCase() + inputQuery.substring(1);

        switch (searchedProperty) {
            case "Make":
                request = ModelQuery.list(Car.class, Car.MAKE.contains(inputQuery));
                break;
            case "Model":
                request = ModelQuery.list(Car.class, Car.MODEL.contains(inputQuery));
                break;
//            case "Price":
//                request = ModelQuery.list(Car.class, Car.PRICE.contains(inputQuery));
//                break;
            case "Color":
                request = ModelQuery.list(Car.class, Car.COLOR.contains(inputQuery));
                break;
            case "VIN":
                request = ModelQuery.list(Car.class, Car.VIN.contains(inputQuery));
                // TODO: On a direct match, automatically start a new intent for the car...
                break;
//            case "Location":
////                request = ModelQuery.list(Car.class, Car.LAT);
//                request = ModelQuery.list(Car.class, Car.MODEL.contains(inputQuery));
//                break;
            default:
                request = ModelQuery.list(Car.class, Car.MODEL.contains(inputQuery));
                break;
        }

        cars.clear();

        Amplify.API.query(
                request,
                response -> {
                    int counter = 0;
                    for (Car car : response.getData()) {
                        cars.add(car);
                        counter++;
                    }
                    Log.i("Amplify.CarSearch", Integer.toString(counter) + " Items Returned");
                },
                error -> Log.e("Amplify.CarSearch", error.toString())
        );

        handler.sendEmptyMessage(1);
    }

    @Override
    public void CarFragmentListener(Car car) {
        Intent intent = new Intent(VehicleListActivity.this, VehicleDetailActivity.class);
        intent.putExtra("id", car.getId());
        intent.putExtra("make", car.getMake());
        intent.putExtra("model", car.getModel());
        intent.putExtra("color", car.getColor());
        intent.putExtra("price", car.getPrice());
        intent.putExtra("vin", car.getVin());
        intent.putExtra("lat", car.getLat());
        intent.putExtra("lon", car.getLon());
        intent.putExtra("status", car.getStatus());
        intent.putExtra("imageURL", car.getImageUrl());
        intent.putExtra("lastUserCheckedOut", car.getLastUserCheckedOut());
        Log.i("Amplify.CarSearch", "You want to view car model " + car.getModel());
        startActivity(intent);
    }

    public void initializeRecyclerView() {
        recyclerView = findViewById(R.id.checkedOutRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CarSearchRecyclerViewAdapter(cars, this));
    }
}