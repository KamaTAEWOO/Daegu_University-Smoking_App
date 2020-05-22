package com.example.zszs1.ypumap00002;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

public class Map1 extends AppCompatActivity implements OnMapReadyCallback {

    MapFragment fragment;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map1);

        //프레그먼트 객체 연결
        fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment1);
//프레그먼트에서 맵을 리턴받음
        // GoogleMap map=fragment.getMap();
//비동기적인 방식(병렬처리)으로 구글맵을 생성함(시간이 너무 오래걸려서 사용함)
        fragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        drawMap();

        //마커를 눌렀을때 페이지 전환
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getTitle().equals("인문대1호관")){
                    Intent intent=new Intent(getApplicationContext(),mk1.class);
                    startActivity(intent);
                }else if(marker.getTitle().equals("교수학습지원관")){
                    Intent intent=new Intent(getApplicationContext(),mk2.class);
                    startActivity(intent);
                }else if(marker.getTitle().equals("복지관(동편)")){
                    Intent intent=new Intent(getApplicationContext(),mk3.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("경상대(1)")){
                    Intent intent=new Intent(getApplicationContext(),mk4.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("경상대(2)")){
                    Intent intent=new Intent(getApplicationContext(),mk5.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("재활과학대1호관(1")){
                    Intent intent=new Intent(getApplicationContext(),mk6.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("재활과학대 1호관(2)")){
                    Intent intent=new Intent(getApplicationContext(),mk7.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("공과대 2호관")){
                    Intent intent=new Intent(getApplicationContext(),mk8.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("정보통신대 1호관")){
                    Intent intent=new Intent(getApplicationContext(),mk9.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("정보통신대 2호관(1)")){
                    Intent intent=new Intent(getApplicationContext(),mk10.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("정보통신대 2호관(2)")){
                    Intent intent=new Intent(getApplicationContext(),mk11.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("정보통신대 2호관(3)")){
                    Intent intent=new Intent(getApplicationContext(),mk12.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("공과대 본부동")){
                    Intent intent=new Intent(getApplicationContext(),mk13.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("공과대 1호관")){
                    Intent intent=new Intent(getApplicationContext(),mk14.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("서문")){
                    Intent intent=new Intent(getApplicationContext(),mk15.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("과학생명융합대(생명관)")){
                    Intent intent=new Intent(getApplicationContext(),mk16.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("과학생명융합대(과학관)")){
                    Intent intent=new Intent(getApplicationContext(),mk17.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("조형예술대 5호관")){
                    Intent intent=new Intent(getApplicationContext(),mk18.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("사범대 1호관")){
                    Intent intent=new Intent(getApplicationContext(),mk19.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("중앙도서관")){
                    Intent intent=new Intent(getApplicationContext(),mk20.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("평생교육관")){
                    Intent intent=new Intent(getApplicationContext(),mk21.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("DU바이크센터")){
                    Intent intent=new Intent(getApplicationContext(),mk22.class);
                    startActivity(intent);
                }else if(marker.getTitle().equals("조형예술대 1호관")){
                    Intent intent=new Intent(getApplicationContext(),mk23.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("조형예술대 2호관")){
                    Intent intent=new Intent(getApplicationContext(),mk24.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("종합강의동")){
                    Intent intent=new Intent(getApplicationContext(),mk25.class);
                    startActivity(intent);
                }
                else if(marker.getTitle().equals("종합연구동")){
                    Intent intent=new Intent(getApplicationContext(),mk26.class);
                    startActivity(intent);
                }



            }

        });

    }
    //맵그리기
    void drawMap() {
//권한 부터 물어보기
        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (check != PackageManager.PERMISSION_GRANTED) {
//사용자가 체크를하면 onrequestPermissionsResult 메소드로 넘어감
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL); //지도종류:보통지도
            map.setMyLocationEnabled(true); //현재위치 표시 옵션
            map.getUiSettings().setZoomControlsEnabled(true); //줌커트롤 표시여부
            LatLng geoPoint = new LatLng(35.898044, 128.850216); //좌표지정
            map.moveCamera(CameraUpdateFactory.newLatLng(geoPoint));  //지도를 바라보는 카메라 이동
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(geoPoint, 16));  //줌레벨

            //맵에 마커 표시하기
            MarkerOptions marker = new MarkerOptions();
            marker.position(geoPoint);//마커를 표시할 좌표
            marker.title("인문대1호관");//마커의 제목
            map.addMarker(marker); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker2 = new MarkerOptions();
            marker2.position(new LatLng(35.900004, 128.850416));//마커를 표시할 좌표
            marker2.title("교수학습지원관");//마커의 제목
            map.addMarker(marker2); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker3=new MarkerOptions();
            marker3.position(new LatLng(35.899415,128.853014));//마커를 표시할 좌표
            marker3.title("복지관(동편)");//마커의 제목
            map.addMarker(marker3); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker4=new MarkerOptions();
            marker4.position(new LatLng(35.901119,128.850854));//마커를 표시할 좌표
            marker4.title("경상대(1)");//마커의 제목
            map.addMarker(marker4); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker5=new MarkerOptions();
            marker5.position(new LatLng(35.900450,128.851146));//마커를 표시할 좌표
            marker5.title("경상대(2)");//마커의 제목
            map.addMarker(marker5); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker6=new MarkerOptions();
            marker6.position(new LatLng(35.899882,128.853394));//마커를 표시할 좌표
            marker6.title("재활과학대1호관(1)");//마커의 제목
            map.addMarker(marker6); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker7=new MarkerOptions();
            marker7.position(new LatLng(35.900001,128.852973));//마커를 표시할 좌표
            marker7.title("재활과학대 1호관(2)");//마커의 제목
            map.addMarker(marker7); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker8=new MarkerOptions();
            marker8.position(new LatLng(35.898961,128.854051));//마커를 표시할 좌표
            marker8.title("공과대 2호관");//마커의 제목
            map.addMarker(marker8); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker9=new MarkerOptions();
            marker9.position(new LatLng(35.898885,128.854959));//마커를 표시할 좌표
            marker9.title("정보통신대 1호관");//마커의 제목
            map.addMarker(marker9); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker10=new MarkerOptions();
            marker10.position(new LatLng(35.900378,128.854160));//마커를 표시할 좌표
            marker10.title("정보통신대 2호관(1)");//마커의 제목
            map.addMarker(marker10); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker11=new MarkerOptions();
            marker11.position(new LatLng(35.900343,128.854697));//마커를 표시할 좌표
            marker11.title("정보통신대 2호관(2)");//마커의 제목
            map.addMarker(marker11); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker12=new MarkerOptions();
            marker12.position(new LatLng(35.900093,128.854879));//마커를 표시할 좌표
            marker12.title("정보통신대 2호관(3)");//마커의 제목
            map.addMarker(marker12); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker13=new MarkerOptions();
            marker13.position(new LatLng(35.900093,128.854879));//마커를 표시할 좌표
            marker13.title("공과대 본부동");//마커의 제목
            map.addMarker(marker13); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker14=new MarkerOptions();
            marker14.position(new LatLng(35.898961,128.854051));//마커를 표시할 좌표
            marker14.title("공과대 1호관");//마커의 제목
            map.addMarker(marker14); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker15=new MarkerOptions();
            marker15.position(new LatLng(35.898818,128.844808));//마커를 표시할 좌표
            marker15.title("서문");//마커의 제목
            map.addMarker(marker15); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker16=new MarkerOptions();
            marker16.position(new LatLng(35.898949,128.847762));//마커를 표시할 좌표
            marker16.title("과학생명융합대(생명관)");//마커의 제목
            map.addMarker(marker16); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker17=new MarkerOptions();
            marker17.position(new LatLng(35.900493,128.848918));//마커를 표시할 좌표
            marker17.title("과학생명융합대(과학관)");//마커의 제목
            map.addMarker(marker17); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker18=new MarkerOptions();
            marker18.position(new LatLng(35.899225,128.846863));//마커를 표시할 좌표
            marker18.title("조형예술대 5호관");//마커의 제목
            map.addMarker(marker18); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker19=new MarkerOptions();
            marker19.position(new LatLng(35.900372,128.847078));//마커를 표시할 좌표
            marker19.title("사범대 1호관");//마커의 제목
            map.addMarker(marker19); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker20=new MarkerOptions();
            marker20.position(new LatLng(35.901815,128.849962));//마커를 표시할 좌표
            marker20.title("중앙도서관");//마커의 제목
            map.addMarker(marker20); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker21=new MarkerOptions();
            marker21.position(new LatLng(35.902515,128.849159));//마커를 표시할 좌표
            marker21.title("평생교육관");//마커의 제목
            map.addMarker(marker21); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker22=new MarkerOptions();
            marker22.position(new LatLng(35.901802,128.844576));//마커를 표시할 좌표
            marker22.title("DU바이크센터");//마커의 제목
            map.addMarker(marker22); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker23=new MarkerOptions();
            marker23.position(new LatLng(35.902379,128.845181));//마커를 표시할 좌표
            marker23.title("조형예술대 1호관");//마커의 제목
            map.addMarker(marker23); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker24=new MarkerOptions();
            marker24.position(new LatLng(35.902617,128.846112));//마커를 표시할 좌표
            marker24.title("조형예술대 2호관");//마커의 제목
            map.addMarker(marker24); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker25=new MarkerOptions();
            marker25.position(new LatLng(35.901867,128.843771));//마커를 표시할 좌표
            marker25.title("종합강의동");//마커의 제목
            map.addMarker(marker25); //지도에 마커추가(여러개 해야함!!)

            MarkerOptions marker26=new MarkerOptions();
            marker26.position(new LatLng(35.902476,128.843078));//마커를 표시할 좌표
            marker26.title("종합연구동");//마커의 제목
            map.addMarker(marker26); //지도에 마커추가(여러개 해야함!!)

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    drawMap();
                }
                break;

            default:
                break;
        }
//허용을했으면 지도를 그리고 그렇지 않으면 그냥끝내라
    }

}
