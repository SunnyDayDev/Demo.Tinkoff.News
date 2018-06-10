package me.sunnydaydev.tnews.domain.news.di

import dagger.Binds
import dagger.Component
import dagger.Module
import me.sunnydaydev.tnews.coregeneral.di.CoreProvider
import me.sunnydaydev.tnews.domain.news.NewsRepository
import me.sunnydaydev.tnews.domain.news.NewsRepositoryImpl
import me.sunnydaydev.tnews.domain.network.di.NetworkServicesProvider
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Singleton
@Component(
        modules = [NewsModule::class],
        dependencies = [CoreProvider::class, NetworkServicesProvider::class]
)
interface NewsDomainComponent: NewsDomainProvider {

    object Initializer {

        fun init(core: CoreProvider, network: NetworkServicesProvider): NewsDomainComponent {

           return DaggerNewsDomainComponent.builder()
                   .coreProvider(core)
                   .networkServicesProvider(network)
                   .build()

        }

    }

}

interface NewsDomainProvider {

    val newsRepository: NewsRepository

}

@Module(includes = [DatabaseModule::class])
internal interface NewsModule {

    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

}