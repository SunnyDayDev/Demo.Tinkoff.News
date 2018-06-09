package me.sunnydaydev.tnews.coreui.viewModel

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

abstract class LifecycleVewModel(
        private val viewLifeCycle: ViewLifeCycle
): BaseVewModel(), LifecycleObserver {

    init {
        @Suppress("LeakingThis")
        viewLifeCycle.addObserver(this)
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        viewLifeCycle.removeObserver(this)
    }

}