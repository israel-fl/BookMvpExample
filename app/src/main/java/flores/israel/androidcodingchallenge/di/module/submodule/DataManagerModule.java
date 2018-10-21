package flores.israel.androidcodingchallenge.di.module.submodule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flores.israel.androidcodingchallenge.data.AppDataManager;
import flores.israel.androidcodingchallenge.data.DataManager;
import flores.israel.androidcodingchallenge.data.source.network.ApiHelper;
import flores.israel.androidcodingchallenge.data.source.network.AppApiHelper;

/**
 * This is used by Dagger to inject the required arguments into the {@link DataManager}.
 */
@Module
abstract public class DataManagerModule {

    @Provides
    @Singleton
    static DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    static ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

}
