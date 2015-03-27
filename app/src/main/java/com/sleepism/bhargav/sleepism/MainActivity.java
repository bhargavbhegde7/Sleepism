package com.sleepism.bhargav.sleepism;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {

    AudioManager am;
    Context context = this;
    ComponentName receiver;
    PackageManager pm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);//get the audio manager
        receiver = new ComponentName(context, IncomingCall.class);//register the broadcast receiver
        pm = context.getPackageManager();//this is to enable or disable the broadcast receiver
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);//keep it disabled by default. that is before switching it on

        SharedPreferences prefs = context.getSharedPreferences(
                "com.sleepism.bhargav.sleepism", Context.MODE_PRIVATE);

        //clear all the numbers
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void toggleSilence(View view)
    {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            System.out.println("on");
            //For Silent mode
            am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);//enable broadcast receiver on switch on
        } else {
            System.out.println("off");
            //For Normal mode
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);//disable broadcast receiver on switch on
        }

        //For Vibrate mode
        //am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
