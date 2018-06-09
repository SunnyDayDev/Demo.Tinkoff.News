package me.sunnydaydev.tnews.coregeneral

import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface CoreInitializer {
    fun init(debug: Boolean)
}

internal class CoreInitializerIml @Inject constructor(): CoreInitializer {

    override fun init(debug: Boolean) {

        initTimberLog(debug)

        initRxPlugins()

    }

    private fun initTimberLog(debug: Boolean) {

        if (debug) {

            Timber.plant(Timber.DebugTree())

        }

    }

    private fun initRxPlugins() {

        RxJavaPlugins.setErrorHandler {
            Timber.e((it as? UndeliverableException)?.cause ?: it)
        }

    }

}