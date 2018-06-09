package me.sunnydaydev.tnews.coreui.di

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */
interface Injector<T> {
    fun inject(target: T)
}