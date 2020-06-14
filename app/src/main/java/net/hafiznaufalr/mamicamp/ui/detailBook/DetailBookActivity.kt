package net.hafiznaufalr.mamicamp.ui.detailBook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.DetailBookResponse
import net.hafiznaufalr.mamicamp.data.model.Review
import net.hafiznaufalr.mamicamp.data.network.NetworkService
import net.hafiznaufalr.mamicamp.databinding.ActivityDetailBookBinding
import net.hafiznaufalr.mamicamp.ui.detailWriter.DetailWriterActivity
import net.hafiznaufalr.mamicamp.ui.main.MainViewModel
import net.hafiznaufalr.mamicamp.utils.Constant
import net.hafiznaufalr.mamicamp.utils.Status
import net.hafiznaufalr.mamicamp.utils.ViewModelFactory
import net.hafiznaufalr.mamicamp.utils.shortToast

class DetailBookActivity : AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBookBinding
    private lateinit var reviewsAdapter: ReviewsAdapter
    private var listReviews: MutableList<Review> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_book)
        setupViewModel()
        setupUI()
        observer()
    }

    private fun setupUI() {
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
        reviewsAdapter = ReviewsAdapter(listReviews)
        binding.rvReviews.adapter = reviewsAdapter

        binding.swipeDetail.setOnRefreshListener {
            observer()
        }
    }

    private fun setupViewModel() {
        val service = NetworkService.create()
        val factory = ViewModelFactory(service)
        detailViewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }


    private fun observer() {
        val idBook = intent.getIntExtra("idBook", 0).toString()
        detailViewModel.getDataDetailBook(idBook).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.swipeDetail.isRefreshing = true
                }
                Status.ERROR -> {
                    binding.swipeDetail.isRefreshing = false
                    shortToast(this, it.message!!)
                }
                Status.SUCCESS -> {
                    it.data.let { response ->
                        fetchDetail(response)
                        listReviews.clear()
                        listReviews.addAll(response?.result?.reviews!!)
                        reviewsAdapter.notifyDataSetChanged()
                    }
                    binding.swipeDetail.isRefreshing = false
                    binding.content.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun fetchDetail(response: DetailBookResponse?) {
        val cover =
            Constant.BASE_IMAGE_URL + response?.result?.coverUrl + Constant.IMAGE_KEY
        Glide.with(binding.ivBook.context).load(cover).into(binding.ivBook)
        binding.tvTitleBook.text = response?.result?.title
        binding.tvWriter.text =
            response?.result?.writerByWriterId?.userByUserId?.name
        binding.tvSynopsis.text = response?.result?.synopsis
        binding.tvSeeWriter.setOnClickListener {
            val intent = Intent(this, DetailWriterActivity::class.java)
            intent.putExtra("idWriter", response?.result?.writerByWriterId?.userId)
            startActivity(intent)
        }
    }


}