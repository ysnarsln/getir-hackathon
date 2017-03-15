package com.getirhackathon.preselection.interfaces;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("/getElements")
    Call<> getInfo(@Query("id") String cityId, @Query("units") String units, @Query("APPID") String appId);

}
