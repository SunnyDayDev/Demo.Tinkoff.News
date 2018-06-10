package me.sunnydaydev.tnews.flow.routing

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import me.sunnydaydev.tnews.newscontent.NewsContentActivity
import me.sunnydaydev.tnews.newslist.NewsListActivity
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject


/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal object Screen {
    const val NEWS_LIST = "newsList"
    const val NEWS_CONTENT = "newsContent"
}

internal class FlowNavigator(
        activity: FragmentActivity,
        fragmentManager: FragmentManager,
        containerId: Int
): SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun createActivityIntent(
            context: Context, screenKey: String, data: Any?
    ): Intent? = when(screenKey) {
        Screen.NEWS_LIST -> NewsListActivity.intent(context)
        Screen.NEWS_CONTENT -> NewsContentActivity.intent(data as String, context)
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
        ): FlowNavigator = FlowNavigator(activity, fragmentManager, containerId)

    }

}