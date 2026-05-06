package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityAlbumBinding

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding
    private lateinit var songViews: List<TextView>

    data class Song(
        val title: String,
        val album: String
    )

    // DATA ARRAY
    private val songArray = arrayOf(
        Song("Anti-Hero", "Midnights"),
        Song("Lavender Haze", "Midnights"),
        Song("Midnight Rain", "Midnights"),
        Song("Bejeweled", "Midnights"),
        Song("Karma", "Midnights"),

        Song("Lover", "Lover"),
        Song("Cruel Summer", "Lover"),
        Song("The Archer", "Lover"),
        Song("Paper Rings", "Lover"),
        Song("Daylight", "Lover")
    )

    private var albumSongs = arrayOf<Song>()
    private var displayedSongs = arrayOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumName = intent.getStringExtra("album") ?: ""

        binding.txtAlbumTitle.text = albumName

        songViews = listOf(
            binding.song1,
            binding.song2,
            binding.song3,
            binding.song4,
            binding.song5
        )

        albumSongs = getSongsByAlbum(albumName)
        displayedSongs = albumSongs

        showSongs(displayedSongs)

        binding.btnSearch.setOnClickListener {
            val keyword = binding.edtSearch.text.toString().trim()

            if (keyword.isEmpty()) {
                Toast.makeText(this, "Masukkan judul lagu yang ingin dicari", Toast.LENGTH_SHORT).show()
                showSongs(albumSongs)
            } else {
                val searchResult = linearSearch(keyword)

                if (searchResult.isEmpty()) {
                    Toast.makeText(this, "Lagu tidak ditemukan", Toast.LENGTH_SHORT).show()
                }

                showSongs(searchResult)
            }
        }

        binding.btnSortAz.setOnClickListener {
            displayedSongs = bubbleSort(displayedSongs, true)
            showSongs(displayedSongs)
        }

        binding.btnSortZa.setOnClickListener {
            displayedSongs = bubbleSort(displayedSongs, false)
            showSongs(displayedSongs)
        }
    }

    private fun getSongsByAlbum(albumName: String): Array<Song> {
        val result = mutableListOf<Song>()

        for (song in songArray) {
            if (song.album == albumName) {
                result.add(song)
            }
        }

        return result.toTypedArray()
    }

    // LINEAR SEARCH
    private fun linearSearch(keyword: String): Array<Song> {
        val result = mutableListOf<Song>()

        for (song in albumSongs) {
            if (song.title.lowercase().contains(keyword.lowercase())) {
                result.add(song)
            }
        }

        displayedSongs = result.toTypedArray()
        return displayedSongs
    }

    // BUBBLE SORT A-Z DAN Z-A
    private fun bubbleSort(data: Array<Song>, ascending: Boolean): Array<Song> {
        val sortedData = data.copyOf()

        for (i in 0 until sortedData.size - 1) {
            for (j in 0 until sortedData.size - i - 1) {
                val compareResult = sortedData[j].title.compareTo(sortedData[j + 1].title)

                val shouldSwap = if (ascending) {
                    compareResult > 0
                } else {
                    compareResult < 0
                }

                if (shouldSwap) {
                    val temp = sortedData[j]
                    sortedData[j] = sortedData[j + 1]
                    sortedData[j + 1] = temp
                }
            }
        }

        return sortedData
    }

    private fun showSongs(songs: Array<Song>) {
        for (i in songViews.indices) {
            if (i < songs.size) {
                songViews[i].visibility = View.VISIBLE
                songViews[i].text = songs[i].title

                songViews[i].setOnClickListener {
                    openDetail(songs[i].title)
                }
            } else {
                songViews[i].visibility = View.GONE
            }
        }
    }

    private fun openDetail(song: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }
}