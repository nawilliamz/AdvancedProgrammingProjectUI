package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


//    private var widthSize = 0
//    private var heightSize = 0
    var buttonPrimaryColor = 0
    var buttonDownloadColor = 0

    val buttonTop:Float = 140F
    val buttonBottom = buttonTop
    val buttonLeft = buttonTop * .20F
    val buttonRight = buttonTop * .2F

    val buttonPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL


    }
    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    private val valueAnimator = ValueAnimator()

    //Note the separate class for ButtonState
    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->

    }


    init {
        isClickable = true

        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            buttonPrimaryColor = getColor(R.styleable.LoadingButton_primaryButtonColor, 0)
            buttonDownloadColor = getColor(R.styleable.LoadingButton_downloadButtonColor, 0)

        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        buttonPaint.color = buttonPrimaryColor
        canvas?.drawRect(buttonLeft, buttonTop, buttonRight, buttonBottom, buttonPaint)



    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
//        widthSize = w
//        heightSize = h
        setMeasuredDimension(w, h)
    }

}