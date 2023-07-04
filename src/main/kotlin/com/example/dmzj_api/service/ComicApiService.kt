package com.example.dmzj_api.service

import com.example.dmzj_api.entity.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicApiService {
    // 漫画轮播
    @GET("/recommend_new.json")
    fun getComicCarousel(): Call<List<ComicRecommendNew>>

    // 漫画推荐
    @GET("/recommend/batchUpdate?category_id=50")
    fun getComicLike(): Call<ComicRecommendLike>

    // 首页国漫
    @GET("/recommend/batchUpdate?category_id=52")
    fun getComicDomestic(): Call<ComicRecommendOther>

    // 漫画热门
    @GET("/recommend/batchUpdate?category_id=54")
    fun getComicHot(): Call<ComicRecommendOther>

    // 漫画订阅
    @GET("/0/category_with_level.json")
    fun getComicCategory(): Call<ComicClassifyCover>

    // 漫画专题
    @GET("/subject/0/{page}.json")
    fun getComicSubject(@Path("page") page: Int): Call<List<ComicSubject>>

    // 漫画专题详情
    @GET("/subject/{id}.json")
    fun getComicSubjectDetail(@Path("id") id: Int): Call<ComicSubjectDetail>

    // 筛选类别
    @GET("/classify/filter.json")
    fun getComicFilter(): Call<List<ComicClassifyFilter>>

    // 漫画筛选
    @GET("/classifyWithLevel/{filter}/{sort}/{page}.json")
    fun getComicClassify(@Path("filter") filter: Int, @Path("sort") sort: Int, @Path("page") page: Int): Call<List<ComicClassify>>

    // 漫画相关
    @GET("/v3/comic/related/{id}.json")
    fun getComicRelated(@Path("id") id: Int): Call<ComicRelated>

    // 漫画搜索
    @GET("/search/show/0/{query}/{page}.json")
    fun getComicSearch(@Path("query") query: String, @Path("page") page: Int): Call<List<ComicSearch>>

    // 漫画搜索-类型
    @GET("/search/hot/{type}.json")
    fun getComicTypeSearch(@Path("type") type: Int): Call<List<ComicSearchHot>>

    // 更新列表
    @GET("/comic/update/list/{type}/{page}")
    fun getComicLatest(@Path("type") type: Int, @Path("page") page: Int): Call<String>

    // 漫画详情
    @GET("/comic/detail/{comicId}")
    fun getComicDetail(@Path("comicId") comicId: Int): Call<String>

    // 漫画章节详情
    @GET("/comic/chapter/{comicId}/{chapterId}")
    fun getComicChapterDetail(@Path("comicId") comicId: Int, @Path("chapterId") chapterId: Int): Call<String>

    // 漫画排行
    @GET("/comic/rank/list")
    fun getComicRank(@Query("tag_id") tagId: Int, @Query("by_time") byTime: Int, @Query("rank_type") rankType: Int, @Query("page") page: Int): Call<String>

    // 作者详情
    @GET("/UCenter/author/{id}.json")
    fun getAuthorDetail(@Path("id") id: Int): Call<AuthorInfo>
}