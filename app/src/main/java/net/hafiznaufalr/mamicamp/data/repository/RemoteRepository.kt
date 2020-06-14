package net.hafiznaufalr.mamicamp.data.repository

import net.hafiznaufalr.mamicamp.data.model.BookByGenreResponse
import net.hafiznaufalr.mamicamp.data.model.DetailBookResponse
import net.hafiznaufalr.mamicamp.data.model.GenreResponse
import net.hafiznaufalr.mamicamp.data.model.NewBookResponse
import net.hafiznaufalr.mamicamp.data.network.ApiService
import net.hafiznaufalr.mamicamp.utils.Constant.TOKEN_HEADER

class RemoteRepository(private val apiService: ApiService) {
    suspend fun getDataNewBook(): NewBookResponse{
        return apiService.getDataNewBookAsync(TOKEN_HEADER, "7").await()
    }

    suspend fun getDataGenre(): GenreResponse{
        return apiService.getDataGenreAsync(TOKEN_HEADER).await()
    }

    suspend fun getDataBookByGenre(id: String): BookByGenreResponse{
        return apiService.getBooksByGenreIdAsync(TOKEN_HEADER, id).await()
    }

    suspend fun getDataBookDetail(id: String): DetailBookResponse{
        return apiService.getBookDetailByIdAsync(TOKEN_HEADER, id).await()
    }
}