package com.udacity.Util

import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener

var loadingFile:Loading = Loading.NONE

class ClickListenerOuter(var glide: Int, var udacity: Int, var retrofit: Int) :
    RadioGroup.OnCheckedChangeListener {

    //checkedId is the Integer id of the button that has been checked.
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

        when (checkedId) {
            glide -> {
                loadingFile = Loading.GLIDE
            }
            udacity -> {
                loadingFile = Loading.UDACITY
            }
            retrofit -> {
                loadingFile = Loading.RETROFIT
            }
            else -> {
                loadingFile = Loading.NONE
            }
        }

        //still need to include an else equivalent to handle case when none of the buttons are
        //clicked
    }


}