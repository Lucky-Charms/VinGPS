package com.luckycharms.vingps.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplifyframework.datastore.generated.model.Car;
import com.luckycharms.vingps.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "imageURL";
    private static final String ARG_PARAM2 = "make";
    private static final String ARG_PARAM3 = "model";
    private static final String ARG_PARAM4 = "distance";
    private static final String ARG_PARAM5 = "status";

    // TODO: Rename and change types of parameters
    private String mImageURL;
    private String mMake;
    private String mModel;
    private String mDistance;
    private String mStatus;

    public CarSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarSearchFragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        CarSearchFragment fragment = new CarSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageURL = getArguments().getString(ARG_PARAM1);
            mMake = getArguments().getString(ARG_PARAM2);
            mModel = getArguments().getString(ARG_PARAM3);
            mDistance = getArguments().getString(ARG_PARAM4);
            mStatus = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_search, container, false);
    }
}