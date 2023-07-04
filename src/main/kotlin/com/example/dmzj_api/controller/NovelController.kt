package com.example.dmzj_api.controller

import com.example.dmzj_api.client.RClient
import com.example.dmzj_api.domain.Key
import com.example.dmzj_api.domain.Urls
import com.example.dmzj_api.proto.NovelChapter.NovelChapterResponse
import com.example.dmzj_api.proto.NovelDetail.*
import com.example.dmzj_api.service.NovelApiService
import com.example.dmzj_api.utils.*
import com.example.dmzj_api.vo.ResultVO
import okio.IOException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.converter.scalars.ScalarsConverterFactory

@RestController
@RequestMapping("/api/novel")
class NovelController {
    // 小说推荐
    @RequestMapping("/recommend")
    fun getNovelRecommend(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelRecommend().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说更新
    @RequestMapping("/latest/{page}")
    fun getNovelLatest(@PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelLatest(page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说分类
    @RequestMapping("/sort")
    fun getNovelSort(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelSort().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说排行
    @RequestMapping("/rank/{tag}/{sort}/{page}")
    fun getNovelRank(@PathVariable tag: Int, @PathVariable sort: Int, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelRank(tag, sort, page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说标签
    @RequestMapping("/rank/tag")
    fun getNovelTag(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelTag().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说筛选
    @RequestMapping("/filter/{tag}/{status}/{sort}/{page}")
    fun getFilterNovel(
        @PathVariable tag: Int,
        @PathVariable status: Int,
        @PathVariable sort: Int,
        @PathVariable page: Int
    ): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getFilterNovel(tag, status, sort, page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 筛选标签
    @RequestMapping("/filter/tag")
    fun getFilterTag(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getFilterTag().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说搜索
    @RequestMapping("/search/{query}/{page}")
    fun getNovelSearch(@PathVariable query: String, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelSearch(query, page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说信息
    @RequestMapping("/detail/{novelId}")
    fun getNovelDetail(@PathVariable novelId: Int): ResultVO {
        val client = RClient().init(Urls.v4url, ScalarsConverterFactory.create())
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelDetail(novelId).execute()
            val content = NovelDetailResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModel(content.data))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说章节
    @RequestMapping("/chapter/{novelId}")
    fun getNovelChapter(@PathVariable novelId: Int): ResultVO {
        val client = RClient().init(Urls.v4url, ScalarsConverterFactory.create())
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelChapter(novelId).execute()
            val content = NovelChapterResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModels(content.dataList))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 小说内容
    @RequestMapping("/{chapterId}/{volumeId}")
    fun getNovelContent(@PathVariable chapterId: Int, @PathVariable volumeId: Int): ResultVO {
        val path = "/lnovel/${volumeId}_${chapterId}.txt"
        val time = System.currentTimeMillis() / 1000
        val key = MD5Util().encrypt(Key.nck + path + time)
        val url = "${path}?t=${time}&k=${key}"

        val client = RClient().init(Urls.vjurl, ScalarsConverterFactory.create())
        val apiService = client.create(NovelApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getNovelContent(url).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }
}