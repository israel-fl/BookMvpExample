package flores.israel.androidcodingchallenge.ui.base;

/**
 * All presenters must implement this presenter
 *
 * @param <V> Upper bound declared to be a subclass of {@link BaseMvpView}
 */
public interface BaseMvpPresenter<V extends BaseMvpView> {

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    void attach(V view);

    /**
     * Drops the reference to the view when destroyed
     */
    void detach();

}