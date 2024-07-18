package com.example.example_mvvm_1.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_1.databinding.LayoutItemCountCharacterBinding

class CountCharacterAdapter(private val hashMap: HashMap<String, Int>) : RecyclerView.Adapter<CountCharacterAdapter.ViewHolder>() {

    private lateinit var binding : LayoutItemCountCharacterBinding

    inner class ViewHolder(binding : LayoutItemCountCharacterBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = LayoutItemCountCharacterBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = hashMap.size
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keys = hashMap.keys
        holder.itemView.apply {
            binding.txtString.text= "${keys.elementAt(position)}   :"
            binding.txtAmount.text = hashMap[keys.elementAt(position)].toString()
        }
    }


}