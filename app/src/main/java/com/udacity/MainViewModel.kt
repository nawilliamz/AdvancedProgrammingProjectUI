package com.udacity

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.udacity.Util.SingleLiveEvent

class MainViewModel() : ViewModel() {

//    val _events = SingleLiveEvent<Events>()
//    val events = _events as LiveData<Events>



    fun resetDownloadManager(downloadManager: DownloadManager) {

        //Clear outstanding downloads from the DownloadManager
        downloadManager.remove()
    }

}