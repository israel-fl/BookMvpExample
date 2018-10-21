package flores.israel.androidcodingchallenge.data;

import android.content.Context;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import flores.israel.androidcodingchallenge.data.source.network.ApiHelper;
import flores.israel.androidcodingchallenge.data.source.network.model.Book;
import io.reactivex.Observable;


@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<List<Book>> getBooks() {
        return mApiHelper.getBooks();
    }

}
