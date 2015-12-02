package vidic.hotupdataapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView= (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new ListAdapter(this));
        //showToast();
    }

    private void showToast(){
        Toast.makeText(this,"This is fixBug version",Toast.LENGTH_SHORT).show();
    }

}
