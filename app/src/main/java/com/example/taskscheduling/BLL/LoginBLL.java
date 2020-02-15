package com.example.taskscheduling.BLL;

import android.content.Context;
import android.net.Uri;

import com.example.taskscheduling.Interface.Users;
import com.example.taskscheduling.Model.LoginResponse;
import com.example.taskscheduling.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    private String username;
    private String password;
    Context context;
    boolean isSuccess = false;


    public LoginBLL( String username, String password) {
        //this.context = context;
        this.username = username;
        this.password = password;

    }
    public void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }





    public boolean checkUser() {
        Users users = Url.getInstance().create(Users.class);
        Call<LoginResponse> usersCall = users.checkUser(username, password);

        try {
            Response<LoginResponse> imageResponseResponse = usersCall.execute();
            if (imageResponseResponse.body().getSuccess()) {
                Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


}
