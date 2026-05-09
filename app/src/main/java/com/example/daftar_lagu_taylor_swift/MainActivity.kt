package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "42430004"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Log.d(TAG, "MainActivity: onCreate dimulai")

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            Log.d(TAG, "MainActivity: Layout utama berhasil ditampilkan")

            binding.cardMidnights.setOnClickListener {
                try {
                    Log.d(TAG, "MainActivity: Card album Midnights diklik")

                    val intent = Intent(this, AlbumActivity::class.java)
                    intent.putExtra("album", "Midnights")
                    startActivity(intent)

                    Log.d(TAG, "MainActivity: Berhasil membuka AlbumActivity dengan album Midnights")
                } catch (e: Exception) {
                    Log.e(TAG, "MainActivity: Gagal membuka album Midnights", e)
                }
            }

            binding.cardLover.setOnClickListener {
                try {
                    Log.d(TAG, "MainActivity: Card album Lover diklik")

                    val intent = Intent(this, AlbumActivity::class.java)
                    intent.putExtra("album", "Lover")
                    startActivity(intent)

                    Log.d(TAG, "MainActivity: Berhasil membuka AlbumActivity dengan album Lover")
                } catch (e: Exception) {
                    Log.e(TAG, "MainActivity: Gagal membuka album Lover", e)
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "MainActivity: Terjadi error saat menjalankan onCreate", e)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart - Activity mulai terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume - Activity aktif dan bisa digunakan")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity: onPause - Activity mulai tidak aktif")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop - Activity masuk ke latar belakang")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy - Activity dihancurkan")
    }
}