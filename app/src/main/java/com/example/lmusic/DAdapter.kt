package com.example.lmusic

import android.app.Activity
import android.icu.text.Transliterator.Position
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.squareup.picasso.Picasso
import kotlinx.coroutines.currentCoroutineContext

class DAdapter(val context : Activity, val dataList: List<Data>):
    RecyclerView.Adapter<DAdapter.MVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_row, parent,false)
        return MVHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MVHolder, position: Int){
        val currentData = dataList[position]
        holder.title.text = currentData.title
        val mediaPlayer= MediaPlayer.create(context, currentData.preview.toUri())
        Picasso.get().load(currentData.album.cover).into(holder.img);
        holder.play.setOnClickListener {
            mediaPlayer.start()
        }
        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }

    }
        class MVHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val img : ImageView
            val title : TextView
            val play : ImageButton
            val pause : ImageButton

            init {
                img = itemView.findViewById(R.id.MusicImage)
                title = itemView.findViewById(R.id.musicName)
                play = itemView.findViewById(R.id.btnPlay)
                pause = itemView.findViewById(R.id.btnPause)
            }
        }
}
