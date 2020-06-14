package net.hafiznaufalr.mamicamp.data.model


import com.google.gson.annotations.SerializedName

data class NewBookResponse(
    @SerializedName("result")
    val result: List<NewBook>?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("time")
    val time: Time?
)

data class NewBook(
    @SerializedName("book_id")
    val bookId: Int?,
    @SerializedName("category")
    val category: Any?,
    @SerializedName("chapter_count")
    val chapterCount: Int?,
    @SerializedName("cover_url")
    val coverUrl: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isNew")
    val isNew: Boolean?,
    @SerializedName("is_update")
    val isUpdate: Boolean?,
    @SerializedName("rate_sum")
    val rateSum: Double?,
    @SerializedName("schedule_task")
    val scheduleTask: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("view_count")
    val viewCount: Int?,
    @SerializedName("Writer_by_writer_id")
    val writerByWriterId: WriterByWriterId?,
    @SerializedName("writer_id")
    val writerId: Int?
)

data class Time(
    @SerializedName("book_official")
    val bookOfficial: Double?,
    @SerializedName("chapter")
    val chapter: Double?,
    @SerializedName("chapter_book")
    val chapterBook: Double?,
    @SerializedName("chapter_new")
    val chapterNew: Double?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("user")
    val user: Double?,
    @SerializedName("viewer")
    val viewer: Double?
)

