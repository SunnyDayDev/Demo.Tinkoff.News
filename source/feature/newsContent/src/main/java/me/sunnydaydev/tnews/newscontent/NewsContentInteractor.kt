package me.sunnydaydev.tnews.newscontent

import me.sunnydaydev.tnews.domain.news.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 *
 * Business logic which interact with other modules or system.
 */

@Singleton
internal class NewsContentInteractor @Inject constructor(
        private val newsRepository: NewsRepository
) {

    fun getNewsContent(id: String) = newsRepository.getNewsContent(id)

}