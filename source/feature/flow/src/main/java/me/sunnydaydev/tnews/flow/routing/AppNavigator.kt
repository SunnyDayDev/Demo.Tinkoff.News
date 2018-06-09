package me.sunnydaydev.tnews.flow.routing

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import me.sunnydaydev.tnews.newslist.NewsListActivity
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject


/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal object Screen {
    const val NEWS_LIST = "newsList"
    const val NEWS_DETAIL = "newsDetail"
}

class AppNavigator(
        activity: FragmentActivity,
        fragmentManager: FragmentManager,
        containerId: Int
): SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun createActivityIntent(
            context: Context, screenKey: String, data: Any?
    ): Intent? = when(screenKey) {
        Screen.NEWS_LIST -> NewsListActivity.intent(context)
        else -> null
    }

    override fun createFragment(
            screenKey: String, data: Any?
    ): Fragment? = TODO()

    class Factory @Inject constructor() {

        fun create(
                activity: FragmentActivity,
                fragmentManager: FragmentManager,
                containerId: Int
        ): AppNavigator = AppNavigator(activity, fragmentManager, containerId)

    }

}