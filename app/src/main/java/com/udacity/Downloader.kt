package com.udacity

interface Downloader {

    //The long returned is the ID of the downloaded file
    fun downloadFile (url:String): Long

}