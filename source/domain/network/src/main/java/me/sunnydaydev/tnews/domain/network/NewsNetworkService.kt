package me.sunnydaydev.tnews.domain.network

import io.reactivex.Single
import me.sunnydaydev.tnews.domain.network.models.*
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NewsNetworkService {

    fun getNews(): Single<List<NewsDto>>

    fun getNewsContent(id: String): Single<NewsContentDto>

}

internal class NewsNetworkServiceImpl @Inject constructor(
        private val api: TinkoffApi
): NewsNetworkService {

    override fun getNews(): Single<List<NewsDto>> {
        return api.getNews().checkResponse()
    }

    override fun getNewsContent(id: String): Single<NewsContentDto> {
        return api.getNewsContent(id).checkResponse()
    }

}
