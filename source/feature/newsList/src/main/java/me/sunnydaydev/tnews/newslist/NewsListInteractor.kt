package me.sunnydaydev.tnews.newslist

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.sunnydaydev.tnews.domain.news.NewsRepository
import me.sunnydaydev.tnews.domain.news.News
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 *
 * Business logic which interact with other modules or system.
 */

@Singleton
internal class NewsListInteractor @Inject constructor(
        private val newsRepository: NewsRepository
) {

    fun getNews(): Observable<List<News>> = newsRepository.getNews()

    fun updateNews(): Completable = newsRepository.updateNews()

}