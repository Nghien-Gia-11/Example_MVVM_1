package com.example.example_mvvm_1.ViewModel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.example_mvvm_1.Model.Point

class Draw @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var _touchPointsTriangle = listOf<Point>()
    var touchPointsTriangle : List<Point>
        get() = _touchPointsTriangle
        set(value) {
            _touchPointsTriangle = value
            invalidate()
        }

    private var _touchPoint = Point(0.0, 0.0)
    var touchPoint : Point
        get() = _touchPoint
        set(value) {
            _touchPoint = value
            invalidate()
        }


    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        strokeWidth = 15f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (point in touchPointsTriangle) {
            Log.e("TAG", "${point.x} ${point.y}")
            canvas.drawCircle(point.x.toFloat(), point.y.toFloat(), 15f, paint)
        }
        for (i in touchPointsTriangle.indices) {
            var next = i + 1
            if (next == touchPointsTriangle.size) {
                next = 0
            }
            canvas.drawLine(
                touchPointsTriangle[i].x.toFloat(),
                touchPointsTriangle[i].y.toFloat(),
                touchPointsTriangle[next].x.toFloat(),
                touchPointsTriangle[next].y.toFloat(),
                paint
            )
        }
        touchPoint.let {
            canvas.drawCircle(touchPoint.x.toFloat(), touchPoint.y.toFloat(), 15f, paint)

        }
    }

}