package com.ray650128.baseframework_test.net

import com.ray650128.baseframework_test.net.dataModels.Weather
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    // 取得氣象局開放資料
    @GET("api/v1/rest/datastore/F-C0032-001")
    fun getWeather(
        @Query("Authorization") auth: String,
        @Query("format") format: String,
        @Query("locationName") locationName: String,
        @Query("sort") sortBy: String
    ): Observable<Weather>
}