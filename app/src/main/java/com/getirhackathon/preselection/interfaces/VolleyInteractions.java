package com.getirhackathon.preselection.interfaces;

import com.getirhackathon.preselection.models.GetirResponse;

public interface VolleyInteractions {
    void onSuccess(GetirResponse response);

    void onError(String reason, String errorMessage);

    void onBeforeRequest();

    void onAfterRequest();
}
