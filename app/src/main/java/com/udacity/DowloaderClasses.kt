package com.udacity

import android.app.Activity
import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Environment
import androidx.core.content.res.TypedArrayUtils.getString
import kotlinx.coroutines.withContext


class GlideDownloader(context: Context) : Downloader {


    val downloadManager = context.getSystemService(DownloadManager::class.java)

    companion object {

         const val URL = "https://github.com/bumptech/glide"
        private const val CHANNEL_ID = "glideId"
    }

    override fun downloadFile(url: String): Long {

        val request = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setTitle("glide-master.zip")
            .setDescription(R.string.app_description.toString())
            .setRequiresCharging(false)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "glide-master.zip")

        return downloadManager.enqueue(request)
    }


}

class UdacityDownloader(context: Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    companion object {

        const val URL = "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter"
        private const val CHANNEL_ID = "udacityId"
    }

    override fun downloadFile(url: String): Long {

        val request = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setTitle("nd940-c3-advanced-android-programming-project-starter-master.zip")
            .setDescription(R.string.app_description.toString())
            .setRequiresCharging(false)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "nd940-c3-advanced-android-programming-project-starter-master.zip")

        return downloadManager.enqueue(request)
    }

}

class RetrofitDownloader(context: Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    companion object {

        const val URL = "https://github.com/square/retrofit"
        private const val CHANNEL_ID = "retrofitId"
    }

    override fun downloadFile(url: String): Long {

        val request = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setTitle("retrofit-master.zip")
            .setDescription(R.string.app_description.toString())
            .setRequiresCharging(false)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "retrofit-master.zip")

        return downloadManager.enqueue(request)
    }

}