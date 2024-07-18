package com.example.example_mvvm_1.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.example_mvvm_1.Model.Point
import com.example.example_mvvm_1.R
import com.example.example_mvvm_1.ViewModel.TriangleViewModel
import com.example.example_mvvm_1.databinding.FragmentTriangleBinding


class TriangleFragment : Fragment() {

    private var _binding: FragmentTriangleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TriangleViewModel by viewModels()

    private var lsPoint = mutableListOf<Point>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTriangleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edts = listOf<EditText>(
            binding.edtXx, binding.edtXy,
            binding.edtAx, binding.edtAy,
            binding.edtBx, binding.edtBy,
            binding.edtCx, binding.edtCy
        )

        binding.btnCheck.setOnClickListener {
            if (!checkTextFilled(edts)) {
                Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show()
            } else {
                getPoint()
            }
        }
        binding.txtReset.setOnClickListener {
            resetPoint(edts)
        }
        getResult()
    }

    @SuppressLint("SetTextI18n")
    private fun resetPoint(edts: List<EditText>) {
        for (edt in edts) {
            edt.setText("")
        }
    }

    private fun getResult() {
        viewModel.result.observe(viewLifecycleOwner) { result ->
            binding.txtAnswer.text = result
        }
    }

    private fun getPoint() {
        lsPoint.clear()
        val x = Point(
            binding.edtXx.text.toString().toDouble(),
            binding.edtXy.text.toString().toDouble()
        )
        lsPoint.add(
            Point(
                binding.edtAx.text.toString().toDouble(), binding.edtAy.text.toString().toDouble()
            )
        )
        lsPoint.add(
            Point(
                binding.edtBx.text.toString().toDouble(), binding.edtBy.text.toString().toDouble()
            )
        )
        lsPoint.add(
            Point(
                binding.edtCx.text.toString().toDouble(), binding.edtCy.text.toString().toDouble()
            )
        )
        viewModel.getResult(x, lsPoint)
    }

    private fun checkTextFilled(edts: List<EditText>): Boolean {
        for (edt in edts) {
            if (edt.text.toString().isEmpty()) {
                return false
            }
        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}