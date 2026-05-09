package com.example.daftar_lagu_taylor_swift

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        private const val TAG = "42430004"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Log.d(TAG, "DetailActivity: onCreate dimulai")

            binding = ActivityDetailBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val song = intent.getStringExtra("song") ?: "Unknown Song"

            if (song == "Unknown Song") {
                Log.w(TAG, "DetailActivity: Data lagu tidak ditemukan dari Intent")
            } else {
                Log.d(TAG, "DetailActivity: Lagu diterima dari Intent = $song")
            }

            binding.txtSongTitle.text = song

            Log.d(TAG, "DetailActivity: Judul lagu berhasil ditampilkan")

            binding.btnPlay.setOnClickListener {
                try {
                    Log.d(TAG, "DetailActivity: Tombol Play diklik untuk lagu = $song")

                    Toast.makeText(
                        this,
                        "Memutar lagu: $song",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d(TAG, "DetailActivity: Toast berhasil ditampilkan")
                } catch (e: Exception) {
                    Log.e(TAG, "DetailActivity: Error saat tombol Play ditekan", e)
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "DetailActivity: Terjadi error saat menjalankan onCreate", e)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "DetailActivity: onStart - Activity mulai terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "DetailActivity: onResume - Activity aktif dan bisa digunakan")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "DetailActivity: onPause - Activity mulai tidak aktif")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "DetailActivity: onStop - Activity masuk ke latar belakang")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "DetailActivity: onDestroy - Activity dihancurkan")
    }
}