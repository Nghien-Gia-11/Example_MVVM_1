package com.example.example_mvvm_1.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example_mvvm_1.Model.Point
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class TriangleViewModel : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    private var _touchPointsTriangle = MutableLiveData<List<Point>>()
    val touchPointsTriangle : LiveData<List<Point>> get() = _touchPointsTriangle

    private var _touchPoint = MutableLiveData<Point>()
    val touchPoint : LiveData<Point> get() = _touchPoint

    fun addTouchPointsTriangle(point: Point){
        if (_touchPointsTriangle.value?.size == 3){
            _touchPoint.value = point
        }
        else{
            val currentPoints = _touchPointsTriangle.value?.toMutableList() ?: mutableListOf()
            currentPoints.add(point)
            _touchPointsTriangle.value = currentPoints
        }

    }


    private fun distance(x: Point, y: Point): Double {
        return sqrt((y.x - x.x).pow(2.0) + (y.y - x.y).pow(2.0))
    }

    // kiểm tra xem điểm có nằm trên cạnh tam giác không
    private fun checkStraight(x: Point, y: Point, z: Point): Boolean {
        return distance(x, z) + distance(y, z) == distance(x, y)
    }

    // tính diện tích tam giác
    private fun triangleArea(x: Point, y: Point, z: Point): Double {
        val a = distance(x, y)
        val b = distance(y, z)
        val c = distance(x, z)
        return sqrt((a + b + c) * (a + b - c) * (b + c - a) * (a + c - b)) / 4
    }

    // tính diện tích đa giác
    private fun calculateArea(lsPoint: List<Point>): Double {
        var area = 0.0

        for (i in lsPoint.indices) {
            val x1 = lsPoint[i].x
            val y1 = lsPoint[i].y
            val x2 = lsPoint[(i + 1) % lsPoint.size].x
            val y2 = lsPoint[(i + 1) % lsPoint.size].y
            area += x1 * y2 - y1 * x2
        }
        area = 0.5 * abs(area)
        return area
    }

    @SuppressLint("DefaultLocale")
    fun checkInOrOut(x: Point, lsPoint: List<Point>): Any {
        for (i in lsPoint.indices) {
            for (j in i + 1..<lsPoint.size) {
                if (checkStraight(lsPoint[i], lsPoint[j], x)) {
                    return "On"
                }
            }
        }
        val areaShape = calculateArea(lsPoint)
        var areaCheck = 0.0
        for (i in lsPoint.indices) {
            for (j in i + 1..<lsPoint.size) {
                areaCheck += triangleArea(x, lsPoint[i], lsPoint[j])
            }
        }
        return String.format("%.5f", areaCheck) == String.format("%.5f", areaShape)
    }

    fun getResult(x: Point, ls: List<Point>) {
        if (checkInOrOut(x, ls) == true) {
            _result.value = "điểm nằm trong tam giác"
        } else if (checkInOrOut(x, ls) == "On") {
            _result.value = "điểm nằm trên cạnh tam giác"
        } else {
            _result.value = "điểm nằm ngoài tam giác"
        }
    }


}