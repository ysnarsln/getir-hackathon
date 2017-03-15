package com.getirhackathon.preselection.networks;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.getirhackathon.preselection.BuildConfig;
import com.getirhackathon.preselection.R;
import com.getirhackathon.preselection.interfaces.VolleyInteractions;
import com.getirhackathon.preselection.models.GetirRequest;
import com.getirhackathon.preselection.models.GetirResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class VolleyRequest {

    private static VolleyRequest mVolley;
    private RequestQueue requestQueue;
    private Gson gson;
    private Context mContext;

    private VolleyRequest(Context context) {
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(context);
        if (gson == null) gson = new Gson();
        if (mContext == null) mContext = context;
    }

    public static VolleyRequest getInstance(Context context) {
        if (mVolley == null)
            mVolley = new VolleyRequest(context);
        return mVolley;
    }

    public void postRequest(final GetirRequest getirRequest, final VolleyInteractions volleyInteractions) {
        volleyInteractions.onBeforeRequest();
        try {
            String tempJson = gson.toJson(getirRequest);
            JSONObject jsonObject = new JSONObject(tempJson);
            @SuppressWarnings("unchecked")
            GsonPostRequest gsonPostRequest = new GsonPostRequest(
                    BuildConfig.MAINURL,
                    jsonObject.toString(),
                    getirRequest.getResponseType(),
                    gson,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            Log.d(TAG, "onResponse: " + response.toString());
                            GetirResponse getirResponse = (GetirResponse) response;
                            if (getirResponse.getCode() == 0) {
                                volleyInteractions.onSuccess(getirResponse);
                            } else {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_general_title), getirResponse.getMsg());
                            }
                            volleyInteractions.onAfterRequest();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "onErrorResponse: " + error.getMessage());
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_timeout), mContext.getString(R.string.error_msg_reason) + error.getMessage());
                            } else if (error instanceof AuthFailureError) {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_auth_failure), mContext.getString(R.string.error_msg_reason) + error.getMessage());
                            } else if (error instanceof ServerError) {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_server), mContext.getString(R.string.error_msg_reason) + error.getMessage());
                            } else if (error instanceof NetworkError) {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_network), mContext.getString(R.string.error_msg_reason) + error.getMessage());
                            } else if (error instanceof ParseError) {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_parse), mContext.getString(R.string.error_msg_reason) + error.getMessage());
                            } else {
                                volleyInteractions.onError(mContext.getString(R.string.error_msg_general_title), mContext.getString(R.string.error_msg_reason) + error.getMessage());
                            }
                            volleyInteractions.onAfterRequest();

                        }
                    }
            );
            requestQueue.add(gsonPostRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
