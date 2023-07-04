package com.example.dmzj_api.controller

import com.example.dmzj_api.client.RClient
import com.example.dmzj_api.domain.Urls
import com.example.dmzj_api.proto.ComicDetailInfo.*
import com.example.dmzj_api.proto.ComicRankList.ComicRankListResponse
import com.example.dmzj_api.proto.ComicUpdateList.ComicUpdateListResponse
import com.example.dmzj_api.service.ComicApiService
import com.example.dmzj_api.utils.RSAUtil
import com.example.dmzj_api.utils.getModel
import com.example.dmzj_api.utils.getModels
import com.example.dmzj_api.utils.getResponseResult
import com.example.dmzj_api.vo.ResultVO
import okio.IOException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.converter.scalars.ScalarsConverterFactory

@RestController
@RequestMapping("/api/comic")
class ComicController {
    // 漫画轮播
    @RequestMapping("/recommend/carousel")
    fun getComicCarousel(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicCarousel().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画推荐
    @RequestMapping("/recommend/like")
    fun getComicLike(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicLike().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 首页国漫
    @RequestMapping("/recommend/domestic")
    fun getComicDomestic(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicDomestic().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画热门
    @RequestMapping("/recommend/hot")
    fun getComicHot(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicHot().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画订阅
    @RequestMapping("/category")
    fun getComicCategory(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicCategory().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画专题
    @RequestMapping("/subject/{page}")
    fun getComicSubject(@PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicSubject(page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画专题详情
    @RequestMapping("/subject/detail/{id}")
    fun getComicSubjectDetail(@PathVariable id: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicSubjectDetail(id).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画筛选
    @RequestMapping("/classify/filter")
    fun getComicFilter(): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicFilter().execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画筛选
    @RequestMapping("/classify/{filter}/{sort}/{page}")
    fun getComicClassify(@PathVariable filter: Int, @PathVariable sort: Int, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicClassify(filter, sort, page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画相关
    @RequestMapping("/related/{id}")
    fun getComicRelated(@PathVariable id: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicRelated(id).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画搜索
    @RequestMapping("/search/{query}/{page}")
    fun getSearch(@PathVariable query: String, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicSearch(query, page).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画搜索-类型
    @RequestMapping("/search/{type}")
    fun getComicTypeSearch(@PathVariable type: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicTypeSearch(type).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 更新列表
    @RequestMapping("/latest/{type}/{page}")
    fun getComicLatest(@PathVariable type: Int, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.v4url, ScalarsConverterFactory.create())
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicLatest(type, page).execute()
            val content = ComicUpdateListResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModels(content.dataList))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画详情
    @RequestMapping("/detail/{comicId}")
    fun getComicDetail(@PathVariable comicId: Int): ResultVO {
        val client = RClient().init(Urls.v4url, ScalarsConverterFactory.create())
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicDetail(comicId).execute()
            val content = ComicDetailResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModel(content.data))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }


    // 漫画章节详情
    @RequestMapping("/detail/chapter/{comicId}/{chapterId}")
    fun getComicChapterDetail(@PathVariable comicId: Int, @PathVariable chapterId: Int): ResultVO {
        val client = RClient().init(Urls.v4url, ScalarsConverterFactory.create())
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicChapterDetail(comicId, chapterId).execute()
            val content = ComicChapterResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModel(content.data))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 漫画排行
    @RequestMapping("/rank/{byTime}/{rankType}/{page}")
    fun getComicRank(@PathVariable byTime: Int, @PathVariable rankType: Int, @PathVariable page: Int): ResultVO {
        val client = RClient().init(Urls.oldV4, ScalarsConverterFactory.create())
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getComicRank(0, byTime, rankType, page).execute()
            val content = ComicRankListResponse.parseFrom(RSAUtil().decrypt(response.body()!!))
            result = getResponseResult(response, getModels(content.dataList))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    // 作者详情
    @RequestMapping("/author/{id}")
    fun getAuthorInfo(@PathVariable id: Int): ResultVO {
        val client = RClient().init(Urls.v3url)
        val apiService = client.create(ComicApiService::class.java)
        var result = ResultVO()

        try {
            val response = apiService.getAuthorDetail(id).execute()
            result = getResponseResult(response)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }
}