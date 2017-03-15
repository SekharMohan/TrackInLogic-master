package trackinlogic.trans.pss.com.trackinlogic.utils;

import android.content.Context;
import android.content.SharedPreferences;
public class LocalStorage {

    private final SharedPreferences sharedPreferences;

    public LocalStorage(Context context, String file) {
        this.sharedPreferences = context.getSharedPreferences(file, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return this.sharedPreferences.getString(key, defaultValue);
    }

    public void remove(String key) {
        this.sharedPreferences.edit().remove(key).apply();
    }
}
