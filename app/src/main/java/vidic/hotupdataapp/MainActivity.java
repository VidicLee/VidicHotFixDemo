package vidic.hotupdataapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import vidic.net.TestWebService;
import vidic.utils.DownLoadCallBack;
import vidic.utils.DownLoadTask;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.result)
    TextView result;

    @InjectView(R.id.progress_lable)
    TextView progress_lable;

    @InjectView(R.id.lable_layout)
    LinearLayout lable_layout;

    @OnClick(R.id.getUrl)
    void getUrl(){
        Intent intent=new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    ProgressDialog dialog=null;

    private void showUpdataDialong(final String url){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("检测到有新版本，需要更新后才能使用，请更新！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lable_layout.setVisibility(View.VISIBLE);
                downLoadFix(url);
            }
        });
        builder.show();
    }

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==2){
                progress_lable.setText(msg.obj+"/"+msg.arg1);
            }
            return false;
        }
    });

    private void downLoadFix(String url) {
        new DownLoadTask(new DownLoadCallBack() {
            @Override
            public void downLoadFinish(File file) {
                if(file!=null&&file.exists()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("下载完成，请重启应用");
                    builder.setPositiveButton("好现在重启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Process.killProcess(Process.myPid());
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void downLoadFail() {

            }
        },handler).execute(url,"fix_bug_dex.jar");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("正在检测运行环境...");
        result.setText(new LoadBugClass().say());
        File file=new File(getFilesDir().getAbsoluteFile(),"fix_bug_dex.jar");
        if(file.exists()){
            //Check local jar is new
        }else{
            checkUpdata();
        }
    }

    private void checkUpdata(){
        dialog.show();
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                String s= TestWebService.checkIfHasBug();
                Log.i("vidic","result="+s);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson=new Gson();
                Result result=gson.fromJson(s,Result.class);
                if(result.bSucess){
                    dialog.dismiss();
                    if(result.isHasBug){
                        showUpdataDialong(result.fixUrl);
                        Log.i("vidic", "fixUrl=" + result.fixUrl);
                    }
                }else{
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
