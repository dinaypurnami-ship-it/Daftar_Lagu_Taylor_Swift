package com.example.daftar_lagu_taylor_swift

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val song = intent.getStringExtra("song") ?: "Unknown Song"
        binding.txtSongTitle.text = song

        binding.btnPlay.setOnClickListener {
            Toast.makeText(this, "Memutar lagu: $song", Toast.LENGTH_SHORT).show()
        }
    }
}