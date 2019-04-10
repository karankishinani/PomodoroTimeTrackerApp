package com.example.pttmobile4.api;

import android.util.Base64;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*public class Client {

    private static final String BASE_URL = "http://10.0.2.2:8080/ptt/api/";
    private static Client mInstance;
    private Retrofit retrofit;


    private Client() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .method(original.method(), original.body());
                                requestBuilder.header("Content-Type", "application/json");
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized Client getInstance() {
        if (mInstance == null) {
            mInstance = new Client();
        }
        return mInstance;
    }

    public APIInterface getApi() {
        return retrofit.create(APIInterface.class);
    }

}*/

public class Client {

    private static final String AUTH = "Basic " + Base64.encodeToString(("").getBytes(), Base64.NO_WRAP);
// "http://10.0.2.2:8080/ptt/api/";
    private static final String BASE_URL = "http://10.0.2.2:8080/ptt/";
    private static Client mInstance;
    private Retrofit retrofit;


    private Client() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized Client getInstance() {
        if (mInstance == null) {
            mInstance = new Client();
        }
        return mInstance;
    }

    public APIInterface getApi() {
        return retrofit.create(APIInterface.class);
    }
}
