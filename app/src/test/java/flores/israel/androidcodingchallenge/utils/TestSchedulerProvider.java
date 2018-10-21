package flores.israel.androidcodingchallenge.utils;

import android.support.annotation.NonNull;

import flores.israel.androidcodingchallenge.utils.schedulers.BaseSchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by israel on 10/21/18.
 */
public class TestSchedulerProvider implements BaseSchedulerProvider {

    private final TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        this.mTestScheduler = testScheduler;
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @NonNull
    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

}
