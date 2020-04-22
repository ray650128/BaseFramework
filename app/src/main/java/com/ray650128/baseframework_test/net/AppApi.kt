package com.ray650128.baseframework_test.net

object AppApi : ApiClient() {
    private var apiService: ApiService? = null
    val instance: ApiService
        get() {
            if (apiService == null) {
                synchronized(ApiService::class.java) {
                    if (apiService == null) {
                        apiService = getRetrofit().create(ApiService::class.java)
                    }
                }
            }
            return apiService!!
        }
}