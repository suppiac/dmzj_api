package com.example.dmzj_api.controller

import com.example.dmzj_api.pojo.NewsBanner
import com.example.dmzj_api.pojo.NewsCategory
import com.example.dmzj_api.pojo.NewsListRes
import com.example.dmzj_api.pojo.v3url
import com.example.dmzj_api.pojo.v4url
import com.example.dmzj_api.service.impl.WebClientServiceImpl
import com.example.dmzj_api.utils.Res2MapUtil
import com.example.dmzj_api.utils.RsaUtil
import com.example.dmzj_api.vo.ResultVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/news")
class NewsController {

    @Autowired
    lateinit var wcsi: WebClientServiceImpl

    // 新闻分类
    @RequestMapping("/tag")
    fun newsTag(): ResultVO {
        val url = "${v3url}/article/category.json"
        val data = wcsi.getRequest(url).toEntityList(NewsCategory().javaClass).block()
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

    // 新闻轮播
    @RequestMapping("/recommend/head")
    fun newsHead(): ResultVO {
        val url = "${v3url}/article/recommend/header.json"
        val data = wcsi.getRequest(url).toEntity(NewsBanner().javaClass).block()
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

    // 新闻列表
    @RequestMapping("/{sort}/{page}")
    fun newsList(@PathVariable sort: Int, @PathVariable page: String): ResultVO {
        val url = "${v4url}/news/list/${sort}/${if (sort == 0) 2 else 3}/${page}"
        val data = wcsi.getRequest(url).toEntity(String().javaClass).block()
        val result = ResultVO()
        data?:run {
            result.code = 404
            result.msg = "request failure"
        }
        data?.let {
            val content = RsaUtil().decrypt(it.body!!)
            val temp = NewsListRes.NewsListResponse.parseFrom(content)
            val res = Res2MapUtil().newsList2Map(temp.dataList)
            result.code = it.statusCodeValue
            result.msg = "request success"
            result.res = res
        }

        return result
    }
}