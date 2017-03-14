package com.getirhackathon.preselection.networks;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.getirhackathon.preselection.interfaces.VolleyResponse;
import com.getirhackathon.preselection.models.ShapeProperties;
import com.getirhackathon.preselection.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

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
        if(mVolley == null)
            mVolley = new VolleyRequest(context);
        return mVolley;
    }

    public void Request(JSONObject jsonObject, final VolleyResponse volleyResponse){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: "+response.toString());

                try {
                    Type type = new TypeToken<List<ShapeProperties>>(){}.getType();
                    List<ShapeProperties> shapeList = gson.fromJson(response.getJSONArray("elements").toString(), type);
                    volleyResponse.onSuccess(shapeList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
                volleyResponse.onError(error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
