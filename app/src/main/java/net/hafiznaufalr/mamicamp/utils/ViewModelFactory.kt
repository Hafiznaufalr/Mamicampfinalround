package net.hafiznaufalr.mamicamp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.hafiznaufalr.mamicamp.data.network.ApiService
import net.hafiznaufalr.mamicamp.data.repository.RemoteRepository
import net.hafiznaufalr.mamicamp.ui.detailBook.DetailViewModel
import net.hafiznaufalr.mamicamp.ui.genre.GenreViewModel
import net.hafiznaufalr.mamicamp.ui.main.MainViewModel

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(RemoteRepository(apiService)) as T
        }
        if (modelClass.isAssignableFrom(GenreViewModel::class.java)) {
            return GenreViewModel(RemoteRepository(apiService)) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(RemoteRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}