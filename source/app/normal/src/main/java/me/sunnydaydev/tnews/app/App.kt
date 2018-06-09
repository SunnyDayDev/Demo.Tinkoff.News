package me.sunnydaydev.tnews.app

import android.app.Application
import me.sunnydaydev.tnews.app.di.AppComponent
import me.sunnydaydev.tnews.coregeneral.CoreInitializer
import me.sunnydaydev.tnews.coregeneral.di.ComponentRequirements
import me.sunnydaydev.tnews.coregeneral.di.RequirementsComponentProvider
import me.sunnydaydev.tnews.flow.FlowInitializer
import javax.inject.Inject

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */
class App: Application(), RequirementsComponentProvider {

    @Inject
    lateinit var coreInitializer: CoreInitializer

    @Inject
    lateinit var flowInitializer: FlowInitializer

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this) }

    override fun <T : ComponentRequirements> getComponentRequirements(): T = appComponent as T

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        coreInitializer.init(BuildConfig.DEBUG)
        flowInitializer.init()

    }

}