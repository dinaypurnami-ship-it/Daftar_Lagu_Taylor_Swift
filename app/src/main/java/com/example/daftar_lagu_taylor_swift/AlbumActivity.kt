package com.example.daftar_lagu_taylor_swift

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daftar_lagu_taylor_swift.databinding.ActivityAlbumBinding

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding
    private lateinit var songViews: List<TextView>

    companion object {
        private const val TAG = "42430004"
    }

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

        try {
            Log.d(TAG, "AlbumActivity: onCreate dimulai")

            binding = ActivityAlbumBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val albumName = intent.getStringExtra("album") ?: ""

            if (albumName.isEmpty()) {
                Log.w(TAG, "AlbumActivity: Data album kosong atau tidak ditemukan dari Intent")
            } else {
                Log.d(TAG, "AlbumActivity: Album diterima dari Intent = $albumName")
            }

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

            Log.d(TAG, "AlbumActivity: Jumlah lagu pada album $albumName = ${albumSongs.size}")

            showSongs(displayedSongs)

            binding.btnSearch.setOnClickListener {
                try {
                    val keyword = binding.edtSearch.text.toString().trim()

                    Log.d(TAG, "AlbumActivity: Tombol search diklik dengan keyword = $keyword")

                    if (keyword.isEmpty()) {
                        Log.w(TAG, "AlbumActivity: Keyword pencarian kosong")

                        Toast.makeText(
                            this,
                            "Masukkan judul lagu yang ingin dicari",
                            Toast.LENGTH_SHORT
                        ).show()

                        displayedSongs = albumSongs
                        showSongs(albumSongs)
                    } else {
                        val searchResult = linearSearch(keyword)

                        Log.d(TAG, "AlbumActivity: Jumlah hasil pencarian = ${searchResult.size}")

                        if (searchResult.isEmpty()) {
                            Log.w(TAG, "AlbumActivity: Lagu dengan keyword $keyword tidak ditemukan")

                            Toast.makeText(
                                this,
                                "Lagu tidak ditemukan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        showSongs(searchResult)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "AlbumActivity: Error saat melakukan pencarian lagu", e)
                }
            }

            binding.btnSortAz.setOnClickListener {
                try {
                    Log.d(TAG, "AlbumActivity: Tombol sort A-Z diklik")

                    displayedSongs = bubbleSort(displayedSongs, true)
                    showSongs(displayedSongs)

                    Log.d(TAG, "AlbumActivity: Lagu berhasil diurutkan A-Z")
                } catch (e: Exception) {
                    Log.e(TAG, "AlbumActivity: Error saat sorting A-Z", e)
                }
            }

            binding.btnSortZa.setOnClickListener {
                try {
                    Log.d(TAG, "AlbumActivity: Tombol sort Z-A diklik")

                    displayedSongs = bubbleSort(displayedSongs, false)
                    showSongs(displayedSongs)

                    Log.d(TAG, "AlbumActivity: Lagu berhasil diurutkan Z-A")
                } catch (e: Exception) {
                    Log.e(TAG, "AlbumActivity: Error saat sorting Z-A", e)
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "AlbumActivity: Terjadi error saat menjalankan onCreate", e)
        }
    }

    private fun getSongsByAlbum(albumName: String): Array<Song> {
        return try {
            Log.d(TAG, "AlbumActivity: Mengambil lagu berdasarkan album = $albumName")

            val result = mutableListOf<Song>()

            for (song in songArray) {
                if (song.album == albumName) {
                    result.add(song)
                    Log.d(TAG, "AlbumActivity: Lagu ditemukan = ${song.title}")
                }
            }

            Log.d(TAG, "AlbumActivity: Proses filter album selesai")
            result.toTypedArray()
        } catch (e: Exception) {
            Log.e(TAG, "AlbumActivity: Error saat mengambil lagu berdasarkan album", e)
            arrayOf()
        }
    }

    // LINEAR SEARCH
    private fun linearSearch(keyword: String): Array<Song> {
        return try {
            Log.d(TAG, "AlbumActivity: Linear search dimulai dengan keyword = $keyword")

            val result = mutableListOf<Song>()

            for (song in albumSongs) {
                Log.d(TAG, "AlbumActivity: Mengecek lagu = ${song.title}")

                if (song.title.lowercase().contains(keyword.lowercase())) {
                    result.add(song)
                    Log.d(TAG, "AlbumActivity: Lagu cocok ditemukan = ${song.title}")
                }
            }

            displayedSongs = result.toTypedArray()

            Log.d(TAG, "AlbumActivity: Linear search selesai")
            displayedSongs
        } catch (e: Exception) {
            Log.e(TAG, "AlbumActivity: Error saat linear search", e)
            arrayOf()
        }
    }

    // BUBBLE SORT A-Z DAN Z-A
    private fun bubbleSort(data: Array<Song>, ascending: Boolean): Array<Song> {
        return try {
            val sortType = if (ascending) "A-Z" else "Z-A"
            Log.d(TAG, "AlbumActivity: Bubble sort $sortType dimulai")

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
                        Log.d(
                            TAG,
                            "AlbumActivity: Menukar ${sortedData[j].title} dengan ${sortedData[j + 1].title}"
                        )

                        val temp = sortedData[j]
                        sortedData[j] = sortedData[j + 1]
                        sortedData[j + 1] = temp
                    }
                }
            }

            Log.d(TAG, "AlbumActivity: Bubble sort $sortType selesai")
            sortedData
        } catch (e: Exception) {
            Log.e(TAG, "AlbumActivity: Error saat bubble sort", e)
            data
        }
    }

    private fun showSongs(songs: Array<Song>) {
        try {
            Log.d(TAG, "AlbumActivity: Menampilkan ${songs.size} lagu ke layar")

            for (i in songViews.indices) {
                if (i < songs.size) {
                    val songTitle = songs[i].title

                    songViews[i].visibility = View.VISIBLE
                    songViews[i].text = songTitle

                    Log.d(TAG, "AlbumActivity: Menampilkan lagu = $songTitle")

                    songViews[i].setOnClickListener {
                        Log.d(TAG, "AlbumActivity: Lagu $songTitle diklik")
                        openDetail(songTitle)
                    }
                } else {
                    songViews[i].visibility = View.GONE
                    Log.d(TAG, "AlbumActivity: Song view ke-$i disembunyikan")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "AlbumActivity: Error saat menampilkan lagu", e)
        }
    }

    private fun openDetail(song: String) {
        try {
            Log.d(TAG, "AlbumActivity: Membuka DetailActivity untuk lagu = $song")

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("song", song)
            startActivity(intent)

            Log.d(TAG, "AlbumActivity: Berhasil membuka DetailActivity")
        } catch (e: Exception) {
            Log.e(TAG, "AlbumActivity: Gagal membuka DetailActivity", e)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "AlbumActivity: onStart - Activity mulai terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "AlbumActivity: onResume - Activity aktif dan bisa digunakan")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "AlbumActivity: onPause - Activity mulai tidak aktif")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "AlbumActivity: onStop - Activity masuk ke latar belakang")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "AlbumActivity: onDestroy - Activity dihancurkan")
    }
}