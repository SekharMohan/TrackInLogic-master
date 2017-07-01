package trackinlogic.trans.pss.com.trackinlogic.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DELL on 01-07-2017.
 */

public class FileUtils {

    public static String loadJSONFromAsset(Context context, String country) {
        String json = null;
        String jsonFile = "";
        if(country.equals("Canada")){
            jsonFile = "canada.json";
        }else if(country.equals("United States")){
            jsonFile = "unitedstates.json";
        }else if(country.equals("Mexico")){
            jsonFile = "mexico.json";
        }
        try {
            AssetManager assetManager = context.getResources().getAssets();
            InputStream is = assetManager.open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
