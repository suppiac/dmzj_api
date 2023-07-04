package com.example.dmzj_api.service

import com.example.dmzj_api.entity.NewsBanner
import com.example.dmzj_api.entity.NewsCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApiService {
    // 新闻分类
    @GET("/article/category.json")
    fun getNewsTags(): Call<List<NewsCategory>>

    // 新闻轮播
    @GET("/article/recommend/header.json")
    fun getNewsHead(): Call<NewsBanner>

    // 新闻列表
    @GET("/news/list/{sort}/{if (sort == 0) 2 else 3}/{page}")
    fun getNewsList(@Path("sort") sort: Int, @Path("page") page: Int): Call<String>
}