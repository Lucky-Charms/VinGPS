package com.luckycharms.vingps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Car;
import com.luckycharms.vingps.R;

import java.util.ArrayList;

public class CarSearchRecyclerViewAdapter extends RecyclerView.Adapter<CarSearchRecyclerViewAdapter.CarSearchViewHolder> {

    public ArrayList<Car> cars;
    public CarFragmentOnClickListener listener;

    @NonNull
    @Override
    public CarSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_car_search, parent, false);

        CarSearchViewHolder viewHolder = new CarSearchViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.CarFragmentListener(viewHolder.car);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarSearchViewHolder holder, int position) {
        holder.car = cars.get(position);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public static class CarSearchViewHolder extends RecyclerView.ViewHolder {
        public Car car;
        public View itemView;

        public CarSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    public static interface CarFragmentOnClickListener {
        public void CarFragmentListener(Car car);
    }
}
