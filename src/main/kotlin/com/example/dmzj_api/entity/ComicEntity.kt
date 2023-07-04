package com.example.dmzj_api.entity

data class AuthorInfo(
    val nickname: String = "",
    val description: String = "",
    val cover: String = "",
    val data: List<Item>? = null,
) {
    data class Item(
        val id: String = "",
        val name: String = "",
        val cover: String = "",
        val status: String = "",
    )
}

data class ComicChapterDetail(
    val chapter_id: String = "",
    val comic_id: String = "",
    val title: String = "",
    val chapter_order: Int = 0,
    val direction: Int = 0,
    val page_url: List<String>? = null,
    val picnum: Int = 0,
    val comment_count: Int = 0,
)

data class ComicClassify(
    val id: String = "",
    val title: String = "",
    val authors: String = "",
    val status: String = "",
    val cover: String = "",
    val types: String = "",
    val last_updatetime: Long = 0,
    val num: String = "",
)

data class ComicClassifyCover(
    val code: Int = 0,
    val msg: String = "",
    val data: List<Item>? = null,
) {
    data class Item(
        val id: String = "",
        val title: String = "",
        val short_title: String = "",
        val create_time: Long = 0,
        val small_cover: String = "",
        val page_type: Int = 0,
        val sort: Int = 0,
        val page_url: String = "",
    )
}

data class ComicClassifyFilter(
    val title: String = "",
    val items: List<Item>? = null,
) {
    data class Item(
        val tag_id: String = "",
        val tag_name: String = "",
    )
}

data class ComicDownload(
    val id: String = "",
    val comic_id: String = "",
    val chapter_name: String = "",
    val chapter_order: Int = 0,
    val createtime: Long = 0,
    val folder: String = "",
    val page_url: List<String>? = null,
    val chapter_type: Int = 0,
    val chaptertype: Int = 0,
    val chapter_true_type: Int = 0,
    val chapter_num: Int = 0,
    val updatetime: Long = 0,
    val sum_pages: Int = 0,
    val sns_tag: Int = 0,
    val uid: String = "",
    val username: String = "",
    val translatorid: String = "",
    val translator: String = "",
    val link: String = "",
    val message: String = "",
    val download: String = "",
    val hidden: Int = 0,
    val direction: Int = 0,
    val filesize: Long = 0,
    val high_file_size: Int = 0,
    val picnum: Int = 0,
    val hit: Int = 0,
    val next_chap_id: String = "",
    val prev_chap_id: String = "",
    val comment_count: Int = 0,
)

data class ComicRecommendLike(
    val code: Int = 0,
    val msg: String = "",
    val data: Items? = null,
) {
    data class Items(
        val category_id: Int = 0,
        val title: String = "",
        val sort: Int = 0,
        val data: List<Item>? = null,
    ) {
        data class Item(
            val id: String = "",
            val title: String = "",
            val author: String = "",
            val status: String = "",
            val cover: String = "",
            val num: Long = 0,
        )
    }
}

data class ComicRecommendNew(
    val category_id: Int = 0,
    val title: String = "",
    val sort: Int = 0,
    val data: List<Item>? = null,
) {
    data class Item(
        val cover: String = "",
        val title: String = "",
        val sub_title: String = "",
        val type: Int = 0,
        val url: String = "",
        val obj_id: String = "",
        val status: String = "",
    )
}

data class ComicRecommendOther(
    val code: Int = 0,
    val msg: String = "",
    val data: ComicRecommendNew? = null,
)

data class ComicRelated(
    val author_comics: List<AuthorComics>? = null,
    val theme_comics: List<RelatedWork>? = null,
    val novel: List<RelatedWork>? = null,
) {
    data class AuthorComics(
        val author_name: String = "",
        val author_id: String = "",
        val data: List<RelatedWork>? = null,
    )

    data class RelatedWork(
        val id: String = "",
        val name: String = "",
        val cover: String = "",
        val status: String = "",
    )
}

data class ComicSearch(
    val _biz: String = "",
    val addtime: Long = 0,
    val alias_name: String = "",
    val authors: String = "",
    val copyright: Int = 0,
    val cover: String = "",
    val device_show: Int = 0,
    val grade: Int = 0,
    val hidden: Int = 0,
    val hot_hits: Int = 0,
    val last_name: String = "",
    val quality: Int = 0,
    val status: Int = 0,
    val title: String = "",
    val types: String = "",
    val id: String = "",
)

data class ComicSearchHot(
    val id: String = "",
    val name: String = "",
)

data class ComicSubject(
    val id: String = "",
    val title: String = "",
    val short_title: String = "",
    val create_time: Long = 0,
    val small_cover: String = "",
    val page_type: Int = 0,
    val sort: Int = 0,
    val page_url: String = "",
)

data class ComicSubjectDetail(
    val mobile_header_pic: String = "",
    val title: String = "",
    val page_url: String = "",
    val description: String = "",
    val comics: List<Item>? = null,
    val comment_amount: Int = 0,
) {
    data class Item(
        val cover: String = "",
        val recommend_brief: String = "",
        val recommend_reason: String = "",
        val id: String = "",
        val name: String = "",
        val alias_name: String = "",
    )
}