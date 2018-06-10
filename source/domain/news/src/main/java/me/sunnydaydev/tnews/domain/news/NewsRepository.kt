package me.sunnydaydev.tnews.domain.news

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
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

    fun getNewsContent(id: String): Single<NewsContent>

    fun updateNews(): Completable

}

internal class NewsRepositoryImpl @Inject constructor(
        private val dao: NewsDao,
        private val networkService: NewsNetworkService,
        mappers: NewsMapperFactory
): NewsRepository {

    private val entityMapper: (NewsEntity) -> News = mappers.newsEntityToPlain::map
    private val dtoMapper: (NewsDto) -> NewsEntity = mappers.newsDtoToEntity::map

    private val newsContentMapper = mappers.newsContentDtoToPlain

    override fun getNews(): Observable<List<News>> = dao.allNews
            .toObservable()
            .map { it.map(entityMapper) }

    override fun getNewsContent(id: String): Single<NewsContent> = networkService.getNewsContent(id)
            .map(newsContentMapper::map)

    override fun updateNews(): Completable = networkService.getNews()
            .map { it.map(dtoMapper) }
            .doOnSuccess(dao::insert)
            .toCompletable()

}