package com.example.dmzj_api.service

import org.springframework.web.reactive.function.client.WebClient

interface WebClientService {
    fun getRequest(url: String): WebClient.ResponseSpec
    fun postRequest(url: String, body: Any): WebClient.ResponseSpec
}