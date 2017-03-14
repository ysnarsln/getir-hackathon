package com.getirhackathon.preselection.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getirhackathon.preselection.R;
import com.getirhackathon.preselection.activities.MainActivity;

public class RetrofitFragment extends Fragment {

    private String TAG = RetrofitFragment.class.getName();

    private Context mContext;

    public RetrofitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context != null && context instanceof MainActivity)
            this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Hello its Retrofit Fragment");
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);



        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext = null;
    }

}
