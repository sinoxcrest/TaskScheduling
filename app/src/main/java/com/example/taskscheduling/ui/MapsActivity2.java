package com.example.taskscheduling.ui;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;


import com.example.taskscheduling.Model.LatitudeLongitude;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.Marker;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;



import com.example.taskscheduling.R;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    private AutoCompleteTextView cityName;
    private Button btnSearch;
    private List<LatitudeLongitude> latitudeLongitudeList;
    Marker markerName;
    CameraUpdate center, zoom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

         cityName = findViewById(R.id.searchcity);
            btnSearch = findViewById(R.id.searchButton);

            fillArrayListAndSetAdapter();

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(TextUtils.isEmpty(cityName.getText().toString()))
                    {
                         cityName.setError("Enter City Name");
                            return;
                    }
                int position = SearchArrayList(cityName.getText().toString());
                    if(position> -1)
                        loadMap(position);
                    else
                        Toast.makeText(MapsActivity2.this, "Location not found" + cityName.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });


    }
    public void loadMap(int position){
        if(markerName != null){
            markerName.remove();
        }
        double latitude = latitudeLongitudeList.get(position).getLat();
        double longitude = latitudeLongitudeList.get(position).getLon();
        String marker = latitudeLongitudeList.get(position).getMarker();
        center = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        zoom = CameraUpdateFactory.zoomTo(17);
        markerName = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(marker));
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    public void fillArrayListAndSetAdapter(){
        latitudeLongitudeList = new ArrayList<>();
        latitudeLongitudeList.add(new LatitudeLongitude(27.7134481, 85.3241922, "Naagpokhari"));

    String[] data = new String[latitudeLongitudeList.size()];

    for (int i = 0; i< data.length; i++){
        data[i] = latitudeLongitudeList.get(i).getMarker();
    }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MapsActivity2.this, android.R.layout.simple_list_item_1, data);
            cityName.setAdapter(adapter);
            cityName.setThreshold(1);
    }

    public int SearchArrayList(String name){
        for (int i=0; i<latitudeLongitudeList.size(); i++){
            if(latitudeLongitudeList.get(i).getMarker().contains(name)){
                return i;
            }
        }


        return -1;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        center = CameraUpdateFactory.newLatLng(new LatLng(27.7172453, 85.3239605));
        zoom = CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }




}