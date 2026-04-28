package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val tvAlbum = findViewById<TextView>(R.id.tvAlbum)
        val listLagu = findViewById<ListView>(R.id.listLagu)

        val album = intent.getStringExtra("ALBUM")
        tvAlbum.text = album

        val laguList = when (album) {
            "Fearless" -> arrayOf("Love Story", "You Belong With Me")
            "1989" -> arrayOf("Blank Space", "Shake It Off")
            else -> arrayOf("Cruel Summer", "Lover")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, laguList)
        listLagu.adapter = adapter

        listLagu.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("NAMA_LAGU", laguList[position])
            startActivity(intent)
        }
    }
}