package app.extras;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageHelper {
    public static String persistImage(Bitmap bitmap, String name, Context _CONTEXT, String suffix, String dir) {
        File imageFile = new File(_CONTEXT.getExternalFilesDir(dir), name + suffix);

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception ignored) { }

        return  imageFile.getAbsolutePath();
    }
}

