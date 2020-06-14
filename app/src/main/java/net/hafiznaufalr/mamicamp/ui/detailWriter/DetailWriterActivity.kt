package net.hafiznaufalr.mamicamp.ui.detailWriter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.DetailWriterResponse
import net.hafiznaufalr.mamicamp.data.network.NetworkService
import net.hafiznaufalr.mamicamp.databinding.ActivityDetailWriterBinding
import net.hafiznaufalr.mamicamp.ui.genre.GenreViewModel
import net.hafiznaufalr.mamicamp.utils.*
import net.hafiznaufalr.mamicamp.utils.Constant.IMAGE_KEY

class DetailWriterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailWriterBinding
    private lateinit var detailWriterViewModel: DetailWriterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_writer)
        setupViewModel()
        setupUI()
        observer()
    }

    private fun setupViewModel() {
        val service = NetworkService.create()
        val factory = ViewModelFactory(service)
        detailWriterViewModel = ViewModelProvider(this, factory).get(DetailWriterViewModel::class.java)
    }

    private fun setupUI() {
        binding.icBack.setOnClickListener {
            onBackPressed()
        }

        binding.swipeDetail.setOnRefreshListener {
            observer()
        }
    }

    private fun observer() {
        val idUser = intent.getIntExtra("idUser", 0).toString()
        detailWriterViewModel.getDataDetailWriter(idUser).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.swipeDetail.isRefreshing = true
                }
                Status.ERROR -> {
                    binding.swipeDetail.isRefreshing = false
                    shortToast(this, it.message!!)
                }
                Status.SUCCESS -> {
                    fetchDetail(it.data)
                    binding.swipeDetail.isRefreshing = false
                    binding.content.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun fetchDetail(response: DetailWriterResponse?) {
        val profile = Constant.BASE_IMAGE_URL + response?.result?.photoUrl + IMAGE_KEY
        Glide.with(binding.ivProfile.context).load(profile).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.ivProfile)
        binding.tvName.text = response?.result?.name
        binding.tvEmail.text = response?.result?.email
        binding.tvPhone.text = response?.result?.phone
        binding.tvDesc.text = response?.result?.deskripsi
    }


}