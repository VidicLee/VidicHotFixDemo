package vidic.utils;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import vidic.hotupdataapp.App;

/**
 * Created by Administrator on 2015/12/1.
 * name:vidic
 */
public class DownLoadUtils {

    private String filePath= App.getInstance().getFilesDir().getAbsolutePath();

    File file=null;

    Handler handler=null;

    public DownLoadUtils(Handler handler){
        this.handler=handler;
    }

    public File downLoad(String url,String fileName){
        try {
            URL downLoadUrl=new URL(url);
            URLConnection connection=downLoadUrl.openConnection();
            if(writeToSDcard(fileName,connection)){
                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean writeToSDcard(String fileName,URLConnection connection){
        file=new File(filePath,fileName);
        if(file.exists()&&file.isFile()){
            file.delete();
        }
        FileOutputStream outputStream=null;
        try {
            InputStream inputStream=connection.getInputStream();
            file.createNewFile();
            outputStream=new FileOutputStream(file);
            byte[] buffer=new byte[1024];
            while ((inputStream.read(buffer)!=-1)){
                outputStream.write(buffer);
                if(handler!=null){
                    Message msg=handler.obtainMessage();
                    msg.what=2;
                    msg.arg1=connection.getContentLength();
                    msg.obj=file.length();
                    handler.sendMessage(msg);
                }
            }
            inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{outputStream.close();}catch (Exception e){e.printStackTrace();}
        }
        return false;
    }
}