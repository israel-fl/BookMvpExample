package flores.israel.androidcodingchallenge.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import flores.israel.androidcodingchallenge.di.ActivityScoped;
import flores.israel.androidcodingchallenge.di.module.submodule.BooksModule;
import flores.israel.androidcodingchallenge.ui.books.BooksActivity;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be ApplicationComponent. The beautiful part
 * about this setup is that you never need to tell ApplicationComponent that it is going to have
 * all these subcomponents nor do you need to tell these subcomponents that ApplicationComponent
 * exists. We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation @ActivityScoped When Dagger.Android
 * annotation processor runs it will create the subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = BooksModule.class)
    abstract BooksActivity booksActivity();

}