package com.hp.fortebank.Retro;


import com.hp.fortebank.models.BenificiaryModel;
import com.hp.fortebank.models.LoginModel;
import com.hp.fortebank.models.OTPModel;

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


    @GET("otp_generation.php?")
    Call<OTPModel> OTP_MODEL_CALL(@Query("qr_code") String qrcode,
                                  @Query("id") String id);

    @GET("beneficiary_account.php?")
    Call<BenificiaryModel> BENIFICIARY_MODEL_CALL(@Query("id") String id,
                                                  @Query("name") String name,
                                                  @Query("bank") String bank,
                                                  @Query("branch") String branch,
                                                  @Query("ifsc") String ifsc);



}
