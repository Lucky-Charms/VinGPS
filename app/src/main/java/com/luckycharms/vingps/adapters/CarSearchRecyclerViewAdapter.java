package com.luckycharms.vingps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Car;
import com.luckycharms.vingps.R;

import java.util.ArrayList;

public class CarSearchRecyclerViewAdapter extends RecyclerView.Adapter<CarSearchRecyclerViewAdapter.CarSearchViewHolder> {

    public ArrayList<Car> checkedOutCars;
    public CarFragmentOnClickListener listener;

    public CarSearchRecyclerViewAdapter(ArrayList<Car> checkedOutCars, CarFragmentOnClickListener listener) {
        this.checkedOutCars = checkedOutCars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_checked_out_cars, parent, false);

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
        holder.car = checkedOutCars.get(position);

        ImageView image = holder.itemView.findViewById(R.id.checkedOutCarFragmentImage);
        TextView make = holder.itemView.findViewById(R.id.checkedOutCarFragmentMake);
        TextView model = holder.itemView.findViewById(R.id.checkedOutCarFragmentModel);
//        TextView distance = holder.itemView.findViewById(R.id.checkedOutCarFragmentDistance);
        TextView status = holder.itemView.findViewById(R.id.checkedOutCarFragmentStatus);
        String carStatus = holder.car.getStatus() ? "Checked out" : "Available";

        image.setImageURI(null); // holder.car.getImageUrl()
        make.setText(holder.car.getMake());
        model.setText(holder.car.getModel());
        status.setText(carStatus);
//        distance.setText("100 meters");
    }

    @Override
    public int getItemCount() {
        return checkedOutCars.size();
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
