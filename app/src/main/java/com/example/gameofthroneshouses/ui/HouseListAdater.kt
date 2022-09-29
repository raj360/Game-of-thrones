package com.example.gameofthroneshouses.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.gameofthroneshouses.databinding.ItemViewHouseBinding
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.utils.startDetailsActivity

internal class CustomAdapter(private var housesList: List<House>, private var context: Context) :
    RecyclerView.Adapter<CustomAdapter.HouseViewHolder>() {


    internal inner class HouseViewHolder(private val binding: ItemViewHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(house: House){
            binding.houseName.text = house.name
            binding.houseRegion.text = house.region
            binding.itemCard.setOnClickListener { context.startDetailsActivity(house.name) }
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding = ItemViewHouseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        housesList[position].let { holder.setData(it) }
    }

    override fun getItemCount(): Int {
        return housesList.size
    }

}