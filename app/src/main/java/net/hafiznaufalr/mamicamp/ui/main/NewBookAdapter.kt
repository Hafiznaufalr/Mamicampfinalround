package net.hafiznaufalr.mamicamp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.Result
import net.hafiznaufalr.mamicamp.databinding.ItemNewBookBinding
import net.hafiznaufalr.mamicamp.utils.Constant.BASE_IMAGE_URL
import net.hafiznaufalr.mamicamp.utils.Constant.IMAGE_KEY

class NewBookAdapter(private val books: List<Result>,
                  private val onCLickListener: (Result) -> Unit
) :
    RecyclerView.Adapter<NewBookAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNewBookBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_new_book, parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindItem(books[position], onCLickListener)
    }

    class BookViewHolder(private val binding: ItemNewBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Result, onCLickListener: (Result) -> Unit) {
            val cover = BASE_IMAGE_URL + item.coverUrl + IMAGE_KEY
            Glide.with(binding.ivBook.context).load(cover).into(binding.ivBook)
            binding.tvTitleBook.text = item.title
            binding.tvWriter.text = item.writerByWriterId?.userByUserId?.name
        }
    }

}