package com.example.dmzj_api.utils

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class RsaUtil {

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
        val key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK8nNR1lTnIfIes6oRWJNj3mB6OssDGx0uGMpgpbV" +
                "Cpf6+VwnuI2stmhZNoQcM417Iz7WqlPzbUmu9R4dEKmLGEEqOhOdVaeh9Xk2IPPjqIu5TbkLZRxkY3dJM1htbz57d/r" +
                "oesJLkZXqssfG5EJauNc+RcABTfLb4IiFjSMlTsnAgMBAAECgYEAiz/pi2hKOJKlvcTL4jpHJGjn8+lL3wZX+LeAHkX" +
                "DoTjHa47g0knYYQteCbv+YwMeAGupBWiLy5RyyhXFoGNKbbnvftMYK56hH+iqxjtDLnjSDKWnhcB7089sNKaEM9Ilil" +
                "6uxWMrMMBH9v2PLdYsqMBHqPutKu/SigeGPeiB7VECQQDizVlNv67go99QAIv2n/ga4e0wLizVuaNBXE88AdOnaZ0LO" +
                "TeniVEqvPtgUk63zbjl0P/pzQzyjitwe6HoCAIpAkEAxbOtnCm1uKEp5HsNaXEJTwE7WQf7PrLD4+BpGtNKkgja6f6F" +
                "4ld4QZ2TQ6qvsCizSGJrjOpNdjVGJ7bgYMcczwJBALvJWPLmDi7ToFfGTB0EsNHZVKE66kZ/8Stx+ezueke4S556Xpl" +
                "qOflQBjbnj2PigwBN/0afT+QZUOBOjWzoDJkCQClzo+oDQMvGVs9GEajS/32mJ3hiWQZrWvEzgzYRqSf3XVcEe7PaXS" +
                "d8z3y3lACeeACsShqQoc8wGlaHXIJOHTcCQQCZw5127ZGs8ZDTSrogrH73Kw/HvX55wGAeirKYcv28eauveCG7iyFR0" +
                "PFB/P/EDZnyb+ifvyEFlucPUI0+Y87F"

        val keyBytes = Base64.getDecoder().decode(key)
        val keySpec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")

        return keyFactory.generatePrivate(keySpec)
    }
}