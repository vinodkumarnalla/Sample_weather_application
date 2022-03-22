package com.android.app.sampleapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.app.sampleapplication.R
import com.android.app.sampleapplication.database.WeatherEntity
import com.android.app.sampleapplication.databinding.ListItemBinding

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private var list = listOf<WeatherEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(weatherEntity: WeatherEntity) {
            binding.txtName.text = weatherEntity.name
            val humidity = binding.root.context.getText(R.string.humidity)
            binding.txtMaxHumidity.text = "$humidity" + weatherEntity.humidity
            val maxTemp = binding.root.context.getText(R.string.max_temp)
            binding.txtMaxTemp.text = "$maxTemp" + weatherEntity.temp_max
            val minTemp = binding.root.context.getText(R.string.humidity)
            binding.txtMinTemp.text = "$minTemp" + weatherEntity.temp_min
        }

    }

    fun setData(list: List<WeatherEntity>) {
        this.list = list
        notifyDataSetChanged()
    }
}