package me.sunnydaydev.tnews.flow.routing

import me.sunnydaydev.tnews.newslist.NewsListRouter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class NewsListRouterImpl @Inject constructor(
        private val router: Router
): NewsListRouter {

    override fun openNews(newsId: String) {
        router.navigateTo(Screen.NEWS_DETAIL, newsId)
    }

}