package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskscheduling.BLL.RegisterBLL;
import com.example.taskscheduling.Interface.Users;
import com.example.taskscheduling.Model.User;
import com.example.taskscheduling.R;
import com.example.taskscheduling.Strictmode.StrictMod;
import com.example.taskscheduling.url.Url;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registerpage extends AppCompatActivity {

    private EditText name, address, contact, username, email, password;
    private Button btnRegister;


    public String BASE_URL = "http://192.168.43.88:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        SensorManager  sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor proximitysensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent.values[0] < proximitysensor.getMaximumRange()) {


                    Toast.makeText(Registerpage.this,"Application Closed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registerpage.this, Loginpage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Registerpage.this,"Far",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,proximitysensor,2*1000*1000);



        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        address = findViewById(R.id.address);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.regisbutton);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nullValidation();


                Toast.makeText(Registerpage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }


//    private void register() {
//        Log.d("VAL", "BTNCLICKED ");
//
//        if (nullValidation()==true) {
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
//                        Toast.makeText(Registerpage.this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
//
//                        Log.d("VAL", "success response ");
//
//                        name.setText("");
//                        email.setText("");
//                        username.setText("");
//                        contact.setText("");
//                        address.setText("");
//                        password.setText("");
//                        startActivity(new Intent(Registerpage.this, Loginpage.class));
//                    } else {
//                        try {
//                            Log.d("VAL", response.toString());
//                            Toast.makeText(Registerpage.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
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
//            });
//        }
//
//
//        final User registerBLL = new User(
//                name.getText().toString().trim(),
//
//                contact.getText().toString().trim(),
//                address.getText().toString().trim(),
//                username.getText().toString().trim(),
//                email.getText().toString().trim(),
//                password.getText().toString().trim());
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
//        StrictMod.StrictMode();
//    }
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
