package flores.israel.androidcodingchallenge.di.module.submodule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flores.israel.androidcodingchallenge.utils.schedulers.BaseSchedulerProvider;
import flores.israel.androidcodingchallenge.utils.schedulers.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This is a Dagger module. We use this to bind our RxJava Composite Disposable and
 * Scheduler Provider
 */
@Module
public abstract class BaseActivityModule {

    @Provides
    @Singleton
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}

