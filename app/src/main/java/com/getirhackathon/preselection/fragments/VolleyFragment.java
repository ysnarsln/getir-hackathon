package com.getirhackathon.preselection.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.getirhackathon.preselection.R;
import com.getirhackathon.preselection.activities.MainActivity;
import com.getirhackathon.preselection.interfaces.VolleyInteractions;
import com.getirhackathon.preselection.models.Element;
import com.getirhackathon.preselection.models.GetirRequest;
import com.getirhackathon.preselection.models.GetirResponse;
import com.getirhackathon.preselection.networks.VolleyRequest;
import com.getirhackathon.preselection.utils.CustomView;
import com.google.gson.Gson;

import java.util.List;

public class VolleyFragment extends Fragment {

    private String TAG = VolleyFragment.class.getName();

    private Context mContext;
    private VolleyRequest mVolley;
    private Gson gson;
    private ContentLoadingProgressBar mContentLoadingProgressBar;

    public VolleyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context != null && context instanceof MainActivity)
            this.mContext = context;

        if (mVolley == null)
            mVolley = VolleyRequest.getInstance(mContext);

        if (gson == null)
            gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Hello its VolleyRequest Fragment");
        View view = inflater.inflate(R.layout.fragment_volley, container, false);
        mContentLoadingProgressBar = (ContentLoadingProgressBar) view.findViewById(R.id.clpLoading);
        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_main);

        mVolley.postRequest(new GetirRequest(), new VolleyInteractions() {
            @Override
            public void onSuccess(GetirResponse getirResponse) {
//                    Log.d(TAG, "onSuccess: " + elementList.get(0).getType());

                CustomView customView;
                List<Element> elementList = getirResponse.getElements();
                if (elementList != null) {
                    for (Element item : elementList) {

                        //it causes null reference object error. you have to check it null or not
                        if (mContext != null) {
                            customView = new CustomView(mContext, item);
                            frameLayout.addView(customView);
                        }

                    }
                }

            }

            @Override
            public void onError(String reason, String errorMessage) {
                Log.d(TAG, "onError: " + errorMessage);
                if (getContext()==null || !isAdded()) return;
                new AlertDialog.Builder(getContext())
                        .setTitle(reason)
                        .setMessage(errorMessage)
                        .setNeutralButton(mContext.getString(R.string.error_msg_btn_txt), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onBeforeRequest() {
                mContentLoadingProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAfterRequest() {
                mContentLoadingProgressBar.setVisibility(View.INVISIBLE);

            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext = null;
    }

}
