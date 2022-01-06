package com.nomadlab.myjwt_server.repository;

import com.nomadlab.myjwt_server.BuildConfig;
import com.nomadlab.myjwt_server.repository.models.request.ReqLogin;
import com.nomadlab.myjwt_server.repository.models.request.ReqSignup;
import com.nomadlab.myjwt_server.repository.models.response.ResLogin;
import com.nomadlab.myjwt_server.repository.models.response.ResPost;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JwtService {


    @POST("login")
    Call<ResLogin> getLogin(@Body ReqLogin reqLogin);

    @POST("join")
    Call<ReqSignup> saveMember(@Body ReqSignup reqSignup);

    @GET("post")
    Call<ResPost> getPostList(@Header("Authorization") String token);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://lalacoding.site/")
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
