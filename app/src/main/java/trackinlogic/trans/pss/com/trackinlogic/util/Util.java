package trackinlogic.trans.pss.com.trackinlogic.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Util {
    private static final String TAG = "Util";

    public static void safePut(Map<String, String> map, String key, boolean value, boolean encode) {
        safePut((Map) map, key, String.valueOf(value), encode);
    }

    public static void safePut(Map<String, String> map, String key, int value, boolean encode) {
        safePut((Map) map, key, String.valueOf(value), encode);
    }

    public static void safePut(Map<String, String> map, String key, String value, boolean encode) {
        if (key != null && key.length() > 0 && value != null && value.length() > 0) {
            if (encode) {
                map.put(Uri.encode(key), Uri.encode(value));
            } else {
                map.put(key, value);
            }
        }
    }

    public static Bitmap getResizedBitmapFromWidth(Bitmap bitmap, float newWidth) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        float newHeight = height * (newWidth / width);
        if (newWidth == width) {
            newHeight = height;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) newWidth, (int) newHeight, false);
    }

    public static Bitmap getResizedBitmapFromHeight(Bitmap bitmap, float newHeight) {
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * (newHeight / ((float) bitmap.getHeight()))), (int) newHeight, false);
    }

    public static Bitmap getResizedBitmap(Bitmap bitmap, float newWidth, float newHeight) {
        return Bitmap.createScaledBitmap(bitmap, (int) newWidth, (int) newHeight, false);
    }

    public static void safePutAppend(Map<String, String> map, String key, String value, boolean encode) {
        if (key != null && key.length() > 0 && value != null && value.length() > 0) {
            if (map.containsKey(key)) {
                value = ((String) map.get(key)) + "&" + key + "=" + value;
            }
            if (encode) {
                map.put(Uri.encode(key), Uri.encode(value));
            } else {
                map.put(key, value);
            }
        }
    }

    public static boolean isPointInCircle(float x, float y, float circleX, float circleY, int radius) {
        return Math.pow((double) (x - circleX), 2.0d) + Math.pow((double) (y - circleY), 2.0d) <= Math.pow((double) radius, 2.0d);
    }

    public static String safeGetString(String string) {
        if (TextUtils.isEmpty(string) || TextUtils.equals(string, "null")) {
            return "";
        }
        return string;
    }

    public static void setStringArrayPref(Context context, String prefID, String key, List<String> values) {
        Editor editor = context.getSharedPreferences(prefID, 0).edit();
        JSONArray array = new JSONArray();
        for (String value : values) {
            array.put(value);
        }
        editor.putString(key, !values.isEmpty() ? array.toString() : null);
        editor.commit();
    }

    public static ArrayList<String> getStringArrayPref(Context context, String prefID, String key) {
        String json = context.getSharedPreferences(prefID, 0).getString(key, null);
        ArrayList<String> urls = new ArrayList();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    urls.add(a.optString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    public static boolean isMyServiceRunning(Context context, Class serviceClass) {
        for (RunningServiceInfo service : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }






    public static String upperCaseFirstLetters(String text) {
        StringBuilder result = new StringBuilder(text.length());
        String[] words = text.split("\\s");
        int l = words.length;
        for (int i = 0; i < l; i++) {
            if (i > 0) {
                result.append(" ");
            }
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
        }
        return result.toString();
    }

    public static float dipToPixels(Context context, float dip) {
        return TypedValue.applyDimension(1, dip, context.getResources().getDisplayMetrics());
    }

    public static void expandTouchArea(final View bigView, final View smallView, final int extraPadding) {
        bigView.post(new Runnable() {
            public void run() {
                Rect rect = new Rect();
                smallView.getHitRect(rect);
                rect.top -= extraPadding;
                rect.left -= extraPadding;
                rect.right += extraPadding;
                rect.bottom += extraPadding;
                bigView.setTouchDelegate(new TouchDelegate(rect, smallView));
            }
        });
    }




    public static int getAppVersionCode(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
