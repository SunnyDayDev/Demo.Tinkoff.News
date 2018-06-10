package me.sunnydaydev.tnews.domain.news

import io.reactivex.Completable
import io.reactivex.Observable
import me.sunnydaydev.tnews.domain.network.NewsNetworkService
import me.sunnydaydev.tnews.domain.network.models.NewsDto
import me.sunnydaydev.tnews.domain.news.db.NewsDao
import me.sunnydaydev.tnews.domain.news.db.NewsEntity
import me.sunnydaydev.tnews.domain.news.mapper.NewsMapperFactory
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NewsRepository {

    fun getNews(): Observable<List<News>>

    fun updateNews(): Completable

}

internal class NewsRepositoryImpl @Inject constructor(
        private val dao: NewsDao,
        private val networkService: NewsNetworkService,
        mappers: NewsMapperFactory
): NewsRepository {

    private val entityMapper: (NewsEntity) -> News = mappers.entityToPlain::map
    private val dtoMapper: (NewsDto) -> NewsEntity = mappers.dtoToEntity::map

    override fun getNews(): Observable<List<News>> = dao.allNews
            .toObservable()
            .map { it.map(entityMapper) }

    override fun updateNews(): Completable = networkService.getNews()
            .map { it.map(dtoMapper) }
            .doOnSuccess(dao::insert)
            .toCompletable()

}