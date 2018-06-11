package me.sunnydaydev.tnews.flow.di

import dagger.*
import me.sunnydaydev.tnews.flow.routing.NewsListRouterImpl
import me.sunnydaydev.tnews.coregeneral.di.CoreProvider
import me.sunnydaydev.tnews.flow.FlowInitializer
import me.sunnydaydev.tnews.flow.FlowInitializerImpl
import me.sunnydaydev.tnews.flow.routing.NewsContentRouterImpl
import me.sunnydaydev.tnews.newscontent.NewsContentRouter
import me.sunnydaydev.tnews.newscontent.di.NewsContentRouterProvider
import me.sunnydaydev.tnews.newslist.NewsListRouter
import me.sunnydaydev.tnews.newslist.di.NewsListRouterProvider
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Singleton
@Component(
        modules = [FlowProvidesModule::class, FlowBindsModule::class],
        dependencies = [CoreProvider::class]
)
interface FlowComponent: FlowProvider {
    
    object Initializer {
        
        fun init(core: CoreProvider): FlowComponent {

            return DaggerFlowComponent.builder()
                    .coreProvider(core)
                    .build()

        }
        
    }
    
}

interface FlowProvider:
        NewsListRouterProvider,
        NewsContentRouterProvider {

    val flowInitializer: FlowInitializer

}

@Module
internal class FlowProvidesModule {

    @get:Singleton
    @get:Provides
    val cicerone: Cicerone<Router>
        get() = Cicerone.create()

    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder =
            cicerone.navigatorHolder

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

}

@Module
internal interface FlowBindsModule {

    @Binds
    @Reusable
    fun bindNewsListRouter(impl: NewsListRouterImpl): NewsListRouter

    @Binds
    @Reusable
    fun bindNewsContentRouter(impl: NewsContentRouterImpl): NewsContentRouter

    @Binds
    @Singleton
    fun bindInitializer(impl: FlowInitializerImpl): FlowInitializer

}

