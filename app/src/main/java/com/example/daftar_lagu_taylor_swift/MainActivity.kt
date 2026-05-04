package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardMidnights.setOnClickListener {
            val intent = Intent(this, AlbumActivity::class.java)
            intent.putExtra("album", "Midnights")
            startActivity(intent)
        }

        binding.cardLover.setOnClickListener {
            val intent = Intent(this, AlbumActivity::class.java)
            intent.putExtra("album", "Lover")
            startActivity(intent)
        }
    }
}