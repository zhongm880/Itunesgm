package com.example.itunesk.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesk.R
import com.example.itunesk.databinding.ResponseItemBinding
import com.example.itunesk.model.ItunesData
import com.example.itunesk.model.ItunesResponse

class ItunesAdapter : RecyclerView.Adapter<ItunesAdapter.ItunesViewHolder>() {

    class ItunesViewHolder(val binding: ResponseItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun onBind(dataItem: ItunesData){
            binding.tvArtistName.text =
                binding.root.context.getString(R.string.artist_name,
                dataItem.artistName)
            binding.tvPrimaryGenre.text =
                binding.root.context.getString(R.string.primary_genre,
                dataItem.primaryGenreName)
            binding.tvReleaseDate.text =
                binding.root.context.getString(R.string.release_date,
                dataItem.releaseDate)
            binding.tvTrackName.text =
                binding.root.context.getString(R.string.track_name,
                dataItem.trackName)
            binding.tvTrackPrice.text =
                binding.root.context.getString(R.string.track_price,
                dataItem.trackPrice.toString())
        }
    }

    private lateinit var dataSet: ItunesResponse

    fun setDataSet(dataSet: ItunesResponse){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesViewHolder {
        return ItunesViewHolder(
            ResponseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if(::dataSet.isInitialized) dataSet.results.size else 0
    }

    override fun onBindViewHolder(holder: ItunesViewHolder, position: Int) {
        dataSet?.let {
            holder.onBind(it.results[position])
        }
    }
}