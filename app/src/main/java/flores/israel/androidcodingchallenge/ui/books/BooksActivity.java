package flores.israel.androidcodingchallenge.ui.books;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import flores.israel.androidcodingchallenge.R;
import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import flores.israel.androidcodingchallenge.ui.base.BaseActivity;

/**
 * Created by israel on 10/21/18.
 */

public class BooksActivity extends BaseActivity implements BooksMvpView {

    @Inject
    BooksMvpPresenter<BooksMvpView> mPresenter;

    @BindView(R.id.content)
    ConstraintLayout content;

    @BindView(R.id.lt_swipe_refresh)
    SwipeRefreshLayout ltSwipeRefresh;

    @BindView(R.id.rv_books)
    RecyclerView rvBooks;

    private BooksAdapter mBooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_books);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.attach(this);

        // Setup activity
        setup();
    }

    @Override
    protected void setup() {

        // Setup the presenter
        mPresenter.setup();

        bindListeners();

    }

    /**
     * Name: bindListeners
     * Input: void
     * Output:
     * Purpose: Provides a single location to initialize any listeners required in this activity
     */
    @Override
    protected void bindListeners() {
        ltSwipeRefresh.setOnRefreshListener(() -> mPresenter.getBooks());
    }


    /**
     * Helper functions to show snackbars
     */
    @Override
    public void showNoNetworkSnack() {
        Snackbar noNetworkSnack = Snackbar.make(content, R.string.no_network, Snackbar.LENGTH_INDEFINITE);
        View view = noNetworkSnack.getView();
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        noNetworkSnack.show();
    }

    @Override
    public void showErrorSnack() {
        Snackbar snackbar = Snackbar.make(content, R.string.error_loading, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * Simple toggle to show the refresh icon on the SwipeRefreshLayout
     * @param enable - state of toggle
     */
    @Override
    public void showLoading(boolean enable) {
        ltSwipeRefresh.setRefreshing(enable);
    }

    /**
     * Name: createList
     * Input: void
     * Output: void
     * Purpose: Create the adapter for the recycler view and populate it with the book list
     * retrieved from the network
     */
    @Override
    public void createList(List<Book> books) {
        mBooksAdapter = new BooksAdapter(getApplicationContext(), books);
        rvBooks.setAdapter(mBooksAdapter);
        rvBooks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
