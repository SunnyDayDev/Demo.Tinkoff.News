package me.sunnydaydev.tnews.coreui.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StableIdProvider @Inject constructor() {

    private var autoincrement = 0L
        get() = ++field

    private val ids = mutableMapOf<Any, Long>()

    operator fun get(key: Any): Long = ids.getOrPut(key) { autoincrement }

}