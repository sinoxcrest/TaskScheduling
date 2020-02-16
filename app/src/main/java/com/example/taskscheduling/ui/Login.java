package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import android.util.Log;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.content.Intent;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.taskscheduling.BLL.LoginBLL;
import com.example.taskscheduling.R;
import com.example.taskscheduling.Model.User;
import com.example.taskscheduling.Model.LoginResponse;
import com.example.taskscheduling.Interface.Users;
import com.example.taskscheduling.Strictmode.StrictMod;
import com.example.taskscheduling.url.Url;


public class Login extends AppCompatActivity {

//   EditText username, password;
//    Button login, regsitera;
//    private boolean isSuccess=false;
//
//
//
//    public  String BASE_URL = "http://10.0.2.2:3000/";
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login2);
//
//
//        // Inflate the layout for this fragment
//
//
//        username = findViewById(R.id.txtusername);
//        password = findViewById(R.id.txtpassword);
//        login = findViewById(R.id.loginbutton);
//        regsitera = findViewById(R.id.movetoregister);
//
//        login.setOnClickListener(new View.OnClickListener() {
//
//                                     @Override
//                                     public void onClick(View v) {
//                    Intent intent = new Intent(Login.this, Register.class);
//                    startActivity(intent);
//                                     }
//                                 });
//
//        login.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//
//                final LoginBLL bll =new LoginBLL(username.getText().toString(), password.getText().toString());
//                StrictMod.StrictMode();
//                if (bll.checkUser()) {
//
//                    Intent intent = new Intent(Login.this, Dashboard.class);
//                    startActivity(intent);
//                   // finish();
//                }
//                else{
//                    Toast.makeText(Login.this, "Error ", Toast.LENGTH_SHORT).show();
//                }
//
//
//
//            }
//        });
//
//
//
//    }
//
//
//
//    public boolean nullValidation(){
//        if (TextUtils.isEmpty(username.getText().toString())){
//            username.setError("Required Field");
//            return false;
//        }
//        else if (TextUtils.isEmpty(password.getText().toString())){
//            password.setError("Required Field");
//            return false;
//        }
//        return true;
//    }
////    boolean checkUser(String username, String password) {
////        Users users = Url.getInstance().create(Users.class);
////
////        Call<LoginResponse> usersCall = users.checkUser(username, password);
////
////        usersCall.enqueue(new Callback<LoginResponse>() {
////            @Override
////            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
////                if (!response.isSuccessful()) {
////                    Toast.makeText(getContext(), " Either username or password is incorrect" + response.code(), Toast.LENGTH_SHORT).show();
////                    return;
////                } else {
////                    if (response.body().getSuccess()) {
////                        Url.Cookie = response.headers().get("Set-Cookie");
////                        Toast.makeText(getContext(), "Success and cookie :" , Toast.LENGTH_SHORT).show();
////                        Toast.makeText(getContext(),"Success"+Url.Cookie,Toast.LENGTH_SHORT).show();
////
////                        isSuccess = true;
////                    }
////                }
////            }
////
////            @Override
////            public void onFailure(Call<LoginResponse> call, Throwable t) {
////                Toast.makeText(getContext(), " Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
////        return isSuccess;
////    }
//
//


}

