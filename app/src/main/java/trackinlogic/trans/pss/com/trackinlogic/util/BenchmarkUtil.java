package trackinlogic.trans.pss.com.trackinlogic.util;

import java.util.HashMap;
import java.util.Map;

public class BenchmarkUtil {
    private static final String TAG = "BenchMarkUtil";
    private static boolean enable = false;
    private static Map<Object, Long> startTimes = new HashMap();

    public static void enable(boolean enable) {

        enable = enable;
    }

    public static void start(Object id) {
        if (enable) {
            startTimes.put(id, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static void stop(Object id) {
        if (enable) {
            Long start = (Long) startTimes.get(id);
            if (start != null) {
                int elapsed = (int) (System.currentTimeMillis() - start.longValue());

            }
        }
    }
}
