package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityAlbumBinding

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumName = intent.getStringExtra("album") ?: "Album"
        binding.txtAlbumTitle.text = albumName

        binding.song1.setOnClickListener {
            openDetail(binding.song1.text.toString())
        }

        binding.song2.setOnClickListener {
            openDetail(binding.song2.text.toString())
        }

        binding.song3.setOnClickListener {
            openDetail(binding.song3.text.toString())
        }

        binding.btnSearch.setOnClickListener {
            val keyword = binding.edtSearch.text.toString().trim()

            if (keyword.isEmpty()) {
                binding.edtSearch.error = "Kolom pencarian tidak boleh kosong"
                Toast.makeText(this, "Masukkan nama lagu terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Input diterima: $keyword", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openDetail(song: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }
}