package com.udacity

import android.animation.ValueAnimator
import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.isGone
import com.udacity.Util.*
import com.udacity.databinding.ActivityMainBinding
import kotlinx.coroutines.*


//lateinit var loadingStatus:Events
lateinit var loadingStatus:LoadingStatus
lateinit var resultStatus:ResultStatus


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    private lateinit var fileName:String
    private lateinit var fileDownloadStatus:String

    private var context: Context = this
    private lateinit var valueAnimator: ValueAnimator

    init {
        loadingStatus = LoadingStatus.LOADING
        resultStatus = ResultStatus.NEUTRAL

    }


    //***********************Couroutines_NoFileSelected***********************************
//    private var animationProcessingJob_NoFileSelected = Job()
//
//    private val animationProcessingScope_NoFileSelected = CoroutineScope(Dispatchers.Main + animationProcessingJob_NoFileSelected)


    private fun processAnimation_NoFileSelected(){

            Log.i("MainActivity", "processAnimation_NoFileSelected is running.")

            //Set the color of DOWNLOAD text to same as background color
            binding.downloadButton.textPaint.color = binding.downloadButton.buttonPrimaryColor
            binding.downloadButton.invalidate()

            val leftPosition = binding.downloadButton.x
            val rightPosition = binding.downloadButton.x + binding.downloadButton.width

            binding.animatedDownloadButton.showAnimatedDownloadButton(leftPosition, rightPosition)

            binding.selectFileButton.showSelectFileButton()

//            delay(5000)
            binding.animatedDownloadButton.isGone = true
            binding.selectFileButton.isGone = true

            //Re-set the color of DOWNLOAD text to white
            binding.downloadButton.textPaint.color = Color.WHITE
            binding.downloadButton.invalidate()

    }
    //*************************************************************************************

    //******************************Coroutines_Glide*****************************************

//    private var animationProcessingJob_GlideSelected = Job()
//
//    private val animationProcessingScope_GlideSelected = CoroutineScope(Dispatchers.Main + animationProcessingJob_GlideSelected)

    private fun processAnimation_Glide() {

            //Set the color of DOWNLOAD text to same as background color
            binding.downloadButton.textPaint.color = binding.downloadButton.buttonPrimaryColor
            binding.downloadButton.invalidate()
            Log.i("MainActivity", "DOWNLOAD text changed to buttonPrimaryColor")

            val leftPosition = binding.downloadButton.x
            val rightPosition = binding.downloadButton.x + binding.downloadButton.width

            binding.animatedDownloadButton.showAnimatedDownloadButton(leftPosition, rightPosition)

            binding.progressCircle.showAnimatedCircle()

//            Start download with code by referencing DownloadClass
            GlideDownloader.downloadFile(Constants.GLIDE_URL, context)


            if (loadingStatus == LoadingStatus.DONE) {

                //Stop animation of customer views
                binding.animatedDownloadButton.cancelAnimators()
                binding.progressCircle.cancelAnimatedCircle()
                Log.i("MainActivity", "DownloadButton & ProgressCircle animates are cancelled")


                //Make custom view disappear
                binding.progressCircle.isGone = true
                binding.animatedDownloadButton.isGone = true

                //Re-set the color of DOWNLOAD text to white
                binding.downloadButton.textPaint.color = Color.WHITE
                binding.downloadButton.invalidate()


                //*****************Navigation to DetailsActivity**************************
                //Trigger navigation to DetailsActivity when download complete using Intent
                //Do you need to place this code inside the CoroutineScope? I do think so insofar as I
                //need to stop this corouting from processing.

                fileName = Constants.GLIDE_FILE_NAME
                if (resultStatus == ResultStatus.SUCCESS) {
                    fileDownloadStatus = "Success"
                } else {
                    fileDownloadStatus = "Fail"
                }

                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra("FILENAME", fileName)
                    it.putExtra("STATUS", fileDownloadStatus)

                    startActivity(it)
                }
                //******************************************************************************


                //Reset loadingStatus & fileDownloadStatus progress indicators
                loadingStatus = LoadingStatus.LOADING
                resultStatus = ResultStatus.NEUTRAL

                //Stop the coroutine from processing
                //I don't think any need to do this as it is being cancelled in onDestroy()??
            }




    }



    //**************************************************************************************




    override fun onDestroy() {
        super.onDestroy()

//        animationProcessingJob_NoFileSelected.cancel()

    }


    //**************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        setOnCheckedListenerToRadioGroup(binding.radioGroup)

//        val DM2 = MultiDownloads(this).downloadFile(MultiDownloads.glideURL)


//        binding.animatedDownloadButton.isGone = true
        binding.selectFileButton.isGone = true

//        binding.animatedDownloadButton.isGone = true

        //setOnDownloadClickListener runs the lambda passed into it
        binding.downloadButton.setOnDownloadClickListener {
            //put what happens when downloadButton is clicked


            when (loadingFile) {
                Loading.GLIDE -> {

                    processAnimation_Glide()
                }
                Loading.UDACITY -> {


                }
                Loading.RETROFIT -> {

                }
                else -> {
                    //Make select_downloaod_button visible, animate it, and
                    // make "Select file to download" custom view visible so it
                    //is overlaid on top of select_download_button
                    //This should only last for a say 5 seconds before animation stops
                    // and customer view becomes invisible


                    processAnimation_NoFileSelected()


                }
            }
        }
    }


    private fun setOnCheckedListenerToRadioGroup (group: RadioGroup) {



        val glide = binding.glideButton.id
        val udacity = binding.udacityButton.id
        val retrofit = binding.retrofitButton.id

        val checkedId = -1

        val listener = ClickListenerOuter(glide, udacity, retrofit)

        //Sets the checked change listener for the group
        group.setOnCheckedChangeListener (listener)

        //calls the onCheckedChanged method for the listener
        listener.onCheckedChanged(group, checkedId)

    }


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }


//    private fun animateSelectFileButton () {
//
//        val animatedWidth = binding.animatedDownloadButton.width
//
//        val animator = ValueAnimator.ofInt(0, animatedWidth)
//        animator.duration = 5000
//
//        animator.addUpdateListener { valueAnimator  ->
//            val animatedValue = valueAnimator.animatedValue as Int
//
//            binding.animatedDownloadButton.right = animatedValue
//        }
//
//
//        animator.start()
//
//    }

//    private fun animateButton () {
//
//        val initialSize = 0
//        val finalSize = binding.animatedDownloadButton.measuredWidth
//
//        val sizeAnimator = ValueAnimator.ofInt(initialSize, finalSize)
//        sizeAnimator.duration = 5000
//
//        sizeAnimator.addUpdateListener {
//            binding.animatedDownloadButton.updateLayoutParams {
//                binding.animatedDownloadButton.width = it.animatedValue as Int
//            }
//        }
//
//    }





}