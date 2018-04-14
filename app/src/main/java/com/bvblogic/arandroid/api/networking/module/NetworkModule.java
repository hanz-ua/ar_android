package com.bvblogic.arandroid.api.networking.module;

import com.bvblogic.arandroid.api.networking.NetworkUser;
import com.bvblogic.arandroid.api.service.User;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hanz on 14.04.2018.
 */

@Module
public class NetworkModule {
    private File cacheFile;
    private String url;

    public NetworkModule(File cacheFile, String url) {
        this.cacheFile = cacheFile;
        this.url = url;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request origin = chain.request();

                    Request request = origin.newBuilder()
                            .header("Content-Type",
                                    "application/json")
                            .header("Cache-Control",
                                    "max-age=432000")
                            .removeHeader("Pragma")
                            .build();

                    Response response = chain.proceed(request);
                    response.cacheResponse();
                    return response;
                })
                .cache(cache)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public User providesUserService(
            Retrofit retrofit){
        return retrofit.create(User.class);
    }

    @Provides
    @Singleton
    public NetworkUser providesNetworkUser(User user){
        return new NetworkUser(user);
    }
}
