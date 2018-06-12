package me.sunnydaydev.tnews.splash.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import me.sunnydaydev.tnews.splash.SplashFragment
import me.sunnydaydev.tnews.splash.SplashViewModel
import me.sunnydaydev.tnews.coregeneral.di.ComponentRequirements
import me.sunnydaydev.tnews.coregeneral.di.CoreProvider
import me.sunnydaydev.tnews.coreui.di.Injector
import me.sunnydaydev.tnews.coreui.di.VMFactory
import me.sunnydaydev.tnews.coreui.di.MVVMModule
import me.sunnydaydev.tnews.coreui.di.ViewModelKey
import me.sunnydaydev.tnews.splash.SplashRouter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface SplashComponentRequirements: ComponentRequirements,
        CoreProvider,
        SplashRouterProvider

interface SplashRouterProvider {

    val splashRouter: SplashRouter

}

@Singleton
@Component(
        modules = [SplashBindModule::class],
        dependencies = [SplashComponentRequirements::class]
)
internal interface SplashComponent: Injector<SplashFragment> {

    object Initializer {

        fun init(requirements: SplashComponentRequirements): SplashComponent {

            return DaggerSplashComponent.builder()
                    .splashComponentRequirements(requirements)
                    .build()

        }

    }

}

@Module(includes = [MVVMModule::class])
internal interface SplashBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindViewModel(vm: SplashViewModel): ViewModel

}

internal class Injection @Inject constructor(
        val vmFactory: VMFactory
)