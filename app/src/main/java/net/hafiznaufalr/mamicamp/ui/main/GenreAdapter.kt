package net.hafiznaufalr.mamicamp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.hafiznaufalr.mamicamp.R
import net.hafiznaufalr.mamicamp.data.model.Genre
import net.hafiznaufalr.mamicamp.data.model.Result
import net.hafiznaufalr.mamicamp.databinding.ItemGenreBinding
import net.hafiznaufalr.mamicamp.utils.Constant

class GenreAdapter(
    private val genres: List<Genre>,
    private val onCLickListener: (Genre) -> Unit
) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemGenreBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_genre, parent, false)
        return GenreViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindItem(genres[position], onCLickListener)
    }

    class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Genre, onCLickListener: (Genre) -> Unit) {
            binding.tvGenre.text = item.title
            binding.root.rootView.setOnClickListener {
                onCLickListener(item)
            }
        }
    }

}