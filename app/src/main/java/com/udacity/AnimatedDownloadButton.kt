package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.renderscript.Sampler.Value
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.view.ContentInfoCompat.Flags
import androidx.core.view.isGone
import androidx.core.view.updateLayoutParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AnimatedDownloadButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var width = 0F
    private var height = 0F

    private var textWidth = 0F
    private var textHeight = 0F

    private var top = 0f
    private var bottom = 0f
    private var left = 0f
    private var right = 0f

    private var buttonPrimaryColor = 0
    private var buttonDownloadColor = 0

    val rect = RectF()




    val selectDownloadPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
//        textAlign = Paint.Align.CENTER
//        textSize = 66.0f
//        typeface = Typeface.create( "", Typeface.BOLD)
    }

    init {

        //Remember: This code is defining a custom attribute for the View called buttonPrimaryColor and
        //button DownloadColor. The actual color is defined in the XML file.
        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            buttonPrimaryColor = getColor(R.styleable.LoadingButton_primaryButtonColor, 0)
            buttonDownloadColor = getColor(R.styleable.LoadingButton_downloadButtonColor, 0)

        }
    }

    suspend fun showAnimatedDownloadButton (downloadButtonX: Float, downloadButtonRight:Float) {
        this.isGone = false

        animateWidth()
//        translateAnimatedDownloadButton(downloadButtonX, downloadButtonRight)
        animateRightPosition(downloadButtonX, downloadButtonRight)


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)


        //Using to set text width & height
        width = w.toFloat()
        height = h.toFloat()



        top = 0F
        bottom = top + height
//        bottom = 0F
        left = 0F
        right = left + width
//        right = 0F

        textWidth = width/2
        textHeight = height/2

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        selectDownloadPaint.color = buttonDownloadColor
        canvas?.drawRect(left, top, right, bottom, selectDownloadPaint)

    }

    private fun animateWidth () {

        val initialSize = 0
        val finalSize = this.width.toInt()

        val sizeAnimator = ValueAnimator.ofInt(initialSize, finalSize)
        sizeAnimator.duration = 5000

        sizeAnimator.addUpdateListener {
            this.updateLayoutParams {
                this.width = it.animatedValue as Int

            }
        }

        sizeAnimator.start()
    }

    private fun animateRightPosition (downloadButtonX: Float, downloadButtonRight:Float) {

        //this.width will vary because of other animation (see above). Need to get the initial value
        var rightEdge = this.x + this.width

        val initialPosition = downloadButtonX
        val finalPosition = downloadButtonX

        val positionAnimator = ValueAnimator.ofFloat(initialPosition, finalPosition)
        positionAnimator.duration = 5000

        positionAnimator.addUpdateListener {

//            rightEdge = it.animatedValue as Float
            this.x = it.animatedValue as Float

        }

        positionAnimator.start()
    }

//    private fun translateAnimatedDownloadButton(downloadButtonX: Float, downloadButtonRight:Float) {
//
//        val startPosition = this.x
//
//        val finalPosition = -(this.x - downloadButtonX)
//
//        val translationAnimator = ValueAnimator.ofFloat(startPosition, finalPosition)
//
//        translationAnimator.addUpdateListener {
//            this.translationX = it.animatedValue as Float
//        }
//
//        translationAnimator.start()
//
//    }

}