package com.example.example_mvvm_1.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example_mvvm_1.Adapter.CountCharacterAdapter
import com.example.example_mvvm_1.ViewModel.CountCharacterViewModel
import com.example.example_mvvm_1.databinding.FragmentCountCharacterBinding


class CountCharacterFragment : Fragment() {

    private var _binding : FragmentCountCharacterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountCharacterViewModel by viewModels()

    private lateinit var countCharacterAdapter: CountCharacterAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheck.setOnClickListener {
            if (binding.edtStrTest.text.toString().isNotEmpty()) viewModel.countString(binding.edtStrTest.text.toString())
            else Toast.makeText(context, "Vui lòng nhập đầy đủ !!", Toast.LENGTH_LONG).show()
        }

        setAdapter()

    }

    private fun setAdapter() {
        viewModel.result.observe(viewLifecycleOwner) { hashMap ->
            countCharacterAdapter = CountCharacterAdapter(hashMap)
            binding.viewAnswer.apply {
                adapter = countCharacterAdapter
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}