package me.sonaive.videoencodedecode

/**
 * Created by liutao on 05/07/2020.
 */
object FfmpegUtil {
    init {
        System.loadLibrary("avcodec")
        System.loadLibrary("avfilter")
        System.loadLibrary("avformat")
        System.loadLibrary("avutil")
        System.loadLibrary("swresample")
        System.loadLibrary("swscale")
        System.loadLibrary("video-encode-decode")
    }

    fun getFfmpegInfo(): String {
        return nGetFfmpegInfo()
    }

    private external fun nGetFfmpegInfo(): String
}
