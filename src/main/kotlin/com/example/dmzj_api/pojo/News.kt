package com.example.dmzj_api.pojo

data class NewsBanner(
    val code: Int = 0,
    val msg: String = "",
    val data: List<NewsBannerData>? = null,
) {
    data class NewsBannerData (
        val id: Int = 0,
        val title: String = "",
        val pic_url: String = "",
        val object_id: String = "",
        val object_url: String = "",
    )
}

data class NewsCategory(
    val tag_id: Int = 0,
    val tag_name: String = "",
)