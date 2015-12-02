package vidic.hotupdataapp;

import android.app.Application;
import android.content.Context;
import vidic.fixbug.Fixbug;


/**
 * Created by Administrator on 2015/11/26.
 * name:vidic
 */
public class App extends Application{

    private static App instance=null;

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Fixbug.init(this);
        Fixbug.loadPatch(this,getFilesDir().getAbsolutePath()+"/fix_bug_dex.jar");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static App getInstance(){
        return  instance;
    }
}
