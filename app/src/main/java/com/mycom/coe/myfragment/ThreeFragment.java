package com.mycom.coe.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.os.TraceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {


    private ThreeFragment() {
        // Required empty public constructor
    }

    public static ThreeFragment newInstance(String index) {
        ThreeFragment fragment = new ThreeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", index);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String key = getArguments().getString("KEY");
        Toast.makeText(getActivity() , "KEY", Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

}
