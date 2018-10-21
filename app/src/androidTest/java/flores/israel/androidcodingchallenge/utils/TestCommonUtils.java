package flores.israel.androidcodingchallenge.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

/**
 * Created by israel on 10/21/18.
 */

public class TestCommonUtils {

    /**
     * Helps get a resource string from our XML
     * @param id
     * @return
     */
    public static String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }

}
