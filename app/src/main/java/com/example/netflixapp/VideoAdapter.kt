package com.example.netflixapp

import android.accounts.Account
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


typealias ClickHandler = (video : Video) -> Unit

class VideoAdapter(var dataSet: ArrayList<Video> ,
                   var clickHandler : ClickHandler):
    ListAdapter<Video, VideoAdapter.ViewHolder>(VideoDiffCallback)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        val view = from(parent.context)
            .inflate(R.layout.video_layout, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position) , clickHandler)
    }

    object VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txvVideo = view.findViewById<TextView>(R.id.txv_video_image)
        val imvVideo = view.findViewById<ImageView>(R.id.imv_video)
        val imvFavorite = view.findViewById<ImageView>(R.id.imv_fav)

        fun bind(video: Video , clickHandler: ClickHandler ){

            txvVideo.text = video.name
            imvVideo.setImageResource(video.image)
            if (video.isFave) {
                imvFavorite.setImageResource(R.drawable.ic_baseline_favorite_red)
            }else{
                imvFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            imvFavorite.setOnClickListener { clickHandler }

        }
    }
}