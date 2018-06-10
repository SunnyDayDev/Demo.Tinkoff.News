package me.sunnydaydev.tnews.flow

import android.app.Activity
import android.app.Application
import androidx.fragment.app.FragmentActivity
import me.sunnydaydev.tnews.coreui.util.HasFragmentContainer
import me.sunnydaydev.tnews.coreui.util.SimpleActivityLifecycleCallbacks
import me.sunnydaydev.tnews.flow.routing.FlowNavigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

internal class FlowLifecycleCallbacks @Inject constructor(
        private val navigatorHolder: NavigatorHolder,
        private val flowNavigatorFactory: FlowNavigator.Factory
): Application.ActivityLifecycleCallbacks by SimpleActivityLifecycleCallbacks() {

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