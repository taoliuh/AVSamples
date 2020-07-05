package me.sonaive.avsamples.utils

import me.sonaive.lab.ext.toast
import me.sonaive.avsamples.App

inline fun toast(value: () -> String): Unit =
        App.instance().toast(value)