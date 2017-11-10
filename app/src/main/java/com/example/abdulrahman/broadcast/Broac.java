package com.example.abdulrahman.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Abdulrahman on 09/11/17.
 */

public class Broac extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b=intent.getExtras();
   if (intent.getAction()=="Test"){
       String msg=intent.getExtras().getString("msg");
       Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
   }

   if (intent.getAction()=="android.provider.Telephony.SMS_RECEIVED"){
      //

       if (b!=null){
           Object object[]=(Object[]) b.get("pdus");
           SmsMessage[] smsMessage=new SmsMessage[object.length];

           for(int i=0;i<smsMessage.length;i++){
              if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                  String txt=b.getString("format");
                  smsMessage[i]=SmsMessage.createFromPdu((byte[])object[i],txt);
           }
           else{
                  smsMessage[i]=SmsMessage.createFromPdu((byte[])object[i]);
              }
              String numP=smsMessage[i].getOriginatingAddress();
              String msg=smsMessage[i].getMessageBody();
               Toast.makeText(context,numP+" : "+msg,Toast.LENGTH_LONG).show();
           }
       }
   }
    }
}
