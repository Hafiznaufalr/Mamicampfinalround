package net.hafiznaufalr.mamicamp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.mamicamp.data.model.GenreResponse
import net.hafiznaufalr.mamicamp.data.model.NewBookResponse
import net.hafiznaufalr.mamicamp.data.repository.RemoteRepository
import net.hafiznaufalr.mamicamp.utils.Resource
import java.lang.Exception

class MainViewModel(private val repository: RemoteRepository): ViewModel() {
    private val newBookResponse = MutableLiveData<Resource<NewBookResponse>>()
    private val genreResponse = MutableLiveData<Resource<GenreResponse>>()

    fun getDataNewBooks(): LiveData<Resource<NewBookResponse>> {
        newBookResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val data = repository.getDataNewBook()
                newBookResponse.postValue(Resource.success(data))
            }catch (ex: Exception){
                newBookResponse.postValue(Resource.error(ex.toString(), null))
            }
        }
        return newBookResponse
    }

    fun getDataGenre(): LiveData<Resource<GenreResponse>> {
        newBookResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val data = repository.getDataGenre()
                genreResponse.postValue(Resource.success(data))
            }catch (ex: Exception){
                genreResponse.postValue(Resource.error(ex.toString(), null))
            }
        }
        return genreResponse
    }



}