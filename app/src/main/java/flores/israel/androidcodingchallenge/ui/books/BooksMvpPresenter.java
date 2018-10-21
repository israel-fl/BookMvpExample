package flores.israel.androidcodingchallenge.ui.books;

import flores.israel.androidcodingchallenge.ui.base.BaseMvpPresenter;

/**
 * Created by israel on 10/21/18.
 */
public interface BooksMvpPresenter<V extends BooksMvpView> extends BaseMvpPresenter<V>{

    void setup();

    void getBooks();

}
