#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include <cmath>
enum Operation{
    GRAY1,GRAY2,GRAY3,REVERSE,REMOVEB,REMOVEG,REMOVER
};
void operationBitmap(JNIEnv *env,jobject bitmap,Operation operation){
    int result;
    // 获取源Bitmap相关信息：宽、高等
    AndroidBitmapInfo sourceInfo;
    result = AndroidBitmap_getInfo(env, bitmap, &sourceInfo);
    if (result < 0) {
        return;
    }
    // 获取源Bitmap像素数据 这里用的是32位的int类型 argb每个8位
    uint32_t* sourceData;
    //锁定像素的地址（不锁定的话地址可能会发生改变）
    result = AndroidBitmap_lockPixels(env, bitmap, (void**)& sourceData);
    if (result < 0) {
        return;
    }
    // 遍历各个像素点
    int color;
    int red, green, blue , alpha;
    int width = sourceInfo.width;
    int height = sourceInfo.height;
    int w, h;
    for (h = 0; h < height; h++) {
        for (w = 0; w < width; w++) {
            color = sourceData[h * width + w];
            alpha = color & 0xff000000;
            red = (color & 0x00ff0000) >> 16;
            green = (color & 0x0000ff00) >> 8;
            blue = color & 0x000000ff;
            switch (operation){
                case GRAY1:
                    color = fmax(blue,fmax(red,green));
                    sourceData[h * width + w] = alpha | color<<16 | color<<8 | color;
                    break;
                case GRAY2:
                    color = fmin(blue,fmin(red,green));
                    sourceData[h * width + w] = alpha | color<<16 | color<<8 | color;
                    break;
                case GRAY3:
                    color = (red+green+blue)/3;
                    sourceData[h * width + w] = alpha | color<<16 | color<<8 | color;
                    break;
                case REVERSE:
                    red = 255-red;
                    green = 255-green;
                    blue = 255-blue;
                    sourceData[h * width + w] = alpha | red<<16 | green<<8 | blue;
                    break;
                case REMOVEB:
                    sourceData[h * width + w] = alpha | red<<16 | green<<8 | 0;
                    break;
                case REMOVEG:
                    sourceData[h * width + w] = alpha | red<<16 | 0<<8 | blue;
                    break;
                case REMOVER:
                    sourceData[h * width + w] = alpha | 0<<16 | green<<8 | blue;
                    break;
            }
        }
    }
    AndroidBitmap_unlockPixels(env, bitmap);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapGray1(JNIEnv *env, jobject instance,
                                                            jobject bitmap) {
    operationBitmap(env,bitmap,GRAY1);
}extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapGray2(JNIEnv *env, jobject instance,jobject bitmap) {
    operationBitmap(env,bitmap,GRAY2);

}extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapGray3(JNIEnv *env, jobject instance,jobject bitmap) {
    operationBitmap(env,bitmap,GRAY3);

}extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapReverse(JNIEnv *env, jobject instance,jobject bitmap) {
    operationBitmap(env,bitmap,REVERSE);
}extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapRemoveB(JNIEnv *env, jobject instance,jobject bitmap) {
    operationBitmap(env,bitmap,REMOVEB);
}extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapRemoveG(JNIEnv *env, jobject instance,jobject bitmap) {
    operationBitmap(env,bitmap,REMOVEG);
}extern "C"
JNIEXPORT void JNICALL
Java_com_itfitness_bitmapoperation_MainActivity_bitmapRemoveR(JNIEnv *env, jobject instance,jobject bitmap) {
    operationBitmap(env,bitmap,REMOVER);

}