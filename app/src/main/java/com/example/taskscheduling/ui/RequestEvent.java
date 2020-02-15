package com.example.taskscheduling.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taskscheduling.Interface.EventInterface;
import com.example.taskscheduling.Model.ImageUploads;
import com.example.taskscheduling.R;
import com.example.taskscheduling.url.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestEvent extends AppCompatActivity {

    private EditText eventName, eventPlace, eventPurpose, eventDescription, eventDate ;
    private Button btnSave;
    private ImageView eventPhoto;
    String imagePath;
    private String imageName;
    EventInterface eventInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);











        eventInterface = Url.getInstance().create(EventInterface.class);

        eventName = findViewById(R.id.eventname);
        eventPlace = findViewById(R.id.place);
        eventPurpose = findViewById(R.id.purpose);
        eventDescription = findViewById(R.id.description);
        eventDate = findViewById(R.id.date);
        eventPhoto = findViewById(R.id.imgProfile);
        btnSave = findViewById(R.id.requestbutton);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        eventPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

    }

    private void BrowseImage() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imagePath = getRealPathFromUri(uri);
        previewImage(imagePath);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void previewImage(String imagePath) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            eventPhoto.setImageBitmap(myBitmap);
        }
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void SaveImageOnly() {

        File file = new File(imagePath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        eventInterface = Url.getInstance().create(EventInterface.class);
        Call<ImageUploads> responseBodyCall = eventInterface.uploadImage(Url.Cookie,body);

        StrictMode();

        try {
            Response<ImageUploads> imageResponseResponse = responseBodyCall.execute();
            // After saving an image, retrieve the current name of the image
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, ""+imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void Save() {
       SaveImageOnly();
        String name = eventName.getText().toString();
        String place = eventPlace.getText().toString();
        String purpose = eventPurpose.getText().toString();
        String date = eventDate.getText().toString();
        String description = eventDescription.getText().toString();


        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("place", place);
        map.put("purpose", purpose);
        map.put("description", description);
        map.put("date", date);


        map.put("image", imageName);

        EventInterface eventInterface = Url.getInstance().create(EventInterface.class);
        Call<Void> eventsCall = eventInterface.addEvent(Url.Cookie,map);


        eventsCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RequestEvent.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                //   Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RequestEvent.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RequestEvent.this, "My error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Open dashboard

    }


}
