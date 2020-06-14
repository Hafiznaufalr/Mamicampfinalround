package net.hafiznaufalr.mamicamp.data.model


import com.google.gson.annotations.SerializedName

data class DetailWriterResponse(
    @SerializedName("result")
    val result: Profile?,
    @SerializedName("success")
    val success: Boolean?
)

data class Profile(
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("coin")
    val coin: Int?,
    @SerializedName("count_follower")
    val countFollower: Int?,
    @SerializedName("count_following")
    val countFollowing: Int?,
    @SerializedName("current_level")
    val currentLevel: Int?,
    @SerializedName("deskripsi")
    val deskripsi: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("following_user")
    val followingUser: List<Any>?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_following")
    val isFollowing: Boolean?,
    @SerializedName("link_user")
    val linkUser: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("photo_url")
    val photoUrl: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("writer_id")
    val writerId: Int?,
    @SerializedName("writer_level")
    val writerLevel: Int?,
    @SerializedName("writer_level_name")
    val writerLevelName: String?
)