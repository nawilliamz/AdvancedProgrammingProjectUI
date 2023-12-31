package com.udacity

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.udacity.Util.ResultStatus

import com.udacity.Util.LoadingStatus
import com.udacity.databinding.ActivityMainBinding

class DownloadReceiver() : BroadcastReceiver() {

    private lateinit var binding: ActivityMainBinding



    override fun onReceive(context: Context?, intent: Intent?) {


        //Is there a means in DownloadManager API for determining which URL a given download is
        //associated with? So, we can match that URL with the URL where the most recent download
        //request was sent.
        //Yes????, you can customer the intent action for each download???? So make a separate receiver for
        //each download file type (i.e. android.intent.action.GLIDE_DOWNLOAD_COMPLETE...). You will
        //need to register each of these actions in the Manifest.
        //Looks like you can use the constant COLUMN_URI to catch the URI target of the download. This is
        //accessed as DownloadManager.COLUMN_URI

        if (intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {

            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)


            if (id != -1L) {

                //Here is where you will put code for what happens after the download has been completed (trigger the notification). Or you
                //just change the global events variable to EVENTS.DONE and, since you're observing it in MainActivity, you can do all of
                //the things that need to happen with the download is done over there.
                //1. Trigger notification,
                //2. Trigger navigation to Details screen where the FileName and Status text fields are updated to reflect the name of
                //the file downloaded (equal to the 3 files listed on the radio buttons) and Status field should reflect "Success"
                //**Just use intents to do this navigation and putExtra to communicate the info to Details screen
                //Cancel the animation by cancelling the animators previously started (animator.cancel())
                Log.i("DownloadReceiver","Download with ID $id finished!")




                //Step2: Trigger notification


                //Set download progress indicator so MainActivity knows whether downlaod was SUCCESS or FAIL
                resultStatus = ResultStatus.SUCCESS
                Log.i("DownloadReceiver", "downloadStatus variable value is $resultStatus")

                //Step3: Navigate from MainActivity to Details screen with fileName String and success/failure String
                //May need to use global variable in Events file to do this from MainActivity.By setting loadingStatus
                //to Done, I will trigger the intent to navigate from MainActivity to DetailActivity in MainActivity in
                //processAnimation_Glide()
                loadingStatus = LoadingStatus.DONE
                Log.i("DownloadReceiver", "loadingStatus variable value is $loadingStatus")

            } else {

                //Here is where you will put code for what happens if a valid file was not downloaded. Will need to updated
                //Details screen with the name of the file that was attempted to download and fill this in toe FileName field and then
                //Fill in Status field with "Fail"


                //Step2: Trigger notification



                //Set download progress indicator so MainActivity knows whether downlaod was SUCCESS or FAIL
                resultStatus = ResultStatus.FAIL


                //Step3: Navigate from MainActivity to Details screen with fileName String and success/failure String
                //May need to use global variable in Events file to do this from MainActivity.By setting loadingStatus
                //to Done, I will trigger the intent to navigate from MainActivity to DetailActivity in MainActivity in
                //processAnimation_Glide()
                loadingStatus = LoadingStatus.DONE

            }
        } else {

            Log.i("DownloadReceiver", "Intent action not showing DOWNLOAD_COMPLETE")

            //**Same code here as in else statement above. Both present case where download not completed successfully.
            //Cancel all animations
//            binding.animatedDownloadButton.cancelAnimators()
//            binding.progressCircle.cancelAnimatedCircle()

            //Step2: Trigger notification

            //Set download progress indicator so MainActivity knows whether downlaod was SUCCESS or FAIL
            resultStatus = ResultStatus.FAIL


            //Step3: Navigate from MainActivity to Details screen with fileName String and success/failure String
            //May need to use global variable in Events file to do this from MainActivity.By setting loadingStatus
            //to Done, I will trigger the intent to navigate from MainActivity to DetailActivity in MainActivity in
            //processAnimation_Glide()
            loadingStatus = LoadingStatus.DONE
        }
    }
}