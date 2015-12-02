package vidic.hotupdataapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 * name:vidic
 */
public class ListAdapter extends BaseAdapter {

    Context context=null;
    private List<String> datas=new ArrayList<>();

    public ListAdapter(Context context){
        this.context=context;
        int i=20;
        while (i>0){
            datas.add("this is bug item"+(20-i));
            i--;
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView= (TextView) LayoutInflater.from(context).inflate(R.layout.list_item,null);
        textView.setText(datas.get(position));
        return textView;
    }
}
