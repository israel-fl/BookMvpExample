package flores.israel.androidcodingchallenge.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import flores.israel.androidcodingchallenge.di.module.ActivityBindingModule;
import flores.israel.androidcodingchallenge.di.module.ApplicationModule;
import flores.israel.androidcodingchallenge.di.module.submodule.BaseActivityModule;
import flores.israel.androidcodingchallenge.di.module.submodule.DataManagerModule;
import flores.israel.androidcodingchallenge.ui.base.BaseApplication;

/**
 * This is a Dagger component. Refer to {@link BaseApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * BaseApplication}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = {
        DataManagerModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        BaseActivityModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}