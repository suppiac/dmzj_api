package com.example.dmzj_api.pojo

data class NovelRecommend(
    val category_id: Int = 0,
    val sort: Int = 0,
    val title: String = "",
    val data: List<NovelRecommendData>? = null,
)

data class NovelRecommendData(
    val id: String = "",
    val obj_id: String = "",
    val title: String = "",
    val cover: String = "",
    val url: String = "",
    val type: Int = 0,
    val sub_title: String = "",
    val status: String = "",
)

data class NovelLatest(
    val id: String = "",
    val status: String = "",
    val name: String = "",
    val authors: String = "",
    val cover: String = "",
    val types: List<String>? = null,
    val last_update_chapter_id: String = "",
    val last_update_volume_id: String = "",
    val last_update_volume_name: String = "",
    val last_update_chapter_name: String = "",
    val last_update_time: Long = 0,
)

data class NovelRank(
    val id: String = "",
    val last_update_time: Long = 0,
    val name: String = "",
    val types: List<String>? = null,
    val cover: String = "",
    val authors: String = "",
    val last_update_chapter_name: String = "",
    val top: Int = 0,
    val subscribe_amount: Int = 0,
)

data class NovelRankTag(
    val tag_id: Int = 0,
    val tag_name: String = "",
)

data class NovelFilter(
    val cover: String = "",
    val name: String = "",
    val authors: String = "",
    val id: String = "",
)

data class NovelFilterTag(
    val title: String = "",
    val items: List<NovelFilterItem>? = null,
)

data class NovelFilterItem(
    val tag_id: Int = 0,
    val tag_name: String = "",
)

data class NovelCategory(
    val tag_id: Int = 0,
    val title: String = "",
    val cover: String = "",
)

data class NovelSearch(
    val _biz: String = "",
    val addtime: Long = 0,
    val authors: String = "",
    val copyright: Int = 0,
    val cover: String = "",
    val hidden: Int = 0,
    val host_hits: Int = 0,
    val last_name: String = "",
    val status: Int = 0,
    val title: String = "",
    val types: String = "",
    val id: String = "",
)
