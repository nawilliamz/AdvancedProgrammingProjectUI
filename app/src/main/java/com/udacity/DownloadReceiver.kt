package com.udacity

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DownloadReceiver() : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {


        if (intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {

            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)

            if (id != -1L) {

                //Here is where you will put code for what happens after the download has been completed (trigger the notification). Or you
                //just change the global events variable to EVENTS.DONE and, since you're observing it in MainActivity, you can do all of
                //the things that need to happen with the download is done over there.
                Log.i("DownloadReceiver","Download with ID $id finished!")


            }
        }
    }
}