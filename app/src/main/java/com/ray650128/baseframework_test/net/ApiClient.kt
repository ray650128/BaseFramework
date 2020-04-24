package com.ray650128.baseframework_test.net

import com.ray650128.baseframework_test.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiClient {
    companion object {
        private var retrofit: Retrofit? = null

        // 參數設定
        private const val API_BASE_URL = "https://opendata.cwb.gov.tw/"
        private const val TIME_OUT = 10L

        // 雙重檢測機制
        @JvmStatic
        fun getRetrofit(): Retrofit {
            if (retrofit == null) {    // 雙重檢測機制
                synchronized(ApiClient::class.java) {
                    if (retrofit == null) {    // 雙重檢測機制
                        val builder = OkHttpClient().newBuilder()
                        // 透過攔截器加入 Header 參數
                        /* gradle 需加入：
                           kotlinOptions {
                               jvmTarget = JavaVersion.VERSION_1_8
                           }
                        */
                        //builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                        //    val request = chain.request()
                        //            .newBuilder()
                        //            .addHeader("Content-Type", "application/json")
                        //            .addHeader("Authorization", "Bearer JWT_TOKEN")
                        //            .addHeader("Accept", "*/*")
                        //    .build()
                        //chain.proceed(request)
                        //})
                        // 設定 Timeout
                        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
                        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)

                        // 顯示 debug 訊息，方便除錯
                        if (BuildConfig.DEBUG) {
                            val interceptor = HttpLoggingInterceptor()
                            interceptor.level = HttpLoggingInterceptor.Level.BODY
                            builder.addInterceptor(interceptor)
                        }
                        retrofit = Retrofit.Builder()
                                .baseUrl(API_BASE_URL)
                                .client(builder.build())
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build()
                    }
                }
            }
            return retrofit!!
        }
    }
}