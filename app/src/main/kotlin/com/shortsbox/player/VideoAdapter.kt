package com.shortsbox.player

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class VideoAdapter(private val context: Context, private val videos: List<Uri>)
    : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(val playerView: PlayerView) : RecyclerView.ViewHolder(playerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val playerView = PlayerView(context)
        playerView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return VideoViewHolder(playerView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val player = ExoPlayer.Builder(context).build()
        holder.playerView.player = player
        player.setMediaItem(MediaItem.fromUri(videos[position]))
        player.prepare()
        player.playWhenReady = true
    }

    override fun getItemCount(): Int = videos.size
}