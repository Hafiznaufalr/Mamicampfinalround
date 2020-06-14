package net.hafiznaufalr.mamicamp.ui.detailBook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.Review
import net.hafiznaufalr.mamicamp.databinding.ItemReviewsBinding
import net.hafiznaufalr.mamicamp.utils.Constant

class ReviewsAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemReviewsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_reviews, parent, false)
        return ReviewsViewHolder(binding)
    }

    override fun getItemCount(): Int{
        return if (reviews.size > 5){
            5
        }else{
            reviews.size
        }
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bindItem(reviews[position])
    }

    class ReviewsViewHolder(private val binding: ItemReviewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Review) {
            val profileReviewers = Constant.BASE_IMAGE_URL + item.userByReviewerId?.photoUrl + Constant.IMAGE_KEY
            Glide.with(binding.ivProfileReviewers.context).load(profileReviewers).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.ivProfileReviewers)
            binding.tvNameReviewers.text = item.userByReviewerId?.name
            binding.tvReviews.text = item.review
        }
    }

}