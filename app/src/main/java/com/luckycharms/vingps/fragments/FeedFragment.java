package com.luckycharms.vingps.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
import com.luckycharms.vingps.activities.ClientListActivity;
import com.luckycharms.vingps.activities.VehicleDetailActivity;
import com.luckycharms.vingps.activities.VehicleListActivity;
import com.luckycharms.vingps.adapters.CarSearchRecyclerViewAdapter;

import java.util.ArrayList;

public class FeedFragment extends Fragment implements CarSearchRecyclerViewAdapter.CarFragmentOnClickListener{
    RecyclerView recyclerView;
    Handler handler;
    ArrayList<Car> checkedOutCars = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        initializeRecyclerView(view, this);
        getCarsFromAWS(view);
        addFindCarButtonListener(view);
        addFindClientButtonListener(view);
        return view;
    }

    public void addFindCarButtonListener(View view) {
        ((Button) view.findViewById(R.id.findCarButton)).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), VehicleListActivity.class));
        });
    }

    public void addFindClientButtonListener(View view) {
        ((Button) view.findViewById(R.id.findClientButton)).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ClientListActivity.class));
        });
    }

    public void initializeRecyclerView(View view, FeedFragment feedFragment) {
        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        recyclerView = view.findViewById(R.id.checkedOutRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CarSearchRecyclerViewAdapter(checkedOutCars, feedFragment));
    }

    private void layoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void getCarsFromAWS(View view) {
        checkedOutCars.clear();
        Amplify.API.query(
                ModelQuery.list(Car.class),
                response -> {
                    int counter = 0;
                    if(response.getData() != null) {
                        for (Car car : response.getData()) {
                            if (car.getStatus().equals(true)) {
                                checkedOutCars.add(car);
                            }
                            counter++;
                        }
                    }
                    handler.sendEmptyMessage(1);
                    Log.i("Amplify.CheckedOutCars", Integer.toString(counter) + " Items Returned");
                },
                error -> Log.e("Amplify.CheckedOutCars", error.toString())
        );
    }

    @Override
    public void CarFragmentListener(Car car) {
        Intent intent = new Intent(getActivity(), VehicleDetailActivity.class);
        intent.putExtra("id", car.getId());
        intent.putExtra("year", car.getYear());
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
}
