package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.taskscheduling.Create;


import com.example.taskscheduling.R;

public class Addmyexpense extends AppCompatActivity {
    EditText  sharec;
    Button sharebutton;
    TextView total;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String EventName = "nameKey";

    private NotificationManagerCompat notificationManagerCompat;
    private Context context;

    private static final String TAG = "MyActivity";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmyevents);

        total = findViewById(R.id.total);
        sharec = findViewById(R.id.shareeventmoney);

        sharebutton = findViewById(R.id.sharebutton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

          String previous = sharedpreferences.getString(EventName,"");
            total.setText(previous);

        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = sharec.getText().toString();

               String previousa = sharedpreferences.getString(EventName,"");
//
               int currentmoney = Integer.parseInt(name);
               int prevmoney = Integer.parseInt(previousa);
//
               int tot = currentmoney + prevmoney;

               String tota = String.valueOf(tot);



               // int finalamount =  + prevmoney;
                //  String store = String.valueOf(finalamount);

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(EventName, tota);

                editor.commit();


                notificationManagerCompat = NotificationManagerCompat.from(Addmyexpense.this);
                Create channel = new Create(Addmyexpense.this);
                channel.createChannel();

                Notification notification = new NotificationCompat.Builder(Addmyexpense.this, Create.CHANNEL_1)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Your Expense Added")
                        .setContentText("Your total expense has been added to past")
                        .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                        .build();


                notificationManagerCompat.notify(1,notification);


                Toast.makeText(Addmyexpense.this,tota,Toast.LENGTH_LONG).show();


            }
        });
    }



    }

