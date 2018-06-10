package me.sunnydaydev.tnews.app.di

import dagger.Component
import me.sunnydaydev.tnews.app.App
import me.sunnydaydev.tnews.newslist.di.NewsListComponentRequirements
import me.sunnydaydev.tnews.coregeneral.di.AppInitializerProvider
import me.sunnydaydev.tnews.coregeneral.di.CoreComponent
import me.sunnydaydev.tnews.coregeneral.di.CoreProvider
import me.sunnydaydev.tnews.coreui.di.Injector
import me.sunnydaydev.tnews.domain.news.di.NewsDomainComponent
import me.sunnydaydev.tnews.domain.news.di.NewsDomainProvider
import me.sunnydaydev.tnews.domain.network.di.NetworkComponent
import me.sunnydaydev.tnews.flow.di.FlowComponent
import me.sunnydaydev.tnews.flow.di.FlowProvider
import me.sunnydaydev.tnews.newscontent.di.NewsContentComponentRequirements

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Component(
        dependencies = [
            CoreProvider::class,
            AppInitializerProvider::class,
            NewsDomainProvider::class,
            FlowProvider::class
        ]
)
interface AppComponent: Injector<App>,
        NewsListComponentRequirements,
        NewsContentComponentRequirements{

    object Initializer {

        fun init(app: App): AppComponent {

            val core = CoreComponent.Initializer.init(app)

            val network = NetworkComponent.Initializer.init()

            val newsDomain = NewsDomainComponent.Initializer.init(core, network)

            val flow = FlowComponent.Initializer.init(core)

            return DaggerAppComponent.builder()
                    .coreProvider(core)
                    .appInitializerProvider(core)
                    .newsDomainProvider(newsDomain)
                    .flowProvider(flow)
                    .build()

        }

    }

}