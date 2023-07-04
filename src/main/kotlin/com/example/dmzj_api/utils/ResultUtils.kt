package com.example.dmzj_api.utils

import com.example.dmzj_api.vo.ResultVO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import retrofit2.Response

// 获取响应结果
fun <T> getResponseResult(response: Response<T>): ResultVO {
    return if (response.isSuccessful) {
        ResultVO(response.code(), "request success", response.body())
    } else {
        ResultVO(response.code(), "request failure")
    }
}

// 获取解密响应结果
fun <T, S> getResponseResult(response: Response<T>, data: S?): ResultVO {
    return if (response.isSuccessful) {
        ResultVO(response.code(), "request success", data)
    } else {
        ResultVO(response.code(), "request failure")
    }
}

// 将任意类型数据转换为Json字符串
private fun <T> getJson(data: T): String {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val jsonElement = JsonParser.parseString(Gson().toJson(data))

    return gson.toJson(jsonElement)
}

// 将数据转换为对象
fun <T> getModel(data: T): Any {
    val json = getJson(data)

    return Gson().fromJson(json, object : TypeToken<Any>() {}.type)
}

// 将数据转换为对象列表
fun <T> getModels(data: T): List<Any> {
    val json = getJson(data)

    return Gson().fromJson(json, object : TypeToken<List<Any>>() {}.type)
}