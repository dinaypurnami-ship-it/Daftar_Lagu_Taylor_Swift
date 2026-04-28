package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val semuaLagu = arrayOf(
        "Love Story", "You Belong With Me",
        "Blank Space", "Shake It Off",
        "Cruel Summer", "Lover"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnPlay = findViewById<Button>(R.id.btnPlay)

        val cardFearless = findViewById<LinearLayout>(R.id.cardFearless)
        val card1989 = findViewById<LinearLayout>(R.id.card1989)
        val cardLover = findViewById<LinearLayout>(R.id.cardLover)

        // CLICK ALBUM
        cardFearless.setOnClickListener {
            pindahAlbum("Fearless")
        }

        card1989.setOnClickListener {
            pindahAlbum("1989")
        }

        cardLover.setOnClickListener {
            pindahAlbum("Lover")
        }

        // SEARCH (LINEAR SEARCH)
        btnPlay.setOnClickListener {
            val input = etSearch.text.toString()

            if (input.isEmpty()) {
                etSearch.error = "Isi dulu ya"
                return@setOnClickListener
            }

            var ditemukan = false

            for (lagu in semuaLagu) {
                if (lagu.equals(input, true)) {
                    ditemukan = true

                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("NAMA_LAGU", lagu)
                    startActivity(intent)
                    break
                }
            }

            if (!ditemukan) {
                Toast.makeText(this, "Lagu tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pindahAlbum(namaAlbum: String) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra("ALBUM", namaAlbum)
        startActivity(intent)
    }
}