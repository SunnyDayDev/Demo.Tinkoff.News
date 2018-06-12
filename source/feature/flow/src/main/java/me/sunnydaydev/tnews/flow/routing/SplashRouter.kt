package me.sunnydaydev.tnews.flow.routing

import me.sunnydaydev.tnews.splash.SplashRouter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal class SplashRouterImpl @Inject constructor(
        private val router: Router
): SplashRouter {

    override fun openNewsList() {
        router.newRootScreen(Screen.NEWS_LIST)
    }

    override fun exit() {
        router.exit()
    }

}