package me.sunnydaydev.tnews.coregeneral.util

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface Mapper<S, T> {

    fun map(source: S): T

}

interface BiMapper<S1, S2, T> {

    fun map(source1: S1, source2: S2): T

}