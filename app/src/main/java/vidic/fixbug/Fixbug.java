package vidic.fixbug;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2015/11/27.
 * name:vidic
 */
public class Fixbug {

    private static final String TAG = "vidic";
    private static final String HACK_DEX = "hack_dex.jar";

    private static final String DEX_DIR = "vidic";
    private static final String DEX_OPT_DIR = "vidicopt";


    public static void init(Context context) {
        File dexDir = new File(context.getFilesDir(), DEX_DIR);
        dexDir.mkdir();

        String dexPath = null;
        try {
            dexPath = AssetUtils.copyAsset(context, HACK_DEX, dexDir);
        } catch (IOException e) {
            Log.i(TAG, "copy " + HACK_DEX + " failed");
            e.printStackTrace();
        }

        loadPatch(context, dexPath);
    }

    public static void loadPatch(Context context, String dexPath) {

        if (context == null) {
            Log.i(TAG, "context is null");
            return;
        }
        if (!new File(dexPath).exists()) {
            Log.i(TAG, dexPath + " is null");
            return;
        }
        File dexOptDir = new File(context.getFilesDir(), DEX_OPT_DIR);
        dexOptDir.mkdir();
        try {
            DexUtils.injectDexAtFirst(dexPath, dexOptDir.getAbsolutePath());
        } catch (Exception e) {
            Log.i(TAG, "inject " + dexPath + " failed");
            e.printStackTrace();
        }
    }

}
