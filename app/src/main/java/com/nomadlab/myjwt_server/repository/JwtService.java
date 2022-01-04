package com.nomadlab.myjwt_server.repository;

import com.nomadlab.myjwt_server.BuildConfig;
import com.nomadlab.myjwt_server.repository.models.request.ReqLogin;
import com.nomadlab.myjwt_server.repository.models.response.Login;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JwtService {


    @POST("login")
    Call<Login> getLogin(@Body ReqLogin reqLogin);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.25.3:8080/")
            .client(httpLoggingInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static OkHttpClient httpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
}
