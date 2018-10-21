package flores.israel.androidcodingchallenge.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

import static flores.israel.androidcodingchallenge.utils.NetworkUtils.isNetworkAvailable;

/**
 * Created by israel on 10/21/18.
 */

public abstract class BaseActivity extends DaggerAppCompatActivity implements BaseMvpView {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public boolean isNetworkConnected() {
        return isNetworkAvailable(getApplicationContext());
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    /**
     * Abstract methods
     */

    protected abstract void setup();

    protected abstract void bindListeners();
}