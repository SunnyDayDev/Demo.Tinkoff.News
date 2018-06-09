package me.sunnydaydev.tnews.domain.news

import io.reactivex.Single
import me.sunnydaydev.tnews.domain.network.NewsNetworkService
import me.sunnydaydev.tnews.domain.network.models.NewsDto
import java.util.*
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NewsRepository {

    fun getNews(): Single<List<News>>

}

internal class NewsRepositoryImpl @Inject constructor(
        private val newsNetworkService: NewsNetworkService
): NewsRepository {

    override fun getNews(): Single<List<News>> = newsNetworkService.getNews()
            .map { it.map(::map) }

    private fun map(dto: NewsDto) = News(
            id = dto.id,
            name = dto.name,
            text = dto.text,
            publicationDate = Date(dto.publicationDate.milliseconds),
            bankInfoTypeId = dto.bankInfoTypeId
    )

}