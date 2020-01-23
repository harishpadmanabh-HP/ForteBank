package com.hp.fortebank.Retro;


import com.hp.fortebank.models.BenificiaryModel;
import com.hp.fortebank.models.HistoryModel;
import com.hp.fortebank.models.LoginModel;
import com.hp.fortebank.models.OTPModel;
import com.hp.fortebank.models.UserDetailsModel;

import retrofit2.Call;
import retrofit2.http.GET;
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

    @GET("transaction_history.php?")
    Call<HistoryModel> HISTORY_MODEL_CALL(@Query("id") String id);


    @GET("user_details.php?")
    Call<UserDetailsModel> USER_DETAILS_MODEL_CALL(@Query("id") String id);
}
