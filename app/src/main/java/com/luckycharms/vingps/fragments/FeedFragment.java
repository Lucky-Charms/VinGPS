package com.luckycharms.vingps.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Car;
import com.luckycharms.vingps.R;
import com.luckycharms.vingps.activities.VehicleDetailActivity;
import com.luckycharms.vingps.activities.VehicleListActivity;
import com.luckycharms.vingps.adapters.CarSearchRecyclerViewAdapter;

import java.util.ArrayList;

public class FeedFragment extends Fragment implements CarSearchRecyclerViewAdapter.CarFragmentOnClickListener{
    RecyclerView recyclerView;
    ArrayList<Car> checkedOutCars = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        addFindCarButtonListener(view);
        getCarsFromAWS();
        initializeRecyclerView(view);
        return view;
    }

    public void addFindCarButtonListener(View view) {
        ((Button) view.findViewById(R.id.findCarButton)).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), VehicleListActivity.class));
        });
    }

    public void initializeRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.checkedOutRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CarSearchRecyclerViewAdapter(checkedOutCars, this));
    }

    public void getCarsFromAWS() {
        Amplify.API.query(
                ModelQuery.list(Car.class),
                response -> {
                    int counter = 0;
                    for (Car car : response.getData()) {
                        if (car.getStatus().equals(true)) {
                            checkedOutCars.add(car);
                        }
                        counter++;
                    }
                    Log.i("Amplify.CarSearch", Integer.toString(counter) + " Items Returned");
                },
                error -> Log.e("Amplify.CarSearch", error.toString())
        );

        checkedOutCars.clear();


    }

    @Override
    public void CarFragmentListener(Car car) {
        Intent intent = new Intent(getActivity(), VehicleDetailActivity.class);
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
//        startActivity(intent);
    }
}
