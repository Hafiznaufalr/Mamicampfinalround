package net.hafiznaufalr.mamicamp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.Genre
import net.hafiznaufalr.mamicamp.data.model.Result
import net.hafiznaufalr.mamicamp.data.network.NetworkService
import net.hafiznaufalr.mamicamp.databinding.ActivityMainBinding
import net.hafiznaufalr.mamicamp.ui.genre.GenreActivity
import net.hafiznaufalr.mamicamp.utils.Status
import net.hafiznaufalr.mamicamp.utils.ViewModelFactory
import net.hafiznaufalr.mamicamp.utils.shortToast

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var newBookAdapter: NewBookAdapter
    private lateinit var genreAdapter: GenreAdapter
    private var listNewBook: MutableList<Result> = mutableListOf()
    private var listGenre: MutableList<Genre> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setupUI()
        observer()
    }

    private fun setupViewModel() {
        val service = NetworkService.create()
        val factory = ViewModelFactory(service)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        newBookAdapter = NewBookAdapter(listNewBook){

        }
        binding.rvNewBooks.adapter = newBookAdapter


        genreAdapter = GenreAdapter(listGenre){genre ->
            val intent = Intent(this, GenreActivity::class.java)
            intent.putExtra("idGenre", genre.id)
            intent.putExtra("nameGenre", genre.title)
            startActivity(intent)
        }
        binding.rvGenre.adapter = genreAdapter

        binding.swipeHome.setOnRefreshListener {
            observer()
        }
    }

    private fun observer() {
        mainViewModel.getDataNewBooks().observe(this, Observer {
            when(it.status){
                Status.LOADING ->{
                    binding.swipeHome.isRefreshing = true
                }
                Status.ERROR ->{
                    binding.swipeHome.isRefreshing = false
                    shortToast(this, it.message!!)
                }
                Status.SUCCESS ->{
                    it.data.let {response ->
                        listNewBook.clear()
                        listNewBook.addAll(response?.result!!)
                        newBookAdapter.notifyDataSetChanged()
                    }
                    binding.swipeHome.isRefreshing = false
                    binding.content.visibility = View.VISIBLE
                }
            }
        })
        mainViewModel.getDataGenre().observe(this, Observer {
            when(it.status){
                Status.LOADING ->{
                    binding.swipeHome.isRefreshing = true
                }
                Status.ERROR ->{
                    binding.swipeHome.isRefreshing = false
                    shortToast(this, it.message!!)
                }
                Status.SUCCESS ->{
                    it.data.let {response ->
                        listGenre.clear()
                        listGenre.addAll(response?.resource!!)
                        genreAdapter.notifyDataSetChanged()
                    }
                    binding.swipeHome.isRefreshing = false
                    binding.content.visibility = View.VISIBLE
                }
            }
        })
    }
}