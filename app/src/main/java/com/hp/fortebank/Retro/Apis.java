package com.hp.fortebank.Retro;


import com.hp.fortebank.models.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Apis {

    @GET("login_action.php?")
    Call<LoginModel> LOGIN_MODEL_CALL(@Query("pin") String pin,
                                      @Query("phone")String phone);
}
