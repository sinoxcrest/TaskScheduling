package com.example.taskscheduling.ui;

import android.graphics.BitmapFactory;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskscheduling.Interface.EventInterface;
import com.example.taskscheduling.Model.Event;
import com.example.taskscheduling.url.Url;

import com.example.taskscheduling.R;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Getevents extends AppCompatActivity {

    TextView eventName, eventPlace, eventPurpose, eventDescription, eventDate;
    ImageView eventPhoto;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getevents);

        eventName = findViewById(R.id.eventName);
        eventPlace = findViewById(R.id.eventPlace);
        eventPurpose = findViewById(R.id.eventPurpose);
        eventDescription = findViewById(R.id.eventDescription);
        eventDate = findViewById(R.id.eventDate);
        eventPhoto = findViewById(R.id.eventPhototest);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String id = bundle.getString("id");


            getEventsDetail(id);

//            String image = bundle.getString("image");
//            Picasso.with(this).load("http://10.0.2.2:3000/uploads/" + image);
//            eventName.setText(bundle.getString("eventName"));
//            eventPlace.setText(bundle.getString("eventPlace"));
//            eventPurpose.setText(bundle.getString("eventPurpose"));
//            eventDescription.setText(bundle.getString("eventDescription"));
//            eventDate.setText(bundle.getString("eventDate"));


        }


    }

    private void loadData(String id) {

    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void getEventsDetail (String id) {
        EventInterface events = Url.getInstance().create(EventInterface.class);

        Call<Event> listCall = events.getEventsDetail(Url.Cookie, id);

        listCall.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Getevents.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                String imgUrl = "http://192.168.43.88:3000/uploads/"+ response.body().getImage();

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try {
                    URL url = new URL(imgUrl);
                    eventPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                eventName.setText(response.body().getName());
                eventPlace.setText(response.body().getPlace());
                eventPurpose.setText(response.body().getPurpose());
                eventDescription.setText(response.body().getDescription());
                eventDate.setText(response.body().getDate());

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
//        listCall.enqueue(new Callback<Event>() {
//            @Override
//            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(Getevents.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<Event> EventsList = response.body();
//                //Pass List to the com.example.eventnotifier.Adapter class
//                EventAdapter contactsAdapter = new EventAdapter(EventsList, Getevents.this);
////                recyclerView.setAdapter(contactsAdapter);
////                recyclerView.setLayoutManager(new LinearLayoutManager(Getevents.this));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Event>> call, Throwable t) {
//                Toast.makeText(Getevents.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
