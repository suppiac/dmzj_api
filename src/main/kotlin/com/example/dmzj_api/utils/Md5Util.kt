package com.example.dmzj_api.utils

import java.security.MessageDigest

class Md5Util {

    fun encrypt(content: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val bytes: ByteArray = md5.digest(content.toByteArray())
        val stringBuilder = StringBuilder()
        bytes.forEach {
            var temp: String = Integer.toHexString((it.toInt() and 0xff))
            if (temp.length == 1) temp = "0$temp"
            stringBuilder.append(temp)
        }

        return stringBuilder.toString()
    }
}