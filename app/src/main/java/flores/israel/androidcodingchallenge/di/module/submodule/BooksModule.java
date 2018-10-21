package flores.israel.androidcodingchallenge.di.module.submodule;

import dagger.Binds;
import dagger.Module;
import flores.israel.androidcodingchallenge.di.ActivityScoped;
import flores.israel.androidcodingchallenge.ui.books.BooksMvpPresenter;
import flores.israel.androidcodingchallenge.ui.books.BooksMvpView;
import flores.israel.androidcodingchallenge.ui.books.BooksPresenter;

/**
 * Created by israel on 10/21/18.
 */
@Module
abstract public class BooksModule {

    @Binds
    @ActivityScoped
    abstract BooksMvpPresenter<BooksMvpView> booksPresenter(BooksPresenter<BooksMvpView> presenter);

}
