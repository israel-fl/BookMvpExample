package israelfl.com.dejsontest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton class to generate volley requests on a queue as per documentation
 */
public class VolleyRequestSingleton {

    private static VolleyRequestSingleton mVolleyRequest;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private VolleyRequestSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestSingleton getInstance(Context context) {
        if (mVolleyRequest == null) {
            mVolleyRequest = new VolleyRequestSingleton(context);
        }
        return mVolleyRequest;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
