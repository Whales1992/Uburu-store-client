package com.android.uburu.repository

class ResponseObjectMapper(private val status: Boolean, private val responseObject: Any?) {

    fun responseObject(): Any? {
        return responseObject
    }

    fun isSuccess(): Boolean {
        return status
    }
}