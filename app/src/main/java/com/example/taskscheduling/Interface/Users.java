package com.example.taskscheduling.Interface;
import com.example.taskscheduling.Model.LoginResponse;
import com.example.taskscheduling.Model.RegistrationResponse;
import com.example.taskscheduling.Model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Users {//    @POST("clients/register")
//    Call<ResponseBody> userRegistration(@Body User user);

    @POST("clients/login")
    Call<ResponseBody> userLogin(@Body User user);


    // For login
    @FormUrlEncoded
    @POST("clients/login")
    Call<LoginResponse> checkUser(@Field("username") String username, @Field("password") String password);

    // For Register
//    @FormUrlEncoded
//    @POST("clients/register")
//    Call<RegistrationResponse> registerUser( @Field("name") String name, @Field("contact") String contact,
//                                      @Field("address") String address,
//            @Field("username") String username, @Field("email") String email, @Field("password") String password);

    @POST("clients/register")
    Call<RegistrationResponse> registerUser(@Body User user);


}
