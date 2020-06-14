package net.hafiznaufalr.mamicamp.ui.detailBook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.mamicamp.data.model.DetailBookResponse
import net.hafiznaufalr.mamicamp.data.model.GenreResponse
import net.hafiznaufalr.mamicamp.data.model.NewBookResponse
import net.hafiznaufalr.mamicamp.data.repository.RemoteRepository
import net.hafiznaufalr.mamicamp.utils.Resource
import java.lang.Exception

class DetailViewModel(private val repository: RemoteRepository): ViewModel() {
    private val detailBookResponse = MutableLiveData<Resource<DetailBookResponse>>()

    fun getDataDetailBook(id: String): LiveData<Resource<DetailBookResponse>> {
        detailBookResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val data = repository.getDataBookDetail(id)
                detailBookResponse.postValue(Resource.success(data))
            }catch (ex: Exception){
                detailBookResponse.postValue(Resource.error(ex.toString(), null))
            }
        }
        return detailBookResponse
    }
}