package me.sunnydaydev.tnews.domain.news.di

import dagger.Binds
import dagger.Component
import dagger.Module
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
        dependencies = [NetworkServicesProvider::class]
)
interface NewsDomainComponent: NewsDomainProvider {

    object Initializer {

        fun init(network: NetworkServicesProvider): NewsDomainComponent {

           return DaggerNewsDomainComponent.builder()
                   .networkServicesProvider(network)
                   .build()

        }

    }

}

interface NewsDomainProvider {

    val newsRepository: NewsRepository

}

@Module
internal interface NewsModule {

    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

}