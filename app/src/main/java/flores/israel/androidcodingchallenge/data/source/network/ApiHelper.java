package flores.israel.androidcodingchallenge.data.source.network;

import java.util.List;

import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import io.reactivex.Observable;

/**
 * Created by israel on 10/21/18.
 */

public interface ApiHelper {

    Observable<List<Book>> getBooks();

}
