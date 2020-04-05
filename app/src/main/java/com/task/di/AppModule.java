package com.task.di;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.task.App;
import com.task.BuildConfig;
import com.task.base.ViewModelFactory;
import com.task.data.DataManager;
import com.task.data.db.AppDatabase;
import com.task.data.db.LocalDatabase;
import com.task.data.remote.RemoteService;
import com.task.data.remote.Repository;
import com.task.util.RxSingleSchedulers;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ViewModelModule.class}) public class AppModule {

    @Provides Context provideContext(App app) {
        return app.getApplicationContext();
    }

    @Provides @Singleton ViewModelFactory provideViewModelFactory(
            Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        return new ViewModelFactory(creators);
    }

    @Provides @Singleton RemoteService provideRemoteService(Retrofit retrofit) {
        return retrofit.create(RemoteService.class);
    }

    @Provides @Singleton Repository provideRepository(RemoteService remoteService,
                                                      LocalDatabase localDatabase) {
        return new Repository(remoteService, localDatabase);
    }

    @Provides @Singleton DataManager provideDataManager(Repository repository,
                                                        LocalDatabase localDatabase) {
        return new DataManager(repository, localDatabase);
    }

    @Provides @Singleton LocalDatabase provideLocalDataBase(AppDatabase appDatabase) {
        return new LocalDatabase(appDatabase);
    }

    @Provides @Singleton RxSingleSchedulers provideRXSingleSchedulers() {
        return RxSingleSchedulers.DEFAULT;
    }

    @Provides @Singleton HttpLoggingInterceptor provideHttpLoggingInterceptot() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return interceptor;
    }

    @Provides @Singleton OkHttpClient provideOkhttpClient(HttpLoggingInterceptor interceptor) {
        return
                new OkHttpClient.Builder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                        .readTimeout(60000, TimeUnit.MILLISECONDS)
                        .writeTimeout(60000, TimeUnit.MILLISECONDS)
                        .addNetworkInterceptor(interceptor).build();
    }

    @Provides @Singleton Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()));
    }

    @Provides @Singleton Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl("https://en.wikipedia.org//").build();
    }

    @Provides @Singleton AppDatabase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_db")
                .fallbackToDestructiveMigration().build();
    }


}
