# CMakeLists.txt

# cmake 最低版本
cmake_minimum_required(VERSION 3.4.1)

# 1. 定义so库和头文件所在目录，方面后面使用
#三方开源项目 路径
set(THIRD_PARTY_ROOT ${CMAKE_SOURCE_DIR}/../../../../thirdparty)

#ffmpeg 路径
set(FFMPEG_PATH ${THIRD_PARTY_ROOT}/ffmpeg)
#jni 路径
set(NATIVE_JNI_PATH ${CMAKE_SOURCE_DIR}/jni)

# 2. 添加头文件目录
#FFmpeg 头文件
include_directories(${FFMPEG_PATH}/include)
#jni 头文件
include_directories(${NATIVE_JNI_PATH})
#cpp 目录下的头文件
include_directories(${CMAKE_SOURCE_DIR})

#JNI 接口
FILE(GLOB JNI_CPP ${NATIVE_JNI_PATH}/*.cpp)

# 3. 添加ffmpeg相关的so库
add_library(avutil SHARED IMPORTED)
set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${THIRD_PARTY_ROOT}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavutil.so)

add_library(swresample SHARED IMPORTED)
set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${THIRD_PARTY_ROOT}/libs/${CMAKE_ANDROID_ARCH_ABI}/libswresample.so)

add_library(avcodec SHARED IMPORTED)
set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${THIRD_PARTY_ROOT}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavcodec.so)

add_library(avfilter SHARED IMPORTED)
set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${THIRD_PARTY_ROOT}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavfilter.so)

add_library(swscale SHARED IMPORTED)
set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${THIRD_PARTY_ROOT}/libs/${CMAKE_ANDROID_ARCH_ABI}/libswscale.so)

add_library(avformat SHARED IMPORTED)
set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${THIRD_PARTY_ROOT}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavformat.so)

# 查找代码中使用到的系统库
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# 配置so库编译信息
add_library(
        # 输出so库的名称
        video-encode-decode

        # 设置生成库的方式，默认为SHARE动态库
        SHARED

        # 列出参与编译的所有源文件
        ${JNI_CPP})

# 指定编译目标库时，cmake要链接的库
target_link_libraries(
        # 指定目标库，video-encode-decode 是在上面 add_library 中配置的目标库
        video-encode-decode
        # 连接 FFmpeg 相关的库
        avutil
        swresample
        avcodec
        avfilter
        swscale
        avformat
        # 连接 log 库
        ${log-lib})