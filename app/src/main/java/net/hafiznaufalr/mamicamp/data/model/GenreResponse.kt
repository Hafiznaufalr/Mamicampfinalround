package net.hafiznaufalr.mamicamp.data.model


import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("resource")
    val resource: List<Genre>?
)

data class Genre(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("icon_url")
    val iconUrl: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)