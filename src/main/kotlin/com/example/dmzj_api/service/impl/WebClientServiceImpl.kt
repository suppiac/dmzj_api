package com.example.dmzj_api.service.impl

import com.example.dmzj_api.service.WebClientService
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
class WebClientServiceImpl: WebClientService {
    val webClient = WebClient.builder().build()

    override fun getRequest(url: String): WebClient.ResponseSpec {
        return webClient.get().uri(url).retrieve()
    }

    override fun postRequest(url: String, body: Any): WebClient.ResponseSpec {
        return webClient.post().uri(url).bodyValue(body).retrieve()
    }
}