package com.example.dmzj_api.controller

import com.example.dmzj_api.pojo.*
import com.example.dmzj_api.service.impl.WebClientServiceImpl
import com.example.dmzj_api.utils.Res2MapUtil
import com.example.dmzj_api.utils.RsaUtil
import com.example.dmzj_api.vo.ResultVO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comic")
class ComicController {

    @Autowired
    lateinit var wcsi: WebClientServiceImpl

    // 首页轮播
    @RequestMapping("/recommend/carousel")
    fun recommendCarousel(): ResultVO {
        val url = "${v3url}/recommend_new.json"
        val data = wcsi.getRequest(url).toEntityList(ComicRecommendNew().javaClass).block()
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

    // 首页猜你喜欢
    @RequestMapping("/recommend/like")
    fun recommendLike(): ResultVO {
        val url = "${v3url}/recommend/batchUpdate?category_id=50"
        val data = wcsi.getRequest(url).toEntity(ComicRecommendLike().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body!!.data
        }

        return result
    }

    // 首页国漫
    @RequestMapping("/recommend/domestic")
    fun recommendDomestic(): ResultVO {
        val url = "${v3url}/recommend/batchUpdate?category_id=52"
        val data = wcsi.getRequest(url).toEntity(ComicRecommendOther().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body!!.data
        }

        return result
    }

    // 首页热门
    @RequestMapping("/recommend/hot")
    fun recommendHot(): ResultVO {
        val url = "${v3url}/recommend/batchUpdate?category_id=54"
        val data = wcsi.getRequest(url).toEntity(ComicRecommendOther().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body!!.data
        }

        return result
    }

    // 目录
    @RequestMapping("/category")
    fun category(): ResultVO {
        val url = "${v3url}/0/category_with_level.json"
        val data = wcsi.getRequest(url).toEntity(ComicClassifyCover().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body!!.data
        }

        return result
    }

    // 专题
    @RequestMapping("/subject/{page}")
    fun subject(@PathVariable page: String): ResultVO {
        val url = "${v3url}/subject/0/${page}.json"
        val data = wcsi.getRequest(url).toEntity(ComicTopic().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body!!.data
        }

        return result
    }

    // 专题详情
    @RequestMapping("/detail/subject/{id}")
    fun detailSubject(@PathVariable id: String): ResultVO {
        val url = "${v3url}/subject/${id}.json"
        val data = wcsi.getRequest(url).toEntity(ComicTopicInfo().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = it.body!!.data
        }

        return result
    }

    // 筛选
    @RequestMapping("/classify/{filter}/{sort}/{page}")
    fun classify(
        @PathVariable filter: String,
        @PathVariable sort: String,
        @PathVariable page: String
    ): ResultVO {
        val url = "${v3url}/classifyWithLevel/${filter}/${sort}/${page}.json"
        val data = wcsi.getRequest(url).toEntityList(ComicClassify().javaClass).block()
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

    // 相关
    @RequestMapping("/related/{id}")
    fun related(@PathVariable id: String): ResultVO {
        val url =  "${v3url}/v3/comic/related/${id}.json"
        val data = wcsi.getRequest(url).toEntity(ComicRelated().javaClass).block()
        val result = ResultVO()
        data?: run {
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
    fun search(@PathVariable query: String, @PathVariable page: String): ResultVO {
        val url = "${v3url}/search/show/0/${query}/${page}.json"
        val data = wcsi.getRequest(url).toEntityList(ComicSearchResult().javaClass).block()
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

    // 类别搜索
    @RequestMapping("/search/{type}")
    fun searchType(@PathVariable type: String): ResultVO {
        val url = "${v3url}/search/hot/${type}.json"
        val data = wcsi.getRequest(url).toEntityList(ComicSearchHot().javaClass).block()
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

    // 章节详情
    @RequestMapping("/detail/chapter/{comicId}/{chapterId}")
    fun detailChapter(@PathVariable comicId: String, @PathVariable chapterId: String): ResultVO {
        val url = "${v3url}/chapter/${comicId}/${chapterId}.json"
        val data = wcsi.getRequest(url).toEntity(ComicChapterInfo().javaClass).block()
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

    // 章节详情（下载）
    @RequestMapping("/detail/chapter_info/{comicId}/{chapterId}")
    fun detailChapterInfo(@PathVariable comicId: String, @PathVariable chapterId: String): ResultVO {
        val url = "${vmurl}/chapinfo/${comicId}/${chapterId}.html"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val om = ObjectMapper()
            val res: ComicDownload = om.readValue(it.body, ComicDownload().javaClass)
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = res
        }

        return result
    }

    // 筛选类别
    @RequestMapping("/classify/filter")
    fun filterTag(): ResultVO {
        val url = "${v3url}/classify/filter.json"
        val data = wcsi.getRequest(url).toEntityList(ComicClassifyFilter().javaClass).block()
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

    // 详细信息与章节
    @RequestMapping("/detail/{comicId}")
    fun detail(@PathVariable comicId: String): ResultVO {
        val url = "${v4url}/comic/detail/${comicId}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val content = RsaUtil().decrypt(it.body!!)
            val temp = ComicDetailRes.ComicDetailResponse.parseFrom(content)
            val res = Res2MapUtil().comicDetail2Map(temp.data)
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = res
        }

        return result
    }

    // 更新列表
    @RequestMapping("/latest/{type}/{page}")
    fun latest(
        @PathVariable type: String,
        @PathVariable page: String
    ): ResultVO {
        val url = "${v4url}/comic/update/list/${type}/${page}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val content = RsaUtil().decrypt(it.body!!)
            val temp = ComicUpdateListRes.ComicUpdateListResponse.parseFrom(content)
            val res = Res2MapUtil().comicChapter2Map(temp.dataList)
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = res
        }

        return result
    }

    // 排行（未完成）
    @RequestMapping("/rank/{tagId}/{rankType}/{page}")
    fun rank(
        @PathVariable tagId: String,
        @PathVariable rankType: String,
        @PathVariable page: String
    ): ResultVO {
        val time = System.currentTimeMillis() / 1000
        val url = "${v4url}/comic/rank/list?tag_id=${tagId}&by_time=${time}&rank_type=${rankType}&page=${page}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val content = RsaUtil().decrypt(it.body!!)
            val temp = ComicRankListRes.ComicRankListResponse.parseFrom(content)
            println(temp.dataList)
        }

        return result
    }

    // 作者相关
    @RequestMapping("/author/{id}")
    fun authorInfo(@PathVariable id: String): ResultVO {
        val url = "${v3url}/UCenter/author/${id}.json"
        val data = wcsi.getRequest(url).toEntity(AuthorInfo().javaClass).block()
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