package com.example.rivios_.sportapp.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.rivios_.sportapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.google.maps.model.LatLng;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GeoApiContext geoCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng bezveze = new LatLng(-34, 149);
        //mMap.addMarker(new MarkerOptions().position(convert(sydney)).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(convert(sydney)));
        //mMap.addMarker(new MarkerOptions().position(convert(bezveze)).title("Marker in Bezveze"));

        geoCtx = new GeoApiContext.Builder()
                .apiKey(getString(R.string.google_directions_key))
                .queryRateLimit(3)
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();

        DateTime now = new DateTime();

        DirectionsResult results;
        try {
            results = DirectionsApi.newRequest(geoCtx)
                    .mode(TravelMode.DRIVING)
                    .origin(sydney)
                    .destination(bezveze)
                    .departureTime(now)
                    .await();
        }
        catch (Exception e)
        {
            Log.d("PERO", "Nesto ne valja s mapom.");
            Log.d("PERO", e.toString());
            return;
        }

        mMap.addMarker(new MarkerOptions().position(convert(new LatLng(results.routes[0].legs[0].startLocation.lat,results.routes[0].legs[0].startLocation.lng))).title("start"));
        mMap.addMarker(new MarkerOptions().position(convert(new LatLng(results.routes[0].legs[0].endLocation.lat,results.routes[0].legs[0].endLocation.lng))).title("end"));

        ArrayList<com.google.android.gms.maps.model.LatLng> decodedPath = new ArrayList<>();
        for (LatLng ll : results.routes[0].overviewPolyline.decodePath())
        {
            decodedPath.add(convert(ll));
        }

        Log.d("PERO", "Broj tocaka " + decodedPath.size());

        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
    }

    private com.google.android.gms.maps.model.LatLng convert(LatLng ll)
    {
        return new com.google.android.gms.maps.model.LatLng(ll.lat, ll.lng);
    }
}
