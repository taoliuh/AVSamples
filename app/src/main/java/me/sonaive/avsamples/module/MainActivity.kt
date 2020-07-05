package me.sonaive.avsamples.module

import com.wuhenzhizao.titlebar.widget.CommonTitleBar.ACTION_LEFT_TEXT
import kotlinx.android.synthetic.main.activity_main.*
import me.sonaive.avsamples.R
import me.sonaive.lab.base.view.activity.BaseBusinessActivity
import me.sonaive.lab.base.viewstate.BaseViewState
import me.sonaive.videoencodedecode.FfmpegUtil

class MainActivity : BaseBusinessActivity() {

    override val layoutId = R.layout.activity_main

    override fun binds() {
        titleBar.setListener { _, action, _ -> if (action == ACTION_LEFT_TEXT) finish() }
        helloWorld.text = FfmpegUtil.getFfmpegInfo()
    }

    override fun <T> processData(state: BaseViewState<T>) {

    }
}
