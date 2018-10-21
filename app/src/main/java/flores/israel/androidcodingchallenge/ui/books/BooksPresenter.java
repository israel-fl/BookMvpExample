package flores.israel.androidcodingchallenge.ui.books;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import flores.israel.androidcodingchallenge.data.DataManager;
import flores.israel.androidcodingchallenge.di.module.submodule.BooksModule;
import flores.israel.androidcodingchallenge.ui.base.BasePresenter;
import flores.israel.androidcodingchallenge.utils.NetworkUtils;
import flores.israel.androidcodingchallenge.utils.schedulers.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Listens to user actions from the UI ({@link BooksActivity}), retrieves the data and
 * updates the UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the TasksPresenter (if it fails, it emits a compiler error).  It uses
 * {@link BooksModule} to do so.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 **/
public class BooksPresenter<V extends BooksMvpView> extends BasePresenter<V> implements
        BooksMvpPresenter<V> {

    @Inject
    public BooksPresenter(@NonNull DataManager dataManager,
                           @NonNull BaseSchedulerProvider schedulerProvider,
                           @NonNull CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void attach(V view) {
        super.attach(view);
    }

    @Override
    public void setup() {

        if (!getMvpView().isNetworkConnected()) {
            getMvpView().showNoNetworkSnack();
        } else {
            getBooks();
        }
    }

    @Override
    public void getBooks() {
        getMvpView().showLoading(true);
        getCompositeDisposable().add(getDataManager().getBooks()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                        // onSuccess
                        bookList -> {
                            getMvpView().createList(bookList);
                            getMvpView().showLoading(false);
                        },
                        // onError
                        error -> {
                            getMvpView().showLoading(false);
                            Timber.e("Error making network call");
                            getMvpView().showErrorSnack();
                        }
                ));
    }

}