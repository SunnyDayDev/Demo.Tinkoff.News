package me.sunnydaydev.tnews.coregeneral.di

import dagger.Lazy
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

class WeakLazy<T> @Inject constructor(
        private val provider: Provider<T>
): Lazy<T> {

    private var weakRef: WeakReference<T>? = null

    override fun get(): T = weakRef?.get() ?: provider.get()
            .also { weakRef = WeakReference(it) }

}