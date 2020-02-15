package com.example.taskscheduling.ui;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.taskscheduling.BLL.LoginBLL;
import com.example.taskscheduling.BLL.RegisterBLL;
import com.example.taskscheduling.R;
import com.example.taskscheduling.Interface.Users;
import com.example.taskscheduling.Model.User;
import com.example.taskscheduling.Strictmode.StrictMod;


public class Register extends Fragment implements View.OnClickListener{

    private EditText name, address, contact, username, email, password;
    private Button btnRegister;

    public String BASE_URL = "http://10.0.2.2:3000/";

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_register, container, false);

        name = v.findViewById(R.id.name);

        contact = v.findViewById(R.id.contact);
        address = v.findViewById(R.id.address);
        username = v.findViewById(R.id.username);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        btnRegister = v.findViewById(R.id.regisbutton);

        btnRegister.setOnClickListener(this);

        return v;
    }


    public void onClick(View view) {
        register();

    }

    private void register() {
//        Log.d("VAL", "BTNCLICKED ");
//
//        if (nullValidation()) {
//
//            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            Users userInterface = retrofit.create(Users.class);
//            final User user = new User(
//                    name.getText().toString().trim(),
//
//                    contact.getText().toString().trim(),
//                    address.getText().toString().trim(),
//                    username.getText().toString().trim(),
//                    email.getText().toString().trim(),
//                    password.getText().toString().trim());
//            Call<ResponseBody> call = userInterface.userRegistration(user);
//
//
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                    Log.d("VAL", "success ");
//
//                    if (response.isSuccessful()) {
//                        Toast.makeText(getActivity(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
//
//                        Log.d("VAL", "success response ");
//
//                        name.setText("");
//                        email.setText("");
//                        username.setText("");
//                        contact.setText("");
//                        address.setText("");
//                        password.setText("");
//                       // startActivity(new Intent(Register.this, Login.class));
//                    } else {
//                        try {
//                            Log.d("VAL", response.toString());
//                        Toast.makeText(getActivity(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    Log.d("VAL", t.getLocalizedMessage());
//
//                }
//
//
//
//            });
//    }




//
//            final User registerBLL = new User(
//                    name.getText().toString().trim(),
//
//                    contact.getText().toString().trim(),
//                    address.getText().toString().trim(),
//                    username.getText().toString().trim(),
//                    email.getText().toString().trim(),
//                    password.getText().toString().trim());
//
//
//
//        final RegisterBLL bll = new RegisterBLL();
//        if (bll.registerUser(registerBLL)) {
//            name.setText("");
//            contact.setText("");
//            address.setText("");
//            email.setText("");
//            username.setText("");
//            password.setText("");
//
//
//        }
        StrictMod.StrictMode();



    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(address.getText().toString())){
            address.setError("Required Field");
            return false;
        }


        else if (TextUtils.isEmpty(contact.getText().toString())){
            contact.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(username.getText().toString())){
            username.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required Field");
            return false;
        }

        return true;
    }

}
