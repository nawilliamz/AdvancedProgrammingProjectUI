package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.updateLayoutParams

class ProgressCircle @JvmOverloads constructor (
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
        ) : View(context, attrs, defStyleAttr) {


        private var width_cir = 0
        private var height_cir = 0


        private var top_cir = 0f
        private var bottom_cir = 0f
        private var left_cir = 0f
        private var right_cir = 0f

        private var rect = RectF()

        private var accentColor = 0

        private var sweepAngleCircle = 0f

        val circlePaint = Paint (Paint.ANTI_ALIAS_FLAG).apply {
                style = Paint.Style.FILL

        }

        init {
            context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
                accentColor = getColor(R.styleable.LoadingButton_accentColor, 0)
                sweepAngleCircle = getFloat(R.styleable.LoadingButton_sweepAngle, 0f)

            }
        }


    suspend fun showAnimatedCircle () {
//        this.isGone = false
//
        animateCircle()

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        width_cir = w
        height_cir = h

        top_cir = 0f
        bottom_cir = top_cir + width
        left_cir = 0f
        right_cir = left_cir + width

        circlePaint.color = accentColor

        rect = RectF(left_cir, top_cir, right_cir, bottom_cir)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        circlePaint.color = accentColor

//        var sweepAngle = 270f

        canvas?.drawArc(rect, 0f, sweepAngleCircle, true, circlePaint)

    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }



    private fun animateCircle () {

        val initialPosition = 0f
        val finalPosition = 360f

        val positionAnimator = ValueAnimator.ofFloat(initialPosition, finalPosition)
        positionAnimator.duration = 5000

        positionAnimator.addUpdateListener {
               this.sweepAngleCircle = it.animatedValue as Float
        }

        positionAnimator.start()
    }

}