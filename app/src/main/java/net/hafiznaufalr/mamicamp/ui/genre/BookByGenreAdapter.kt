package net.hafiznaufalr.mamicamp.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.Book
import net.hafiznaufalr.mamicamp.data.model.Genre
import net.hafiznaufalr.mamicamp.databinding.ItemBookByGenreBinding
import net.hafiznaufalr.mamicamp.utils.Constant


class BookByGenreAdapter(
    private val books: List<Book>,
    private val onCLickListener: (Book) -> Unit
) :
    RecyclerView.Adapter<BookByGenreAdapter.BookByGenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookByGenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBookByGenreBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_book_by_genre, parent, false)
        return BookByGenreViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookByGenreViewHolder, position: Int) {
        holder.bindItem(books[position], onCLickListener)
    }

    class BookByGenreViewHolder(private val binding: ItemBookByGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Book, onCLickListener: (Book) -> Unit) {
            val cover = Constant.BASE_IMAGE_URL + item.coverUrl + Constant.IMAGE_KEY
            Glide.with(binding.ivBook.context).load(cover).into(binding.ivBook)
            binding.tvTitleBook.text = item.title
            binding.tvWriter.text = item.writerByWriterId?.userByUserId?.name
        }
    }

}