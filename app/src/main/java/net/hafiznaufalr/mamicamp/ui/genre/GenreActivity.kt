package net.hafiznaufalr.mamicamp.ui.genre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.Book
import net.hafiznaufalr.mamicamp.data.network.NetworkService
import net.hafiznaufalr.mamicamp.databinding.ActivityGenreBinding
import net.hafiznaufalr.mamicamp.databinding.ActivityMainBinding
import net.hafiznaufalr.mamicamp.ui.detailBook.DetailBookActivity
import net.hafiznaufalr.mamicamp.ui.main.MainViewModel
import net.hafiznaufalr.mamicamp.utils.Status
import net.hafiznaufalr.mamicamp.utils.ViewModelFactory
import net.hafiznaufalr.mamicamp.utils.shortToast

class GenreActivity : AppCompatActivity() {
    private lateinit var genreViewModel: GenreViewModel
    private lateinit var binding: ActivityGenreBinding
    private lateinit var bookByGenreAdapter: BookByGenreAdapter
    private var listBook: MutableList<Book> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_genre)
        setupViewModel()
        setupUI()
        observer()
    }


    private fun setupViewModel() {
        val service = NetworkService.create()
        val factory = ViewModelFactory(service)
        genreViewModel = ViewModelProvider(this, factory).get(GenreViewModel::class.java)
    }

    private fun setupUI() {
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
        val title = intent.getStringExtra("nameGenre")
        binding.tvGenre.text = title
        bookByGenreAdapter = BookByGenreAdapter(listBook){book ->
            val intent = Intent(this, DetailBookActivity::class.java)
            intent.putExtra("idBook", book.id)
            startActivity(intent)
        }
        binding.rvListBook.adapter = bookByGenreAdapter
        binding.swipeListBook.setOnRefreshListener {
            observer()
        }
    }

    private fun observer() {
        val id = intent.getIntExtra("idGenre", 0).toString()
        genreViewModel.getDataBookByGenreResponse(id).observe(this, Observer {
            when(it.status){
                Status.LOADING ->{
                    binding.swipeListBook.isRefreshing = true
                }
                Status.ERROR ->{
                    binding.swipeListBook.isRefreshing = false
                    shortToast(this, it.message!!)
                }
                Status.SUCCESS ->{
                    it.data.let {response ->
                        listBook.clear()
                        listBook.addAll(response?.result!!)
                        bookByGenreAdapter.notifyDataSetChanged()
                    }
                    binding.swipeListBook.isRefreshing = false
                    binding.content.visibility = View.VISIBLE
                }
            }
        })
    }
}