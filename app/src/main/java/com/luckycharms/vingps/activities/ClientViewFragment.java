package com.luckycharms.vingps.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplifyframework.datastore.generated.model.Car;
import com.luckycharms.vingps.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "firstName";
    private static final String ARG_PARAM2 = "lastName";
//    private static final String ARG_PARAM3 = "phone";
//    private static final String ARG_PARAM4 = "email";
//    private static final String ARG_PARAM5 = "lastSalesPerson";
//    private static final String ARG_PARAM6 = "license";
//    private static final String ARG_PARAM7 = "licenseImageUrl";


    // TODO: Rename and change types of parameters
    private String mFirstName;
    private String mLastName;
//    private String mPhone;
//    private String mEmail;
//    private String mLastSalesPerson;
//    private String mLicense;
//    private String mLicenceImageUrl;

//    List<Car> cars =  MainActivity.currentClient.getCars();

//    ArrayList<Car> cars =  MainActivity.currentClient.getCars();

    public ClientViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientViewFragment newInstance(String param1, String param2
    //    String param3, String param4, String param5, String param6, String param7
        ) {
        ClientViewFragment fragment = new ClientViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
//        args.putString(ARG_PARAM3, param3);
//        args.putString(ARG_PARAM4, param4);
//        args.putString(ARG_PARAM5, param5);
//        args.putString(ARG_PARAM6, param6);
//        args.putString(ARG_PARAM7, param7);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFirstName = getArguments().getString(ARG_PARAM1);
            mLastName = getArguments().getString(ARG_PARAM2);
//            mPhone = getArguments().getString(ARG_PARAM3);
//            mEmail= getArguments().getString(ARG_PARAM4);
//            mLastSalesPerson = getArguments().getString(ARG_PARAM5);
//            mLicense = getArguments().getString(ARG_PARAM6);
//            mLicenceImageUrl = getArguments().getString(ARG_PARAM7);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_view, container, false);
    }
}