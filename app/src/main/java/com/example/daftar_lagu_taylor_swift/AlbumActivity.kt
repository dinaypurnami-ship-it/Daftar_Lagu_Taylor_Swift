package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityAlbumBinding
import kotlin.jvm.java

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumName = intent.getStringExtra("album")
        binding.txtAlbumTitle.text = albumName

        binding.song1.setOnClickListener {
            openDetail("Anti-Hero")
        }

        binding.song2.setOnClickListener {
            openDetail("Lavender Haze")
        }
    }

    private fun openDetail(song: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }
}