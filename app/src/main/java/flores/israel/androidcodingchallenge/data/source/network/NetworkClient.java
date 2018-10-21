package flores.israel.androidcodingchallenge.data.source.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import flores.israel.androidcodingchallenge.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by israel on 10/21/18.
 */

@Singleton
public class NetworkClient {

    private static Retrofit retrofit;

    private static OkHttpClient okHttpClient;

    private NetworkClient() {
        // Not publicly instantiable
    }

    static Retrofit getRetrofit() {

        if (okHttpClient == null) {
            initOkHttp();
        }

        Gson gson = new GsonBuilder().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;

    }

    private static void initOkHttp() {
        okHttpClient = new OkHttpClient().newBuilder().build();
    }

}