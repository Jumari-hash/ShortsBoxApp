package com.shortsbox.player

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import java.io.File

class MainActivity : ComponentActivity() {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = ViewPager2(this)
        setContentView(viewPager)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            loadVideos()
        }
    }

    private fun loadVideos() {
        val videoFolder = File(Environment.getExternalStorageDirectory(), "ShortsBox")
        val videoFiles = videoFolder.listFiles { file ->
            file.extension in listOf("mp4", "mkv", "mov")
        }?.map { Uri.fromFile(it) } ?: emptyList()

        val adapter = VideoAdapter(this, videoFiles)
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}