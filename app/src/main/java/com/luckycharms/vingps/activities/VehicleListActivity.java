package com.luckycharms.vingps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class VehicleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        // Setup
        initializeCarSearchSpinner();

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
                break;
//            case "Location":
////                request = ModelQuery.list(Car.class, Car.LAT);
//                request = ModelQuery.list(Car.class, Car.MODEL.contains(inputQuery));
//                break;
            default:
                request = ModelQuery.list(Car.class, Car.MODEL.contains(inputQuery));
                break;
        }

        Amplify.API.query(
                request,
                response -> {
                    int counter = 0;
                    for (Car car : response.getData()) {
//                        Log.i("Amplify.CarSearch", car.toString());
                        counter++;
                    }
                    Log.i("Amplify.CarSearch", Integer.toString(counter) + " Items Returned");
                },
                error -> Log.e("Amplify.CarSearch", error.toString())
        );
    }
}