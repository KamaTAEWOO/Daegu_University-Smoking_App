package com.example.zszs1.ypumap00002;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick (View v){
        Intent intent =null;
        switch (v.getId()){
            case R.id.button1:
                intent=new Intent(this,Map1.class);
                break;
            case R.id.button4:
                intent=new Intent(this,Map4.class);
                break;
        }
        startActivity(intent);
    }
}
