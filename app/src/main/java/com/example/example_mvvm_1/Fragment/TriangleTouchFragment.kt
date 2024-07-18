package com.example.example_mvvm_1.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.example_mvvm_1.Model.Point
import com.example.example_mvvm_1.ViewModel.TriangleViewModel
import com.example.example_mvvm_1.databinding.FragmentTriangleTouchBinding


class TriangleTouchFragment : Fragment() {

    private var _binding: FragmentTriangleTouchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TriangleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTriangleTouchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // gán lên view tọa độ đỉnh tam giác vừa chạm
        viewModel.touchPointsTriangle.observe(viewLifecycleOwner) {
            binding.viewDraw.touchPointsTriangle = it
        }
        // gán lên view tọa độ điểm vừa chạm
        viewModel.touchPoint.observe(viewLifecycleOwner) {
            binding.viewDraw.touchPoint = it
            viewModel.getResult(it, binding.viewDraw.touchPointsTriangle)
        }
        getPosition()
        viewModel.result.observe(viewLifecycleOwner) { item ->
            Toast.makeText(context, item, Toast.LENGTH_LONG).show()
        }
    }

    // lấy tọa độ các điểm chạm
    @SuppressLint("ClickableViewAccessibility")
    private fun getPosition(){
        binding.viewDraw.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    viewModel.addTouchPointsTriangle(Point(event.x.toDouble(), event.y.toDouble()))
                }
            }
            true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}