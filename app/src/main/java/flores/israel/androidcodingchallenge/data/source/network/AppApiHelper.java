package flores.israel.androidcodingchallenge.data.source.network;

import java.util.List;

import javax.inject.Inject;

import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import io.reactivex.Observable;

/**
 * Created by israel on 10/21/18.
 */

public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Observable<List<Book>> getBooks() {
        return NetworkClient.getRetrofit().create(BookApi.class).getBooks();
    }

}