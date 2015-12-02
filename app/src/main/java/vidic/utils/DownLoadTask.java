package vidic.utils;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.File;

/**
 * Created by Administrator on 2015/12/1.
 * name:vidic
 */
public class DownLoadTask extends AsyncTask<String,Integer,File> {

    private DownLoadCallBack callBack=null;
    private DownLoadUtils downLoadUtils=null;

    public DownLoadTask(DownLoadCallBack callBack,Handler handler){
        this.callBack=callBack;
        downLoadUtils=new DownLoadUtils(handler);
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(callBack!=null){
            callBack.downLoadFinish(file);
        }
    }

    @Override
    protected File doInBackground(String... params) {
        return downLoadUtils.downLoad(params[0],params[1]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if(callBack!=null){
            callBack.downLoadFail();
        }
    }
}
