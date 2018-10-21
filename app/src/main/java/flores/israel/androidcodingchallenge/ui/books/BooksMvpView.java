package flores.israel.androidcodingchallenge.ui.books;

import java.util.List;

import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import flores.israel.androidcodingchallenge.ui.base.BaseMvpView;

/**
 * Created by israel on 10/21/18.
 */

public interface BooksMvpView extends BaseMvpView {

    void showNoNetworkSnack();

    void showErrorSnack();

    void showLoading(boolean enable);

    void createList(List<Book> bookList);

}
