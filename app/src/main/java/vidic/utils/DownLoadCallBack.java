package vidic.utils;

import java.io.File;

/**
 * Created by Administrator on 2015/12/1.
 * name:vidic
 */
public interface DownLoadCallBack{

    void downLoadFinish(File file);
    void downLoadFail();
}
