package com.example.dmzj_api.controller

import com.example.dmzj_api.service.impl.WebClientServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/common")
class CommonController {

    @Autowired
    lateinit var wcsi: WebClientServiceImpl

}