package com.udacity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)


        //Using to set text width & height
        width = w.toFloat()
        height = h.toFloat()



        top = 0F
        bottom = top + height
        left = 0F
//        right = left + width
        right = 0F

        textWidth = width/2
        textHeight = height/2

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        selectDownloadPaint.color = buttonDownloadColor
        canvas?.drawRect(left, top, right, bottom, selectDownloadPaint)

    }
}