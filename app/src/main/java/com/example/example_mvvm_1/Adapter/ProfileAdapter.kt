package com.example.example_mvvm_1.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_1.Model.History
import com.example.example_mvvm_1.databinding.LayoutItemHistoryBinding

class ProfileAdapter(private val history: List<History>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private lateinit var binding: LayoutItemHistoryBinding

    inner class ViewHolder(binding: LayoutItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = LayoutItemHistoryBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            binding.txtTitle.text = history[position].title
            binding.txtIsUp.text = if (history[position].isUp) "UP!" else "DOWN!"
        }
    }

}