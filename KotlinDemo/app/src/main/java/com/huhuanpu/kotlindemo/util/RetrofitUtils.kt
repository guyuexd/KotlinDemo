package com.huhuanpu.kotlindemo.util

import com.huhuanpu.kotlindemo.api.RetrofitService
import com.huhuanpu.kotlindemo.constant.Constant
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

/**
 * Created by huhuanpu on 19-9-16
 */
class RetrofitUtils<T> {
    companion object{
        /**
         * creat Retrofit
         */
        fun create(url: String): Retrofit {
//            // 日志显示级别
//            val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
//            // 新建log拦截器
//            val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
//                message -> loge("RetrofitUtils", "OkHttp: " + message)
//            })
//            loggingInterceptor.level = level
            // okHttpClientBuilder
            val okHttpClientBuilder = OkHttpClient().newBuilder()

            okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(10, TimeUnit.SECONDS)
            // okHttp添加拦截器loggingInterceptor
            // okHttpClientBuilder.addInterceptor(loggingInterceptor)

            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        val retrofitService: RetrofitService = RetrofitUtils.getService(Constant.REQUEST_BASE_URL, RetrofitService::class.java)

        /**
         * get service api
         */
        fun<T> getService(url: String, service:Class<T>): T {
            return create(url).create(service)
        }
    }
}