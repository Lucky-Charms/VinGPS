package com.luckycharms.vingps.adapters;

//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.amplifyframework.datastore.generated.model.Car;
//import com.luckycharms.vingps.R;
//
//import java.util.ArrayList;
//
//public class CheckedOutCarRecyclerViewAdapter extends RecyclerView.Adapter<CheckedOutCarRecyclerViewAdapter> {
//    public ArrayList<Car> checkedOutCars;
//    public CheckedOutCarFragmentOnClickListener listener;
//
//    public CheckedOutCarRecyclerViewAdapter(ArrayList<Car> checkedOutCars, CheckedOutCarFragmentOnClickListener listener) {
//        this.checkedOutCars = checkedOutCars;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public CheckedOutCarRecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_checked_out_cars, parent, false);
//
//
//    }
//
////    @Override
////    public void onBindViewHolder(@NonNull CheckedOutCarRecyclerViewAdapter holder, int position) {
////
////    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public static interface CheckedOutCarFragmentOnClickListener {
//        public void CheckedOutCarFragmentListener(Car car);
//    }
//}
