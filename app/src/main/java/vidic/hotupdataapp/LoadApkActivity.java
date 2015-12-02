package vidic.hotupdataapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import vidic.net.TestWebService;

public class LoadApkActivity extends AppCompatActivity {

    @InjectView(R.id.result)
    TextView result;

    @OnClick(R.id.getUrl)
    void getUrl(){
        dialog.show();
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                String s= TestWebService.getUpdataUrl();
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s!=null){
                    dialog.dismiss();
                    result.setText(s);
                }else{
                    dialog.dismiss();
                    result.setText("Fail");
                }
            }
        }.execute();
    }

    ProgressDialog dialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
    }

    public void loadApk(){

    }
}
