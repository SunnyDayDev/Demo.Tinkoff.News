package me.sunnydaydev.tnews.newslist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import me.sunnydaydev.tnews.newslist.NewsListFragment
import me.sunnydaydev.tnews.newslist.vm.NewsListViewModel
import me.sunnydaydev.tnews.coregeneral.di.ComponentRequirements
import me.sunnydaydev.tnews.coregeneral.di.CoreProvider
import me.sunnydaydev.tnews.coreui.di.Injector
import me.sunnydaydev.tnews.domain.news.di.NewsDomainProvider
import me.sunnydaydev.tnews.coreui.di.VMFactory
import me.sunnydaydev.tnews.coreui.di.MVVMModule
import me.sunnydaydev.tnews.coreui.di.ViewModelKey
import me.sunnydaydev.tnews.newslist.NewsListRouter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NewsListComponentRequirements: ComponentRequirements,
        CoreProvider,
        NewsDomainProvider,
        NewsListRouterProvider

interface NewsListRouterProvider {

    val newsListRouter: NewsListRouter

}

@Singleton
@Component(
        modules = [NewsListBindModule::class],
        dependencies = [NewsListComponentRequirements::class]
)
interface NewsListComponent: Injector<NewsListFragment> {

    object Initializer {

        fun init(requirements: NewsListComponentRequirements): NewsListComponent {
            return DaggerNewsListComponent.builder()
                    .newsListComponentRequirements(requirements)
                    .build()
        }

    }

}

@Module(includes = [MVVMModule::class])
internal interface NewsListBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    fun bindViewModel(vm: NewsListViewModel): ViewModel

}

internal class Injection @Inject constructor(
        val vmFactory: VMFactory
)