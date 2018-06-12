package me.sunnydaydev.tnews.flow.routing

import android.annotation.SuppressLint
import androidx.core.util.Pair as UtilPair
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import me.sunnydaydev.mvvmkit.util.findViewWithTag
import me.sunnydaydev.tnews.coreui.util.Transitions
import me.sunnydaydev.tnews.flow.R
import me.sunnydaydev.tnews.flow.util.actionBarView
import me.sunnydaydev.tnews.newscontent.NewsContentActivity
import me.sunnydaydev.tnews.newslist.NewsContentTransitionData
import me.sunnydaydev.tnews.newslist.NewsListActivity
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
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
        private val activity: FragmentActivity,
        fragmentManager: FragmentManager,
        containerId: Int
): SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun createActivityIntent(
            context: Context, screenKey: String, data: Any?
    ): Intent? = when(screenKey) {
        Screen.NEWS_LIST -> NewsListActivity.intent(context)
        Screen.NEWS_CONTENT -> {

            data as NewsContentTransitionData

            val titleTransitionName = activity
                    .findViewWithTag(R.id.transition_newslist_title, data.id)
                    ?.let { ViewCompat.getTransitionName(it) }
                    ?: ""

            NewsContentActivity.intent(
                    data.id,
                    data.title,
                    titleTransitionName,
                    context
            )

        }
        else -> null
    }

    @SuppressLint("InlinedApi")
    override fun createStartActivityOptions(command: Command, activityIntent: Intent): Bundle? = when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP -> null
        command is Forward -> when(command.screenKey) {
            Screen.NEWS_CONTENT -> {

                // TODO: Transition name nullability

                val data = command.transitionData as NewsContentTransitionData

                val title = activity.findViewWithTag(R.id.transition_newslist_title, data.id)!!
                val statusBar: View = activity.findViewById(android.R.id.statusBarBackground)
                val navigationBar: View = activity.findViewById(android.R.id.navigationBarBackground)
                val toolbar = activity.actionBarView !!

                ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        UtilPair.create(title, ViewCompat.getTransitionName(title)),
                        UtilPair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME),
                        UtilPair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME),
                        UtilPair.create(toolbar, Transitions.TOOLBAR_TRANSITION_NAME)
                ).toBundle()

            }
            else -> super.createStartActivityOptions(command, activityIntent)
        }
        else -> super.createStartActivityOptions(command, activityIntent)
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

    override fun exit() {
        activity.supportFinishAfterTransition()
    }

}