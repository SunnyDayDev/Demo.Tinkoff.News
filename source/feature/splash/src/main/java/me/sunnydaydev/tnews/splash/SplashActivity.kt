package me.sunnydaydev.tnews.splash

import android.os.Bundle
import me.sunnydaydev.mvvmkit.BaseMVVMActivity



/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class SplashActivity : BaseMVVMActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)

        val fragment = SplashFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_content, fragment)
                .commit()

    }

}