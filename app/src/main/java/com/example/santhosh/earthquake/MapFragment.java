package com.example.santhosh.earthquake;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.santhosh.earthquake.interfaces.BackPressHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback, BackPressHandler {

    public static final String TAG = MapFragment.class.getSimpleName();
    private MapView mapView;
    private GoogleMap googleMap;

    private Double latitude;
    private Double longitude;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_view, container, false);

        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;
        if(bundle != null){
            longitude = bundle.getDouble("lng");
            latitude = bundle.getDouble("lat");
        }

        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        LatLng earthQuakeLocation = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions().position(earthQuakeLocation).title("sad"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(earthQuakeLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getChildFragmentManager();
        if(fragmentManager.getBackStackEntryCount() > 0){
            if (fragmentManager.findFragmentByTag(EarthQuakeFragment.TAG) != null) {
                if (!((BackPressHandler) fragmentManager.findFragmentByTag(EarthQuakeFragment.TAG)).onBackPressed()) {
                    getChildFragmentManager().popBackStack();
                } else {
                    getChildFragmentManager().popBackStack();
                }
            } else {
                getFragmentManager().popBackStack();
            }
        }
        return false;
    }
}
