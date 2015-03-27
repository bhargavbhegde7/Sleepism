package com.sleepism.bhargav.sleepism;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;

import java.util.Map;

/**
 * Created by bhargav on 3/25/2015.
 */
public class IncomingCall extends BroadcastReceiver {

    public static int count = 1;
    AudioManager am;

    @Override
    public void onReceive(Context context, Intent intent) {

        am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);//get the audio manager

        SharedPreferences prefs = context.getSharedPreferences(
                "com.sleepism.bhargav.sleepism", Context.MODE_PRIVATE);



        if(count == 1)
        {
            //set it to silent mode when ever a call comes, before checking if it is the second time
            //For Silent mode
            am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

            if(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) != null)
            {
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                System.out.println(" number is :  " + incomingNumber);
                Map<String,?> keys =  prefs.getAll();
                System.out.println("The number is : " + keys.get(incomingNumber));
                if(keys.get(incomingNumber) == null)//number is not found in the list
                {
                    //add the number to the list
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(incomingNumber, incomingNumber);
                    editor.commit();
                }
                else//number is already there
                {
                    //enable the ringer
                    //For Normal mode
                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
            }

            count++;
        }//if count is 1
        else
        {
            count =1;
        }

    }//onReceive ends here
}
