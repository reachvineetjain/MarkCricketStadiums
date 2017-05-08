package com.nehvin.markcricketstadiums;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    boolean mapReady=false;
    private LocationManager locMgr;
    private LocationListener locListner;

    private static final CameraPosition cameraPosition = CameraPosition.builder()
            .target(new LatLng(18.9388656,72.8349698))
            .bearing(0)
            .zoom(5)
            .tilt(0)
            .build();

    private MarkerOptions wankhede;
    private MarkerOptions eden;
    private MarkerOptions PCA;
    private MarkerOptions rajiv;
    private MarkerOptions MCA;
    private MarkerOptions saurashtra;
    private MarkerOptions holkar;
    private MarkerOptions chinna;
    private MarkerOptions ferozshah;
    private MarkerOptions greenpark;
    private MarkerOptions dharam;
    // Wankhede Stadium 18.9389044,72.8235896
    // Eden Gardens 22.564613,88.3410758
    // Punjab Cricket Association Stadium - 30.6908936,76.7353423
    // Rajiv Gandhi International Cricket Stadium - 17.4065262,78.548271
    // MCA Pune International Cricket Stadium - 18.6745533,73.7043628
    // Saurashtra Cricket Association Stadium , Rajkot 22.3628812,70.7077379
    // Holkar Stadium - 22.7242592,75.8774681
    // M Chinnaswamy Stadium - 12.9790072,77.5972145
    // Feroz Shah Kotla Stadium - 28.637743,77.240835
    // Green Park Stadium - 26.4822328,80.3455433
    // Himachal Pradesh Cricket Association - 32.1976142,76.3237065

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dharam = new MarkerOptions()
                .position(new LatLng(32.1976142,76.3237065))
                .title("Himachal Pradesh Cricket Association")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        greenpark = new MarkerOptions()
                .position(new LatLng(26.4822328,80.3455433))
                .title("Green Park Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        ferozshah = new MarkerOptions()
                .position(new LatLng(28.637743,77.240835))
                .title("Feroz Shah Kotla Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        chinna = new MarkerOptions()
                .position(new LatLng(12.9790072,77.5972145))
                .title("M Chinnaswamy Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        holkar = new MarkerOptions()
                .position(new LatLng(22.7242592,75.8774681))
                .title("Holkar Stadium ")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        saurashtra = new MarkerOptions()
                .position(new LatLng(22.3628812,70.7077379))
                .title("Saurashtra Cricket Association Stadium , Rajkot")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        MCA = new MarkerOptions()
                .position(new LatLng(18.6745533,73.7043628))
                .title("MCA Pune International Cricket Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        rajiv = new MarkerOptions()
                .position(new LatLng(17.4065262,78.548271))
                .title("Rajiv Gandhi International Cricket Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        PCA = new MarkerOptions()
                .position(new LatLng(30.6908936,76.7353423))
                .title("Punjab Cricket Association Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

        eden = new MarkerOptions()
                .position(new LatLng(22.564613,88.3410758))
                .title("Eden Gardens")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        wankhede = new MarkerOptions()
                .position(new LatLng(18.9389044,72.8235896))
                .title("Wankhede Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapReady = true;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        locMgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Location localLocation = fetchBestLocation();
        locListner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location){

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
        };
        if (Build.VERSION.SDK_INT < 23)
        {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListner);
        }
        else
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            else
            {
                locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListner);
                Location location = fetchBestLocation();
                updateCurrentLoc(location);
            }
        }
        addStadiumMarkers();
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(18.9389044,72.8235896)) //wankhede
                .add(new LatLng(22.3628812,70.7077379)) //saurshtra
                .add(new LatLng(22.7242592,75.8774681)) //holkar
                .add(new LatLng(32.1976142,76.3237065)) //dharam
                .add(new LatLng(30.6908936,76.7353423)) //PCA
                .add(new LatLng(28.637743,77.240835)) //feroz shah
                .add(new LatLng(26.4822328,80.3455433)) // green park
                .add(new LatLng(22.564613,88.3410758)) //eden
                .add(new LatLng(17.4065262,78.548271)) // hyd
                .add(new LatLng(12.9790072,77.5972145)) //chinna
                .add(new LatLng(18.6745533,73.7043628)) //pune
                .add(new LatLng(18.9389044,72.8235896))); //wan

        mMap.addPolygon(new PolygonOptions().geodesic(true)
        .add(new LatLng(18.9389044,72.8235896))
        .add(new LatLng(32.1976142,76.3237065))
        .add(new LatLng(22.564613,88.3410758))
        .add(new LatLng(12.9790072,77.5972145)));

        CircleOptions circa = new CircleOptions()
                .center(new LatLng(32.1976142,76.3237065))
                .radius(50000)
                .fillColor(Color.YELLOW)
                .strokeColor(Color.CYAN)
                .strokeWidth(20);
        mMap.addCircle(circa);
    }

    private void addStadiumMarkers()
    {
        mMap.addMarker(wankhede);
        mMap.addMarker(eden);
        mMap.addMarker(PCA);
        mMap.addMarker(MCA);
        mMap.addMarker(dharam);
        mMap.addMarker(holkar);
        mMap.addMarker(saurashtra);
        mMap.addMarker(chinna);
        mMap.addMarker(rajiv);
        mMap.addMarker(ferozshah);
        mMap.addMarker(greenpark);

    }


    private void updateCurrentLoc(Location lastUnkownLocation) {
        LatLng currentLocation = new LatLng(lastUnkownLocation.getLatitude(), lastUnkownLocation.getLongitude());

//        String nameOfPlace = getAddressOnMarker(lastUnkownLocation);

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(currentLocation)); //.title("Your Current Location")
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 5));
    }

    private Location fetchBestLocation() {
        Location locationGPS = null;
        Location locationNetwork = null;

        // get both but return more accurate of GPS & network location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationGPS = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            locationNetwork = locMgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (locationGPS == null && locationNetwork == null)
            return null;
        else
        if (locationGPS == null)
            return locationNetwork;
        else
        if (locationNetwork == null)
            return locationGPS;
        else
            return (locationGPS.getAccuracy() < locationNetwork.getAccuracy() ? locationGPS : locationNetwork);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }
    }

    private void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListner);
        }
        Location lastUnkownLocation = fetchBestLocation();
        if (lastUnkownLocation != null) {
            updateCurrentLoc(lastUnkownLocation);
        }
    }

}
