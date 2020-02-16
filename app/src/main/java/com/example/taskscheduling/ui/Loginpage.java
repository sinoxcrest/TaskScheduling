package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskscheduling.BLL.LoginBLL;
import com.example.taskscheduling.R;
import com.example.taskscheduling.Strictmode.StrictMod;

public class Loginpage extends AppCompatActivity implements SensorEventListener {


    EditText username, password;
    Button login, regsitera;
    private boolean isSuccess=false;

    private SensorManager sensorManager;
    private boolean isMoved = false;
    private View view;
    private long lastUpdate;



  //  public  String BASE_URL = "http://10.0.2.2:3000/";


    Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();


        // Inflate the layout for this fragment


        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        login = findViewById(R.id.loginbutton);
        regsitera = findViewById(R.id.movetoregister);



        regsitera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Loginpage.this,Registerpage.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(Loginpage.this, "I am clicked", Toast.LENGTH_SHORT).show();
//
//                   if(username.getText().toString().equals("sajak") && password.getText().toString().equals("sajak")){
//                       startActivity(new Intent(Loginpage.this,Dashboard.class));
//                  }

                final LoginBLL bll =new LoginBLL(username.getText().toString(), password.getText().toString());
               StrictMod.StrictMode();
                if (bll.checkUser()) {
                    vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);


                    if (Build.VERSION.SDK_INT >= 26) {
                        vb.vibrate(VibrationEffect.createOneShot(900, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vb.vibrate(200);
                    }
                    Intent intent = new Intent(Loginpage.this, Dashboard.class);
                    startActivity(intent);
                    // finish();
                }
                else{
                    Toast.makeText(Loginpage.this, "Error ", Toast.LENGTH_SHORT).show();




                }



            }
        });



    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();
//        Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+
//                SensorManager.GRAVITY_EARTH, Toast.LENGTH_SHORT).show();

        if (accelationSquareRoot >= 2) //it will be executed if you shuffle
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle
            if (isMoved) {
                // view.setBackgroundColor(Color.GREEN);
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
                Toast.makeText(Loginpage.this,"Success",Toast.LENGTH_SHORT).show();

            } else {
                // view.setBackgroundColor(Color.RED);

                Toast.makeText(Loginpage.this,"Application Closed",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);

            }
            isMoved = !isMoved;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }




    public boolean nullValidation(){
        if (TextUtils.isEmpty(username.getText().toString())){
            username.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required Field");
            return false;
        }
        return true;
    }
//    boolean checkUser(String username, String password) {
//        Users users = Url.getInstance().create(Users.class);
//
//        Call<LoginResponse> usersCall = users.checkUser(username, password);
//
//        usersCall.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), " Either username or password is incorrect" + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    if (response.body().getSuccess()) {
//                        Url.Cookie = response.headers().get("Set-Cookie");
//                        Toast.makeText(getContext(), "Success and cookie :" , Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getContext(),"Success"+Url.Cookie,Toast.LENGTH_SHORT).show();
//
//                        isSuccess = true;
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(getContext(), " Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        return isSuccess;
//    }


}
