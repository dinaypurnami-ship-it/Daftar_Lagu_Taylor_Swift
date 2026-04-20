package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan variabel dengan ID yang ada di XML tadi
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnPlay = findViewById<Button>(R.id.btnPlay)

        btnPlay.setOnClickListener {
            val inputLagu = etSearch.text.toString()

            if (inputLagu.isEmpty()) {
                etSearch.error = "Dina, tulis judul lagu Taylor favoritmu dulu ya!"
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                // Baris DetailActivity di bawah ini akan merah sampai langkah #2 selesai
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("NAMA_LAGU", inputLagu)
                startActivity(intent)
            }
        }
    }
}