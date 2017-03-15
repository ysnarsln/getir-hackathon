package com.getirhackathon.preselection.networks;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.getirhackathon.preselection.BuildConfig;
import com.getirhackathon.preselection.interfaces.VolleyResponse;
import com.getirhackathon.preselection.models.GetirRequest;
import com.getirhackathon.preselection.models.GetirResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class VolleyRequest {

    private static VolleyRequest mVolley;
    private RequestQueue requestQueue;
    private Gson gson;

    private VolleyRequest(Context context) {
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(context);
        if (gson == null) gson = new Gson();
    }

    public static VolleyRequest getInstance(Context context) {
        if (mVolley == null)
            mVolley = new VolleyRequest(context);
        return mVolley;
    }

    public void postRequest(final GetirRequest getirRequest, final VolleyResponse volleyResponse) {
        try{
            String tempJson = gson.toJson(getirRequest);
            JSONObject jsonObject = new JSONObject(tempJson);
            GsonPostRequest gsonPostRequest = new GsonPostRequest(
                    BuildConfig.MAINURL,
                    jsonObject.toString(),
                    new TypeToken<GetirResponse>() {}.getType(),
                    gson,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            Log.d(TAG, "onResponse: "+response.toString());
                            volleyResponse.onSuccess((GetirResponse)response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "onErrorResponse: "+error.getMessage());
                            volleyResponse.onError(error.getMessage());
                        }
                    }
            );
            requestQueue.add(gsonPostRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
