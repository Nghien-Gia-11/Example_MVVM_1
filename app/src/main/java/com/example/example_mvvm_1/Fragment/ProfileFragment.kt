package com.example.example_mvvm_1.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example_mvvm_1.Adapter.ProfileAdapter
import com.example.example_mvvm_1.R
import com.example.example_mvvm_1.ViewModel.CountCharacterViewModel
import com.example.example_mvvm_1.ViewModel.ProfileViewModel
import com.example.example_mvvm_1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profile.observe(viewLifecycleOwner) { item ->
            binding.progressViewHistory.visibility = View.GONE
            binding.txtFullName.text = item.fullName
            binding.txtPosition.text = item.position
            binding.viewHistory.apply {
                adapter = ProfileAdapter(item.history.reversed())
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