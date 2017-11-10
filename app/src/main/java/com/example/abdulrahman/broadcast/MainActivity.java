package com.example.abdulrahman.broadcast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check();
    }

    final int q=1;
    public void check(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.RECEIVE_SMS
            },q);
        }
       // rec();
    }

    public void buSent(View view) {
      /*  Intent intent=new Intent();
        intent.setAction("Test");
        intent.putExtra("msg","Hell21o");
       sendBroadcast(intent);
       */

    }

    public void rec(){
        Intent intent=new Intent();
        intent.setAction("android.provider.Telephony.SMS_RECEIVED");
        intent.putExtra("msg","Hell21o");
        sendBroadcast(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case q:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    rec();
                }
                break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
