package net.hafiznaufalr.mamicamp.data.network

import kotlinx.coroutines.Deferred
import net.hafiznaufalr.mamicamp.data.model.BookByGenreResponse
import net.hafiznaufalr.mamicamp.data.model.GenreResponse
import net.hafiznaufalr.mamicamp.data.model.NewBookResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("api/v2/book/uptodate")
    fun getDataNewBookAsync(
        @Header("x-dreamfactory-api-key") apiKey: String,
        @Query("limit") limit: String
    ): Deferred<NewBookResponse>

    @GET("api/v2/cabaca/_table/genre")
    fun getDataGenreAsync(
        @Header("x-dreamfactory-api-key") apiKey: String
    ): Deferred<GenreResponse>

    @GET("api/v2/book/category")
    fun getBooksByGenreIdAsync(
        @Header("x-dreamfactory-api-key") apiKey: String,
        @Query("id") id: String
    ): Deferred<BookByGenreResponse>
}