package com.example.timertask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MyBroadcastReceiver extends BroadcastReceiver {

    MediaPlayer mp;
    Context context;
    AlertDialog.Builder builder;
    @Override
    public void onReceive(Context context, Intent intent) {
     /*   mp=MediaPlayer.create(context, R.raw.alarm_rooster);
        mp.start();*/
        Toast.makeText(context, "Your Task Time Is Over", Toast.LENGTH_SHORT).show();
    }

    /*private void pop() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Message");
        dialogBuilder.setMessage("Your Task Is Complete");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK", null);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }*/


}


