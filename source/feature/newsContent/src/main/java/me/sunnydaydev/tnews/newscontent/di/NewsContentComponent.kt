package me.sunnydaydev.tnews.newscontent.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import me.sunnydaydev.tnews.newscontent.NewsContentFragment
import me.sunnydaydev.tnews.newscontent.NewsContentViewModel
import me.sunnydaydev.tnews.coregeneral.di.ComponentRequirements
import me.sunnydaydev.tnews.coregeneral.di.CoreProvider
import me.sunnydaydev.tnews.coreui.di.Injector
import me.sunnydaydev.tnews.domain.news.di.NewsDomainProvider
import me.sunnydaydev.tnews.coreui.di.VMFactory
import me.sunnydaydev.tnews.coreui.di.MVVMModule
import me.sunnydaydev.tnews.coreui.di.ViewModelKey
import me.sunnydaydev.tnews.newscontent.NewsContentParams
import me.sunnydaydev.tnews.newscontent.NewsContentRouter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NewsContentComponentRequirements: ComponentRequirements,
        CoreProvider,
        NewsDomainProvider,
        NewsContentRouterProvider

interface NewsContentRouterProvider {

    val newsContentRouter: NewsContentRouter

}

@Singleton
@Component(
        modules = [NewsContentBindModule::class],
        dependencies = [NewsContentComponentRequirements::class]
)
internal interface NewsContentComponent: Injector<NewsContentFragment> {

    object Initializer {

        fun init(
                requirements: NewsContentComponentRequirements,
                args: NewsContentParams
        ): NewsContentComponent {

            return DaggerNewsContentComponent.builder()
                    .requirements(requirements)
                    .bindArguments(args)
                    .build()

        }

    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindArguments(args: NewsContentParams): Builder

        fun requirements(requirements: NewsContentComponentRequirements): Builder

        fun build(): NewsContentComponent

    }

}

@Module(includes = [MVVMModule::class])
internal interface NewsContentBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsContentViewModel::class)
    fun bindViewModel(vm: NewsContentViewModel): ViewModel

}

internal class Injection @Inject constructor(
        val vmFactory: VMFactory
)