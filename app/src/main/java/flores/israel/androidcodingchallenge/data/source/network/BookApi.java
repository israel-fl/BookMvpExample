package flores.israel.androidcodingchallenge.data.source.network;

import java.util.List;

import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Interface to map network requests to Retrofit
 */
public interface BookApi {

    @GET("books.json")
    Observable<List<Book>> getBooks();

}
