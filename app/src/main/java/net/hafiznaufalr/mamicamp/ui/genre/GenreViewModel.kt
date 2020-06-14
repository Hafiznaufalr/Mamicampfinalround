package net.hafiznaufalr.mamicamp.ui.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.mamicamp.data.model.BookByGenreResponse
import net.hafiznaufalr.mamicamp.data.model.GenreResponse
import net.hafiznaufalr.mamicamp.data.model.NewBookResponse
import net.hafiznaufalr.mamicamp.data.repository.RemoteRepository
import net.hafiznaufalr.mamicamp.utils.Resource
import java.lang.Exception

class GenreViewModel(private val repository: RemoteRepository): ViewModel() {
    private val bookByGenreResponse = MutableLiveData<Resource<BookByGenreResponse>>()

    fun getDataBookByGenreResponse(id: String): LiveData<Resource<BookByGenreResponse>> {
        bookByGenreResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val data = repository.getDataBookByGenre(id)
                bookByGenreResponse.postValue(Resource.success(data))
            }catch (ex: Exception){
                bookByGenreResponse.postValue(Resource.error(ex.toString(), null))
            }
        }
        return bookByGenreResponse
    }
}