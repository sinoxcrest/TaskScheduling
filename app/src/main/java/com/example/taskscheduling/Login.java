package com.example.taskscheduling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
 EditText email,password;
 Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
    login=findViewById(R.id.button);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Loginclass login=new Loginclass(email.getText().toString(),password.getText().toString());
            if (login.email.equals("admin") && login.password.equals("admin")){
                Intent intent=new Intent(Login.this,Dashboard.class);
                startActivity(intent);
             
            }
            else{
                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
            }

        }
    });
    }
}
