package me.sunnydaydev.tnews.coregeneral.di

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface ComponentRequirements

interface RequirementsComponentProvider {

    fun <T: ComponentRequirements> getComponentRequirements(): T

}