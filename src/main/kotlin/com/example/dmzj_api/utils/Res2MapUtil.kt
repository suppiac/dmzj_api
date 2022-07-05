package com.example.dmzj_api.utils

import com.example.dmzj_api.pojo.ComicDetailRes.ComicDetailChapterInfoResponse
import com.example.dmzj_api.pojo.ComicDetailRes.ComicDetailInfoResponse
import com.example.dmzj_api.pojo.ComicUpdateListRes
import com.example.dmzj_api.pojo.NewsListRes.NewsListItemResponse
import com.example.dmzj_api.pojo.NovelChapterRes.NovelChapterVolumeResponse
import com.example.dmzj_api.pojo.NovelDetailRes.NovelDetailInfoResponse

class Res2MapUtil {

    // 小说详细信息NovelDetailInfoResponse类转hashmap
    fun novelDetail2Map(data: NovelDetailInfoResponse): HashMap<String, Any> {
        val res = HashMap<String, Any>()
        var volumeList = listOf<HashMap<String, Any>>()
        res["novelId"] = data.novelId
        res["name"] = data.name
        res["zone"] = data.zone
        res["status"] = data.status
        res["lastUpdateVolumeName"] = data.lastUpdateVolumeName
        res["lastUpdateChapterName"] = data.lastUpdateChapterName
        res["lastUpdateVolumeId"] = data.lastUpdateVolumeId
        res["lastUpdateChapterId"] = data.lastUpdateChapterId
        res["lastUpdateTime"] = data.lastUpdateTime
        res["cover"] = data.cover
        res["hotHits"] = data.hotHits
        res["introduction"] = data.introduction
        res["typesList"] = data.typesList
        res["authors"] = data.authors
        res["firstLetter"] = data.firstLetter
        res["subscribeNum"] = data.subscribeNum
        res["redisUpdateTime"] = data.redisUpdateTime
        data.volumeList.forEach {
            val volume = HashMap<String, Any>()
            volume["volumeId"] = it.volumeId
            volume["lnovelId"] = it.lnovelId
            volume["volumeName"] = it.volumeName
            volume["volumeOrder"] = it.volumeOrder
            volume["addtime"] = it.addtime
            volume["sumChapters"] = it.sumChapters
            volumeList = volumeList.plus(volume)
        }
        res["volumeList"] = volumeList

        return res
    }

    // 小说章节信息NovelChapterResponse类转hashmap
    fun novelChapter2Map(data: List<NovelChapterVolumeResponse>): List<HashMap<String, Any>> {
        var res = listOf<HashMap<String, Any>>()
        data.forEach{
            val volume = HashMap<String, Any>()
            var chapterList = listOf<HashMap<String, Any>>()
            volume["volumeId"] = it.volumeId
            volume["volumeName"] = it.volumeName
            volume["volumeOrder"] = it.volumeOrder
            it.chaptersList.forEach { s ->
                val chapter = HashMap<String, Any>()
                chapter["chapterId"] = s.chapterId
                chapter["chapterName"] = s.chapterName
                chapter["chapterOrder"] = s.chapterOrder
                chapterList = chapterList.plus(chapter)
            }
            volume["chaptersList"] = chapterList
            res = res.plus(volume)
        }

        return res
    }

    // 新闻列表NewsListResponse类转hashmap
    fun newsList2Map(data: List<NewsListItemResponse>): List<HashMap<String, Any>> {
        var res = listOf<HashMap<String, Any>>()
        data.forEach {
            val news = HashMap<String, Any>()
            news["articleId"] = it.articleId
            news["title"] = it.title
            news["fromName"] = it.fromName
            news["fromUrl"] = it.fromUrl
            news["createTime"] = it.createTime
            news["isForeign"] = it.isForeign
            news["foreignUrl"] = it.foreignUrl
            news["intro"] = it.intro
            news["authorId"] = it.authorId
            news["status"] = it.status
            news["rowPicUrl"] = it.rowPicUrl
            news["colPicUrl"] = it.colPicUrl
            news["qchatShow"] = it.qchatShow
            news["pageUrl"] = it.pageUrl
            news["commentAmount"] = it.commentAmount
            news["authorUid"] = it.authorId
            news["cover"] = it.cover
            news["nickname"] = it.nickname
            news["moodAmount"] = it.moodAmount
            res = res.plus(news)
        }

        return res
    }

    // 漫画详细信息ComicDetailInfoResponse类转hashmap
    fun comicDetail2Map(data: ComicDetailInfoResponse): HashMap<String, Any> {
        val res = HashMap<String, Any>()
        var authorsList = listOf<HashMap<String, Any>>()
        var chaptersList = listOf<HashMap<String, Any>>()
        var statusList = listOf<HashMap<String, Any>>()
        var typesList = listOf<HashMap<String, Any>>()
        data.authorsList.forEach {
            val authors = HashMap<String, Any>()
            authors["tagIid"] = it.tagId
            authors["tagName"] = it.tagName
            authorsList = authorsList.plus(authors)
        }
        data.chaptersList.forEach {
            var chapters = HashMap<String, Any>()
            var sectionsList = listOf<HashMap<String, Any>>()
            it.dataList.forEach { s ->
                val sections = HashMap<String, Any>()
                sections["chapterId"] = s.chapterId
                sections["chapterOrder"] = s.chapterOrder
                sections["chapterTitle"] = s.chapterTitle
                sections["filesize"] = s.filesize
                sections["updatetime"] = s.updatetime
                sectionsList = sectionsList.plus(sections)
            }
            chapters["data"] = sectionsList
            chapters["title"] = it.title
            chaptersList = chaptersList.plus(chapters)
        }

        data.statusList.forEach {
            val status = HashMap<String, Any>()
            status["tagId"] = it.tagId
            status["tagName"] = it.tagName
            statusList = statusList.plus(status)
        }

        data.typesList.forEach {
            val types = HashMap<String, Any>()
            types["tagId"] = it.tagId
            types["tagName"] = it.tagName
            typesList = typesList.plus(types)
        }

        res["authorsList"] = authorsList
        res["chaptersList"] = chaptersList
        res["comicPy"] = data.comicPy
        res["cover"] = data.cover
        res["description"] = data.description
        res["direction"] = data.direction
        res["firstLetter"] = data.firstLetter
        res["hitNum"] = data.hitNum
        res["hotNum"] = data.hotNum
        res["id"] = data.id
        res["isLong"] = data.islong
        res["lastUpdateChapterId"] = data.lastUpdateChapterId
        res["lastUpdateChapterName"] = data.lastUpdateChapterName
        res["lastUpdatetime"] = data.lastUpdatetime
        res["statusList"] = statusList
        res["subscribeNum"] = data.subscribeNum
        res["title"] = data.title
        res["typesList"] = typesList
        res["isDmzj"] = data.isDmzj
        res["copyright"] = data.copyright
        res["hidden"] = data.hidden
        res["uid"] = data.uid
        res["isLock"] = data.isLock
        res["isNeedLogin"] = data.isNeedLogin
        res["isHideChapter"] = data.isHideChapter

        return res
    }

    // 漫画章节信息ComicUpdateListItemResponse类转hashmap
    fun comicChapter2Map(data: List<ComicUpdateListRes.ComicUpdateListItemResponse>): List<HashMap<String, Any>> {
        var res = listOf(HashMap<String, Any>())
        data.forEach {
            val section = HashMap<String, Any>()
            section["comicId"] = it.comicId
            section["title"] = it.title
            section["isLong"] = it.islong
            section["authors"] = it.authors
            section["types"] = it.types
            section["cover"] = it.cover
            section["status"] = it.status
            section["lastUpdateChapterId"] = it.lastUpdateChapterId
            section["lastUpdateChapterName"] = it.lastUpdateChapterName
            section["lastUpdatetime"] = it.lastUpdatetime
            res = res.plus(section)
        }

        return res
    }

    //
    fun comicChapter2Map() {

    }
}