package com.example.dmzj_api.config

import io.undertow.server.DefaultByteBufferPool
import io.undertow.servlet.api.DeploymentInfo
import io.undertow.websockets.jsr.WebSocketDeploymentInfo
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.context.annotation.Configuration

@Configuration
class UndertowConfig: WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
    override fun customize(factory: UndertowServletWebServerFactory) {
        factory.addDeploymentInfoCustomizers(UndertowDeploymentInfoCustomizer { deploymentInfo: DeploymentInfo ->
            val webSocketDeploymentInfo = WebSocketDeploymentInfo()
            webSocketDeploymentInfo.setBuffers(DefaultByteBufferPool(false, 4096))
            deploymentInfo.addServletContextAttribute(
                "io.undertow.websockets.jsr.WebSocketDeploymentInfo",
                webSocketDeploymentInfo
            )
        })
    }
}