//
// Created by liutao on 05/07/2020.
//

#include <jni.h>
#include <string>

extern "C" {
    #include <libavcodec/avcodec.h>
    #include <libavformat/avformat.h>
    #include <libavfilter/avfilter.h>
    #include <libavcodec/jni.h>
}

extern "C"
JNIEXPORT jstring JNICALL
Java_me_sonaive_videoencodedecode_FfmpegUtil_nGetFfmpegInfo(JNIEnv *env, jobject thiz) {
    char info[40000] = {0};
    void *i = 0;
    const AVCodec *c_temp = av_codec_iterate(&i);
    while (c_temp) {
        if (c_temp->decode) {
            sprintf(info, "%sdecode:", info);
        } else {
            sprintf(info, "%sencode:", info);
        }
        switch (c_temp->type) {
            case AVMEDIA_TYPE_VIDEO:
                sprintf(info, "%s(video):", info);
                break;
            case AVMEDIA_TYPE_AUDIO:
                sprintf(info, "%s(audio):", info);
                break;
            default:
                sprintf(info, "%s(other):", info);
                break;
        }
        sprintf(info, "%s[%s]\n", info, c_temp->name);
        c_temp = c_temp->next;
    }
    return env->NewStringUTF(info);
}