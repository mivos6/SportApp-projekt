package com.example.rivios_.sportapp.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap map;
    private GeoApiContext geoCtx;

    private static final LatLng cro = new LatLng(44.7380161, 18.1921251);

    ArrayList<LatLng> checkpoints = new ArrayList<LatLng>();
    String encodedRoute = "";
    long routeLength = 0;

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
        map = googleMap;
        geoCtx = new GeoApiContext.Builder()
                .apiKey(getString(R.string.google_directions_key))
                .queryRateLimit(3)
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(convert(cro), 8));
        map.setOnMapClickListener(this);
    }

    private com.google.android.gms.maps.model.LatLng convert(LatLng ll)
    {
        return new com.google.android.gms.maps.model.LatLng(ll.lat, ll.lng);
    }

    @Override
    public void onMapClick(com.google.android.gms.maps.model.LatLng latLng) {
        MarkerOptions opt = new MarkerOptions().position(latLng);
        if (checkpoints.size() == 0) {
            opt.title("Start");
        } else {
            opt.title("Stanica " + checkpoints.size());
        }
        map.addMarker(opt);
        checkpoints.add(new LatLng(latLng.latitude, latLng.longitude));

        DirectionsResult results;

        if (checkpoints.size() > 1) {
            try {
                results = DirectionsApi.newRequest(geoCtx)
                        .mode(TravelMode.WALKING)
                        .origin(checkpoints.get(checkpoints.size() - 2))
                        .destination(checkpoints.get(checkpoints.size() - 1))
                        .await();
            } catch (Exception e) {
                Log.d("PERO", "Nesto ne valja s mapom.");
                Log.d("PERO", e.toString());
                return;
            }

            encodedRoute += results.routes[0].overviewPolyline.getEncodedPath();
            routeLength += results.routes[0].legs[0].distance.inMeters;

            ArrayList<com.google.android.gms.maps.model.LatLng> decodedPath = new ArrayList<>();
            for (LatLng ll : results.routes[0].overviewPolyline.decodePath())
            {
                decodedPath.add(convert(ll));
            }

            map.addPolyline(new PolylineOptions().addAll(decodedPath));
        }
    }

    public void undoMarker(View v)
    {

    }

    public void saveRoute(View v)
    {

    }
}
