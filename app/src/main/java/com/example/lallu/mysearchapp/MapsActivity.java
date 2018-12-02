package com.example.lallu.mysearchapp;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    EditText mysearch;
    Button findbutton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mysearch=(EditText)findViewById(R.id.searchedittext);
        findbutton=(Button) findViewById(R.id.searchbtn);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> addressList = null;
                MarkerOptions mo=new MarkerOptions();
                                String location=mysearch.getText().toString();
                                if (!location.equals("")){
                                    Geocoder geocoder=new Geocoder(getApplicationContext());
                                    try {
                                        addressList=geocoder.getFromLocationName(location,5);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    for (int i=0;i<addressList.size();i++){
                                        Address myaddress=addressList.get(i);
                                        mMap.clear();
                                        LatLng latLng=new LatLng(myaddress.getLatitude(),myaddress.getLongitude());
                                        mo.position(latLng);
                                        mo.title("Your search here");
                                        mMap.addMarker(mo);
                                        float zoomVariable=16.1f;
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoomVariable));



                                    }

                                }



            }
        });
        SupportMapFragment mapfrag=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapfrag.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap){

        mMap=googleMap;

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



}
