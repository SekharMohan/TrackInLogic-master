package trackinlogic.trans.pss.com.trackinlogic.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

public class BitmapHolder {
    private static final String TAG = "BitmapHolder";
    private static HashMap<String, Bitmap> cachedBitmaps = new HashMap();

    public static Bitmap getBitmap(Resources resources, int width, int height, int resID) {
        String id = String.format("%d_%dx%d", new Object[]{Integer.valueOf(resID), Integer.valueOf(width), Integer.valueOf(height)});
        if (cachedBitmaps.get(id) == null) {
            Bitmap scaledBitmap;
            Bitmap bitmap = BitmapFactory.decodeResource(resources, resID);
            if (height == 0) {
                scaledBitmap = Util.getResizedBitmapFromWidth(bitmap, (float) width);
            } else if (width == 0) {
                scaledBitmap = Util.getResizedBitmapFromHeight(bitmap, (float) height);
            } else {
                scaledBitmap = Util.getResizedBitmap(bitmap, (float) width, (float) height);
            }
            cachedBitmaps.put(id, scaledBitmap);
            if (bitmap != scaledBitmap) {
                bitmap.recycle();
            }
        }
        return (Bitmap) cachedBitmaps.get(id);
    }

    public static Bitmap getBitmap(Resources resources, int resID) {
        String id = String.format("%d", new Object[]{Integer.valueOf(resID)});
        if (cachedBitmaps.get(id) == null) {
            BenchmarkUtil.start("createBitmap: " + id);
            cachedBitmaps.put(id, BitmapFactory.decodeResource(resources, resID));
            BenchmarkUtil.stop("createBitmap: " + id);
        }
        return (Bitmap) cachedBitmaps.get(id);
    }
}
