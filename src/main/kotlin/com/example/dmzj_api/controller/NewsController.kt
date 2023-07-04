package com.example.dmzj_api.controller

import com.example.dmzj_api.client.RClient
import com.example.dmzj_api.domain.Urls
import com.example.dmzj_api.proto.NewsList.NewsListResponse
import com.example.dmzj_api.service.NewsApiService
import com.example.dmzj_api.utils.RSAUtil
import com.example.dmzj_api.utils.getModels
import com.example.dmzj_api.utils.getResponseResult
import com.example.dmzj_api.vo.ResultVO
import okio.IOException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.converter.scalars.ScalarsConverterFactory

@RestController
@RequestMapping("/api/news")
class NewsController {
    // 新闻分类
    @RequestMapping("/tags")
    fun getNewsTags(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NewsApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNewsTags().execute()

            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 新闻轮播
    @RequestMapping("/recommend/head")
    fun getNewsHead(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NewsApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNewsHead().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 新闻列表
    @RequestMapping("/{sort}/{page}")
    fun getNewsList(@PathVariable sort: Int, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v4url, ScalarsConverterFactory.create())
        val apiService = client.create(NewsApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNewsList(sort, page).execute()
            val content = NewsListResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModels(content.dataList))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }
}