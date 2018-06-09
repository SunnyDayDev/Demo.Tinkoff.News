package me.sunnydaydev.tnews.domain.network

import io.reactivex.Single
import me.sunnydaydev.tnews.domain.network.models.NewsContentResponse
import me.sunnydaydev.tnews.domain.network.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal interface TinkoffApi {

    object Urls {
        const val BASE_URL = "https://api.tinkoff.ru/v1/"
    }

    @GET("news")
    fun getNews(): Single<NewsResponse>

    @GET("news_content")
    fun getNewsContent(@Query("id") id: String): Single<NewsContentResponse>

}