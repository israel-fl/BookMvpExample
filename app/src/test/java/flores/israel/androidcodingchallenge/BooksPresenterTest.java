package flores.israel.androidcodingchallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import flores.israel.androidcodingchallenge.data.DataManager;
import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import flores.israel.androidcodingchallenge.ui.books.BooksMvpView;
import flores.israel.androidcodingchallenge.ui.books.BooksPresenter;
import flores.israel.androidcodingchallenge.utils.TestSchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link BooksPresenter}
 */
@RunWith(MockitoJUnitRunner.class)
public class BooksPresenterTest {

    @Mock
    private DataManager mMockDataManager;

    @Mock
    private BooksMvpView mMockMvpView;

    private TestScheduler mTestScheduler;
    private BooksPresenter<BooksMvpView> mPresenter;

    @Before
    public void setup() throws Exception {

        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        // Make the sure that all schedulers are immediate.
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mPresenter = new BooksPresenter<>(
                mMockDataManager,
                testSchedulerProvider,
                compositeDisposable);
        mPresenter.attach(mMockMvpView);

    }

    @Test
    public void test_setup_withoutNetwork() {
        // Mock
        doReturn(false).when(mMockMvpView).isNetworkConnected();

        // Action
        mPresenter.setup();

        // Assert
        verify(mMockMvpView).showNoNetworkSnack();
    }

    @Test
    public void test_getBooks_success() {
        // Mock
        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author", "ImageUrl"));
        doReturn(Observable.just(books)).when(mMockDataManager).getBooks();

        // Action
        mPresenter.getBooks();
        mTestScheduler.triggerActions();

        // Assert
        verify(mMockMvpView).showLoading(true); // loading shown initially
        verify(mMockMvpView).createList(books); // our book list should be passed to the view
        verify(mMockMvpView).showLoading(false); // loading hidden again
    }

    @Test
    public void test_getBooks_error() {
        // Mock
        doReturn(Observable.error(new Exception("Error"))).when(mMockDataManager).getBooks();

        // Action
        mPresenter.getBooks();
        mTestScheduler.triggerActions();

        // Assert
        verify(mMockMvpView).showLoading(true); // loading shown initially
        verify(mMockMvpView).showErrorSnack(); // an error should be displayed
        verify(mMockMvpView).showLoading(false); // loading hidden again
    }


}
