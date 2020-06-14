package net.hafiznaufalr.mamicamp.ui.detailWriter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.mamicamp.data.model.DetailWriterResponse
import net.hafiznaufalr.mamicamp.data.repository.RemoteRepository
import net.hafiznaufalr.mamicamp.utils.Resource
import java.lang.Exception

class DetailWriterViewModel(private val repository: RemoteRepository): ViewModel() {
    private val detailWriterResponse = MutableLiveData<Resource<DetailWriterResponse>>()

    fun getDataDetailWriter(id: String): LiveData<Resource<DetailWriterResponse>> {
        detailWriterResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val data = repository.getDataWriterProfile(id)
                detailWriterResponse.postValue(Resource.success(data))
            }catch (ex: Exception){
                detailWriterResponse.postValue(Resource.error(ex.toString(), null))
            }
        }
        return detailWriterResponse
    }
}