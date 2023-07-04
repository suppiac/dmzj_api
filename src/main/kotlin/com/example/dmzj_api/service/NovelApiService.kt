package com.example.dmzj_api.service

import com.example.dmzj_api.entity.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface NovelApiService {
    // 小说推荐
    @GET("/novel/recommend.json")
    fun getNovelRecommend(): Call<List<NovelRecommend>>

    // 小说更新
    @GET("/novel/recentUpdate/{page}.json")
    fun getNovelLatest(@Path("page") page: Int): Call<List<NovelLatest>>

    // 小说分类
    @GET("/1/category.json")
    fun getNovelSort(): Call<List<NovelCategory>>

    // 小说排行
    @GET("/novel/rank/{tag}/{sort}/{page}.json")
    fun getNovelRank(@Path("tag") tag: Int, @Path("sort") sort: Int, @Path("page") page: Int): Call<List<NovelRank>>

    // 小说标签
    @GET("/novel/tag.json")
    fun getNovelTag(): Call<List<NovelRankTag>>

    // 小说筛选
    @GET("/novel/{tag}/{status}/{sort}/{page}.json")
    fun getFilterNovel(@Path("tag") tag: Int, @Path("status") status: Int, @Path("sort") sort: Int, @Path("page") page: Int): Call<List<NovelFilter>>

    // 筛选标签
    @GET("/novel/filter.json")
    fun getFilterTag(): Call<List<NovelFilterTag>>

    // 小说搜索
    @GET("/search/show/1/{query}/{page}.json")
    fun getNovelSearch(@Path("query") query: String, @Path("page") page: Int): Call<List<NovelSearch>>

    // 小说信息
    @GET("/novel/detail/{novelId}")
    fun getNovelDetail(@Path("novelId") novelId: Int): Call<String>

    // 小说章节
    @GET("/novel/chapter/{novelId}")
    fun getNovelChapter(@Path("novelId") novelId: Int): Call<String>

    // 小说内容
    @GET
    fun getNovelContent(@Url url: String): Call<String>
}