package com.example.dmzj_api.entity

data class NewsCategory(
    val tag_id: Int = 0,
    val tag_name: String = "",
)

data class NewsBanner(
    val code: Int = 0,
    val msg: String = "",
    val data: List<Item>? = null,
) {
    data class Item(
        val id: Int = 0,
        val title: String = "",
        val pic_url: String = "",
        val object_id: String = "",
        val object_url: String = "",
    )
}