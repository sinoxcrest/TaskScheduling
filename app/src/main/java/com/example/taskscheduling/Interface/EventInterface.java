package com.example.taskscheduling.Interface;

import com.example.taskscheduling.Model.Event;
import com.example.taskscheduling.Model.ImageUploads;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface EventInterface {

//    @POST("events")
//    Call<List<Event>> getEvents(@Header("Cookie") String cookie);

//    @POST("events")
//    Call<Void> addEvent(@Header("Cookie") String cookie, @Body Event events);


    // 2. Using @Field
//    @FormUrlEncoded
//    @POST("events")
//    Call<Void> addEvent(@Header("Cookie") String cookie, @Field("name") String eventName, @Field("place") String place,
//                        @Field("purpose") String purpose, @Field("description") String description, @Field("date") String date
//    );

    // 3. Using @FieldMap
    @FormUrlEncoded
    @POST("requestevent")
    Call<Void> addEvent(@Header("Cookie") String cookie, @FieldMap Map<String,String> map);

    // For uploading image
    @Multipart
    @POST("upload")
    Call<ImageUploads> uploadImage(@Header("Cookie") String cookie, @Part MultipartBody.Part img );

    @GET("events")
    Call<List<Event>> getEvents(@Header("Cookie") String cookie);

    @GET("events/{id}")
    Call<Event> getEventsDetail(@Header("Cookie") String cookie , @Path("id") String id);



}