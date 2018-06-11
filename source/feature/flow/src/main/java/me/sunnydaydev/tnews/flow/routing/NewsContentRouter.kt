package me.sunnydaydev.tnews.flow.routing

import me.sunnydaydev.tnews.newscontent.NewsContentRouter
import me.sunnydaydev.tnews.newslist.NewsListRouter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class NewsContentRouterImpl @Inject constructor(
        private val router: Router
): NewsContentRouter {

    override fun exit() {
        router.exit()
    }

}