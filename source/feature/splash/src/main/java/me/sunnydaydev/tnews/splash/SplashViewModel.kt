package me.sunnydaydev.tnews.splash

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import me.sunnydaydev.mvvmkit.OnBackPressedListener
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import me.sunnydaydev.tnews.coreui.viewModel.LifecycleVewModel
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 *
 * Contain business logic which interact within module.
 *
 */

internal class SplashViewModel @Inject constructor(
        private val router: SplashRouter,
        viewLifeCycle: ViewLifeCycle
): LifecycleVewModel(viewLifeCycle), OnBackPressedListener {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStart() {
        router.openNewsList()
    }

    override fun onBackPressed(): Boolean = router.exit().let { true }

}