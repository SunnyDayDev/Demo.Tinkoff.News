package me.sunnydaydev.tnews.coreui.di

import dagger.Module
import dagger.Provides
import me.sunnydaydev.mvvmkit.util.ViewLifeCycle
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Module
class MVVMModule {

    @Provides
    @Singleton
    fun provideLifeCycle() = ViewLifeCycle()

}