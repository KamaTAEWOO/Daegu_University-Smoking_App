package com.example.zszs1.ypumap00002;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class Map4 extends AppCompatActivity implements OnMapReadyCallback,LocationListener,PlacesListener {
    //implements 순서대로 지도불러오고,현재위치 찍어주고,주변장소 찾아줌
    LatLng currentPosition;//현재좌표값
    List<Marker> prevMarkers;//마커리스트
    GoogleMap map;//맵객체
    double latitude;//위도
    double longitude;//경도
    LocationListener locationListener;
    MapFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map4);

        prevMarkers=new ArrayList<>();
        fragment=(MapFragment)getFragmentManager().findFragmentById(R.id.fragment1);
        fragment.getMapAsync(this);

        //검색버튼 클릭
        Button btnSearch =(Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener (new View.OnClickListener () {
            public void onClick (View v) {
                prepareMap();
                drawMap();
                showPlaceInformation(currentPosition);//현재위치 전달
            }
        });
    }


    void showPlaceInformation(LatLng location) {
        map.clear();//지도 지우기
        if(prevMarkers !=null){
            prevMarkers.clear();//지역정보 마커 클리어
        }

        new NRPlaces.Builder()
                .listener(this)//장소검색 리스너를 구현한 객체
                .key("AIzaSyBnpbMr6pwBjzTa6GedIeioflp_bbK1w0Q")//키값
                .latlng(latitude,longitude)//현재 위치 기준
                .radius(500)//500미터 이내 검색
                .type(PlaceType.BUS_STATION) //버스정류장
                .build()
                .execute();
        //서버에서 결과가 도착하면 onPlacesSuccess()가 호출됨

    }
//위치가 변경되면
    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        currentPosition=new LatLng(latitude,longitude);//새로운 현재좌표
        drawMap();//지도출력
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
    //맵이로딩이되면
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map=googleMap;
        prepareMap();
        drawMap();
    }

//권한체크
    private void prepareMap() {
        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (check != PackageManager.PERMISSION_GRANTED) {
//사용자가 체크를하면 onrequestPermissionsResult 메소드로 넘어감
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL); //지도종류:보통지도
            map.setMyLocationEnabled(true); //현재위치 표시 옵션
            map.getUiSettings().setZoomControlsEnabled(true); //줌커트롤 표시여부

            //위치정보 관리자 객체
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //리스너 등록
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            //수동으로 위치 구하기
            String locationProvider=LocationManager.GPS_PROVIDER;
            Location lastKnownLocation=locationManager.getLastKnownLocation(locationProvider);
            //최근 gps좌표를 저장
            if(lastKnownLocation != null){
                latitude=lastKnownLocation.getLatitude();
                longitude=lastKnownLocation.getLongitude();
            }else{
                latitude=37.5796212;
                longitude=126.9748523;
            }
            currentPosition =new LatLng(latitude,longitude);//현재좌표를 준비한다
            Log.i("text","currentPosition:" + currentPosition);
        }
    }

    void drawMap() {
        map.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition,18));
        MarkerOptions marker=new MarkerOptions();
        marker.position(currentPosition);
        marker.title("현재위치");
        map.addMarker(marker);
    }

    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }
//서버에서 장소 검색 결과가 도착할 때 호출
    @Override
    public void onPlacesSuccess(final List<Place> places) {
//메인화면을 수정해야 하므로 runOnUiThread()에서 처리
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(Place place : places){
                    LatLng latLng=new LatLng(place.getLatitude(),place.getLongitude());//좌표값들어있음
                    MarkerOptions markerOptions=new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.snippet(place.getVicinity());
                    Marker item=map.addMarker(markerOptions);
                    prevMarkers.add(item);//마커만들어서 셋팅
                }

                //중복된값 제거하기 위해 hashset사용

                HashSet<Marker>  hashSet=new HashSet<Marker>();
                hashSet.addAll(prevMarkers);
                prevMarkers.clear();
                prevMarkers.addAll(hashSet);
            }
        });
    }

    @Override
    public void onPlacesFinished() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[],int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length>0)&&(grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    prepareMap();
                    drawMap();
                }//권한을 허가받았으면 맵을 그리세요
                break;

                default:
                    break;
        }
    }
}
