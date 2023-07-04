package com.example.dmzj_api.utils

import com.example.dmzj_api.domain.Key
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class RSAUtil {
    fun decrypt(content: String): ByteArray {
        val pk = getPrivateKey()
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, pk)
        val iS = ByteArrayInputStream(Base64.getDecoder().decode(content))
        val oS = ByteArrayOutputStream()
        val buffer = ByteArray(128)
        var len: Int

        do {
            len = iS.read(buffer)
            if (len != -1) {
                var block: ByteArray
                if (buffer.size == len) {
                    block = buffer
                } else {
                    block = ByteArray(len)
                    for (i in buffer.indices) {
                        block[i] = buffer[i]
                    }
                }
                oS.write(cipher.doFinal(block))
            } else {
                break
            }
        } while (true)

        return oS.toByteArray()
    }

    private fun getPrivateKey(): PrivateKey {
        val key = Key.pk
        val keyBytes = Base64.getDecoder().decode(key)
        val keySpec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")

        return keyFactory.generatePrivate(keySpec)
    }
}