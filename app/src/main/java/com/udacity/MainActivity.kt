package com.udacity

import android.animation.ValueAnimator
import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.udacity.Util.ClickListenerOuter
import com.udacity.Util.Constants
import com.udacity.Util.Loading
import com.udacity.Util.loadingFile
import com.udacity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action


    private lateinit var valueAnimator: ValueAnimator



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


        setOnCheckedListenerToRadioGroup(binding.radioGroup)

//        val DM2 = MultiDownloads(this).downloadFile(MultiDownloads.glideURL)


//        binding.selectDownloadButton.isGone = true
        binding.selectFileButton.isGone = true

//        binding.selectFileButton.setBackgroundResource(R.drawable.rectangle_rounded_corners)

        binding.downloadButton.setOnDownloadClickListener {
            //put what happens when downloadButton is clicked

            when (loadingFile) {
                Loading.GLIDE -> {

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

                    animateSelectFileButton()

//                    binding.selectDownloadButton.isVisible = true

                }
            }
        }
    }


    private fun setOnCheckedListenerToRadioGroup (group: RadioGroup) {


        val glide = binding.glideButton.id
        val udacity = binding.udacityButton.id
        val retrofit = binding.retrofitButton.id

        val checkedId = 0

        val listener = ClickListenerOuter(glide, udacity, retrofit)
        listener.onCheckedChanged(group, checkedId)

    }


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }


    private fun animateSelectFileButton () {



        val animatedWidth = binding.animatedDownloadButton.width

        val animator = ValueAnimator.ofInt(0, animatedWidth)
        animator.duration = 5000

        animator.addUpdateListener { valueAnimator  ->
            val animatedValue = valueAnimator.animatedValue as Int

//            binding.selectDownloadButton.right = animatedValue
        }


        animator.start()

    }

//    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
//
//        val glideId = binding.glideButton.id
//        val udacityId = binding.udacityButton.id
//        val retrofitId = binding.retrofitButton.id
//
//        when (checkedId) {
//            glideId -> {}
//            udacityId -> {}
//            retrofitId -> {}
//        }

//    }





}