package me.sunnydaydev.tnews.flow

import android.app.Application
import android.content.Context
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface FlowInitializer {

    fun init()

}

internal class FlowInitializerImpl @Inject constructor(
        private val context: Context,
        private val lifecycleCallbacks: FlowLifecycleCallbacks
): FlowInitializer {

    private var initialized = false

    override fun init() {

        if (initialized) return

        (context.applicationContext as Application)
                .registerActivityLifecycleCallbacks(lifecycleCallbacks)

    }

}