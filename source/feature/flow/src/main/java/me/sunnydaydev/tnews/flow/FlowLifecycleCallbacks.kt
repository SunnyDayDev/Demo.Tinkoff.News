package me.sunnydaydev.tnews.flow

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ActionBarContainer
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import me.sunnydaydev.mvvmkit.util.find
import me.sunnydaydev.tnews.coreui.util.HasFragmentContainer
import me.sunnydaydev.tnews.coreui.util.SimpleActivityLifecycleCallbacks
import me.sunnydaydev.tnews.coreui.util.Transitions
import me.sunnydaydev.tnews.flow.routing.FlowNavigator
import me.sunnydaydev.tnews.flow.util.actionBarView
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

internal class FlowLifecycleCallbacks @Inject constructor(
        private val navigatorHolder: NavigatorHolder,
        private val flowNavigatorFactory: FlowNavigator.Factory
): Application.ActivityLifecycleCallbacks by SimpleActivityLifecycleCallbacks() {

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

        val actionBarView = p0.actionBarView ?: return
        ViewCompat.setTransitionName(actionBarView, Transitions.TOOLBAR_TRANSITION_NAME)

    }

    // TODO: onResumeFragments()
    override fun onActivityResumed(p0: Activity) {
        addNavigator(p0)
    }

    override fun onActivityPaused(p0: Activity) {
        removeNavigator(p0)
    }

    private fun addNavigator(p0: Activity) {

        val activity = p0 as? FragmentActivity ?: return
        val containerId = (p0 as? HasFragmentContainer)?.fragmentContainerId ?: -1

        val navigator = flowNavigatorFactory.create(
                activity, activity.supportFragmentManager, containerId)

        navigatorHolder.setNavigator(navigator)

    }

    private fun removeNavigator(p0: Activity) {

        p0 as? FragmentActivity ?: return

        navigatorHolder.removeNavigator()

    }

}