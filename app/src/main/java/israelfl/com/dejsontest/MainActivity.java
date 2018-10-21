package israelfl.com.dejsontest;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private ConstraintLayout content;
    private SwipeRefreshLayout ltSwipeRefresh;
    private RecyclerView rvBooks;

    private List<Book> mBooks;
    private BookAdapter mBookAdapter;
    private Snackbar noNetworkSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views to activity
        content = findViewById(R.id.content);
        ltSwipeRefresh = findViewById(R.id.lt_swipe_refresh);
        rvBooks = findViewById(R.id.rv_books);

        // Instantiate list
        mBooks = new ArrayList<>();

        setup();
    }


    /**
     * Name: Setup
     * Input: void
     * Output: void
     * Purpose: Check network status, if available make a request to retrieve the book list and
     * bind all listeners
     */
    private void setup() {

        if (!isNetworkAvailable()) {
            noNetworkSnack = Snackbar.make(content, "No network", Snackbar.LENGTH_INDEFINITE);
            View view = noNetworkSnack.getView();
            TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.RED);
            noNetworkSnack.show();
        } else {
            getBooks();
        }

        // Setup listeners
        bindListeners();
    }

    /**
     * Name: bindListeners
     * Input: void
     * Output:
     * Purpose: Provides a single location to initialize any listeners required in this activity
     */
    private void bindListeners() {
        // Get book list again on swipe down
        ltSwipeRefresh.setOnRefreshListener(this::getBooks);
    }

    /**
     * Name: createList
     * Input: void
     * Output: void
     * Purpose: Create the adapter for the recycler view and populate it with the book list
     * retrieved from the network
     */
    private void createList() {
        mBookAdapter = new BookAdapter(getApplicationContext(), mBooks);
        rvBooks.setAdapter(mBookAdapter);
        rvBooks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    /**
     * Name: getBooks
     * Input: void
     * Output: void
     * Purpose: Volley request to retrieve a JSONArray from the
     */
    private void getBooks() {
        ltSwipeRefresh.setRefreshing(true);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, BuildConfig.API_URL,null,
                // onSuccess
                response -> {
                    try {
                        // Dismiss the no network notice
                        noNetworkSnack.dismiss();
                        // Clear out the list so we can retrieve a fresh one, prevents duplicates
                        mBooks.clear();
                        for (int i=0; i < response.length(); i++) {
                            JSONObject jsonBook = response.getJSONObject(i);
                            // Create an object out of each item in the array
                            String bookTitle = jsonBook.optString("title", "N/A");
                            String bookImageUrl = jsonBook.optString("imageURL", null);
                            Book book = new Book(bookTitle, bookImageUrl);
                            mBooks.add(book);
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                    }

                    createList();
                    ltSwipeRefresh.setRefreshing(false);
                },
                // onError
                volleyError -> {
                    ltSwipeRefresh.setRefreshing(false);
                    showSnackBar("Error retrieving list, try again later.");
                });

        VolleyRequestSingleton.getInstance(getApplicationContext())
                .addToRequestQueue(jsonArrayRequest);
    }

    /**
     * Name: showSnackBar
     * Input:
     * @param message - message to display on snackbar
     * Output: void
     * Purpose: Shows snackbar with given message
     */
    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(content, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * Name: isNetworkAvailable
     * Input: void
     * Output: boolean, indicating available network or otherwise
     * Purpose: Uses the connectivity manager to check if the device has an available network
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
