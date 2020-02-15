package com.example.taskscheduling.BLL;

import com.example.taskscheduling.Interface.Users;
import com.example.taskscheduling.Model.RegistrationResponse;
import com.example.taskscheduling.Model.User;
import com.example.taskscheduling.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLL {
    private String name;
    private String contact;
    private String address;
    private String username;
    private String email;
    private String password;
    boolean isSucess =false;

    public RegisterBLL(String name, String contact, String address, String username, String email, String password) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
    }





    public RegisterBLL(){

    }

    public boolean registerUser(User user){
        Users users = Url.getInstance().create(Users.class);
        Call<RegistrationResponse> userCall = users.registerUser(user);

        try{
            Response<RegistrationResponse> imageResponseResponse = userCall.execute();
            if(imageResponseResponse.isSuccessful())
            {
                isSucess = true;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

        return isSucess;
    }


}