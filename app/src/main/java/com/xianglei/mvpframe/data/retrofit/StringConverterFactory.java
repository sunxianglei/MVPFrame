package com.xianglei.mvpframe.data.retrofit;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author sunxianglei
 * @date 2019/1/3
 */

public final class StringConverterFactory extends Converter.Factory {

    private final static String TAG = "StringConverterFactory";

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type == String.class){
            return StringResponseConverter.INSTANCE;
        }
        return null;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if(type == RequestBody.class){
            return StringRequestBodyConverter.INSTANCE;
        }
        return null;
    }

    final static class StringResponseConverter implements Converter<ResponseBody, String> {
        final static StringResponseConverter INSTANCE = new StringResponseConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            Log.v(TAG, value.toString());
            return value.string();
        }
    }

    final static class StringRequestBodyConverter implements Converter<RequestBody, RequestBody> {
        final static StringRequestBodyConverter INSTANCE = new StringRequestBodyConverter();

        @Override
        public RequestBody convert(RequestBody value) throws IOException {
            Log.v(TAG, "no change, hahaha...");
            return value;
        }
    }

}
