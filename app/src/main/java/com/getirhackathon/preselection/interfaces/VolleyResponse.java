package com.getirhackathon.preselection.interfaces;

import com.getirhackathon.preselection.models.ShapeProperties;

import java.util.List;

public interface VolleyResponse {
    void onSuccess(List<ShapeProperties> shapePropertiesList);
    void onError(String errorMessage);
}
