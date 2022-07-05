package com.example.dmzj_api.controller

import com.example.dmzj_api.pojo.*
import com.example.dmzj_api.service.impl.WebClientServiceImpl
import com.example.dmzj_api.utils.Md5Util
import com.example.dmzj_api.utils.Res2MapUtil
import com.example.dmzj_api.utils.RsaUtil
import com.example.dmzj_api.vo.ResultVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/novel")
class NovelController {

    @Autowired
    lateinit var wcsi: WebClientServiceImpl

    // 推荐
    @RequestMapping("/recommend")
    fun recommend(): ResultVO{
        val url = "${v3url}/novel/recommend.json"
        val data = wcsi.getRequest(url).toEntityList(NovelRecommend().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 更新
    @RequestMapping("/latest/{page}")
    fun latest(@PathVariable page: String): ResultVO {
        val url = "${v3url}/novel/recentUpdate/${page}.json"
        val data = wcsi.getRequest(url).toEntityList(NovelLatest().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 分类
    @RequestMapping("/sort")
    fun sort(): ResultVO {
        val url = "${v3url}/1/category.json"
        val data = wcsi.getRequest(url).toEntityList(NovelCategory().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 排行
    @RequestMapping("/rank/{tag}/{sort}/{page}")
    fun rank(
        @PathVariable tag: String,
        @PathVariable sort: String,
        @PathVariable page: String
    ): ResultVO {
        val url = "${v3url}/novel/rank/${tag}/${sort}/${page}.json"
        val data = wcsi.getRequest(url).toEntityList(NovelRank().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 排行分类
    @RequestMapping("/rank/tag")
    fun rankTag(): ResultVO {
        val url = "${v3url}/novel/tag.json"
        val data = wcsi.getRequest(url).toEntityList(NovelRankTag().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 筛选
    @RequestMapping("/filter/{tag}/{status}/{sort}/{page}")
    fun filter(
        @PathVariable tag: String,
        @PathVariable status: String,
        @PathVariable sort: String,
        @PathVariable page: String
    ): ResultVO {
        val url = "${v3url}/novel/${tag}/${status}/${sort}/${page}.json"
        val data = wcsi.getRequest(url).toEntityList(NovelFilter().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 筛选分类
    @RequestMapping("/filter/tag")
    fun filterTag(): ResultVO {
        val url = "${v3url}/novel/filter.json"
        val data = wcsi.getRequest(url).toEntityList(NovelFilterTag().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 搜索
    @RequestMapping("/search/{query}/{page}")
    fun search(
        @PathVariable query: String,
        @PathVariable page: String
    ): ResultVO {
        val url = "${v3url}/search/show/1/${query}/${page}.json"
        val data = wcsi.getRequest(url).toEntityList(NovelSearch().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

    // 详细信息
    @RequestMapping("/detail/{novelId}")
    fun detail(@PathVariable novelId: String): ResultVO {
        val url = "${v4url}/novel/detail/${novelId}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val content = RsaUtil().decrypt(it.body!!)
            val temp = NovelDetailRes.NovelDetailResponse.parseFrom(content)
            val res = Res2MapUtil().novelDetail2Map(temp.data)
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = res
        }

        return result
    }

    // 章节列表
    @RequestMapping("/chapter/{novelId}")
    fun chapter(@PathVariable novelId: String): ResultVO {
        val url = "${v4url}/novel/chapter/${novelId}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val content = RsaUtil().decrypt(it.body!!)
            val temp = NovelChapterRes.NovelChapterResponse.parseFrom(content)
            val res = Res2MapUtil().novelChapter2Map(temp.dataList)
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = res
        }

        return result
    }

    // 获取小说内容
    @RequestMapping("/{volumeId}/{chapterId}")
    fun content(@PathVariable volumeId: String, @PathVariable chapterId: String): ResultVO {
        val path = "/lnovel/${volumeId}_${chapterId}.txt"
        val t = System.currentTimeMillis() / 1000
        val key = Md5Util().encrypt(novelContentKey + path + t)
        val url = "${jurisdiction}${path}?t=${t}&k=${key}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body
        }

        return result
    }

}