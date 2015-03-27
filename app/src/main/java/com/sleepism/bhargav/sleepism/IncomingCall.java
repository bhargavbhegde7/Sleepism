package com.sleepism.bhargav.sleepism;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * Created by bhargav on 3/25/2015.
 */
public class IncomingCall extends BroadcastReceiver {

    public static int count = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(count == 1)
        {
            /*Bundle bundle = intent.getExtras();

            if (null == bundle)
            {
                System.out.println("bundle is null");
                return;
            }

            String state = bundle.getString(TelephonyManager.EXTRA_STATE);

            if (TelephonyManager.EXTRA_STATE_RINGING.equalsIgnoreCase(state))//ringing
            {
                System.out.println("state is ringing");

                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                System.out.println(" number is :  "+incomingNumber);
                return;
            }


            if (TelephonyManager.EXTRA_STATE_IDLE.equalsIgnoreCase(state))//hung up
            {
                System.out.println("state is idle");
                return;
            }
            if (TelephonyManager.EXTRA_STATE_OFFHOOK.equalsIgnoreCase(state))//received the call
            {
                System.out.println("state is off hook");
                return;
            }*/

            if(intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) != null)
            {
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                System.out.println(" number is :  " + incomingNumber);
            }

            count++;
        }//if count is 1
        else
        {
            count =1;
        }

    }//onReceive ends here
}
