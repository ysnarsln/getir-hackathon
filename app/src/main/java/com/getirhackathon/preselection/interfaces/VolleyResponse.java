package com.getirhackathon.preselection.interfaces;

import com.getirhackathon.preselection.models.GetirResponse;

public interface VolleyResponse {
    void onSuccess(GetirResponse response);
    void onError(String errorMessage);
}
