package com.scnu.swimmingtrainingsystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.model.Athlete;

import java.util.List;

/**
 * 拖拽运动员适配器
 * Created by lixinkun on 15/12/23.
 */
public class DragAdapter extends ArrayAdapter<Athlete>{
    private Context context;

    private int mResouseId;
    private List<Athlete> list;

    public DragAdapter(Context context, int textViewResourceId,List<Athlete> list) {
        super(context, textViewResourceId);
        this.mResouseId = textViewResourceId;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Athlete getItem(int position) {
        return list.get(position);
    }

    @Override
    public void insert(Athlete object, int index) {
        list.add(index,object);
        this.notifyDataSetChanged();
    }

    @Override
    public void remove(Athlete object) {
        list.remove(object);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Athlete a = getItem(position);
        View v = View.inflate(context, mResouseId, null);
        TextView tvUserName = (TextView) v.findViewById(R.id.drag_list_item_text);
        tvUserName.setText(a.getName());
        return v;
    }

    public List<Athlete> getAthletes(){
        return list;
    }

}
