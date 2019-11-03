package com.ptitsn.presentation.ui.screens

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ptitsn.presentation.R
import com.ptitsn.presentation.databinding.RhWetherBinding
import com.ptitsn.presentation.mvvm.model.WeatherUi

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherForecastHolder>() {
    private val mRegionsList: ArrayList<WeatherUi> = ArrayList()


    override fun getItemCount(): Int = mRegionsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastHolder {
        val binding = DataBindingUtil.inflate<RhWetherBinding>(LayoutInflater.from(parent.context),
                R.layout.rh_wether, parent, false)
        return WeatherForecastHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastHolder, position: Int) {
        holder.binding.weather = mRegionsList[position]
    }

    fun submitUpdate(regionsList: List<WeatherUi>?) {
        val difCallback = RegionDiffCallback(mRegionsList, regionsList ?: listOf())
        val diffResult = DiffUtil.calculateDiff(difCallback)
        this.mRegionsList.clear()
        this.mRegionsList.addAll(difCallback.new)
        diffResult.dispatchUpdatesTo(this)
    }

    class WeatherForecastHolder(val binding: RhWetherBinding) : RecyclerView.ViewHolder(binding.root)

    class RegionDiffCallback constructor(private val old: List<WeatherUi>,
                                         val new: List<WeatherUi>) : DiffUtil.Callback() {


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]
            return old == new
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]
            return old.equals(new)
        }

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return new[newItemPosition]
        }
    }

}