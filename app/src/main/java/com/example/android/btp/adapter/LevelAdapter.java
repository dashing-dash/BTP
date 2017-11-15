package com.example.android.btp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.btp.R;
import com.example.android.btp.other.Level;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pallav on 11/16/2017.
 */

public class LevelAdapter extends ArrayAdapter<Level> {

    private Activity context;
    private List<Level> levelList=new ArrayList<>();

    public LevelAdapter(@NonNull Activity context, List<Level> levelList) {
        super(context, 0,levelList);
        this.context=context;
        this.levelList=levelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem=convertView;
        if(listViewItem==null){
            listViewItem= LayoutInflater.from(context).inflate(R.layout.level_list_item,parent,false);
        }
        Level currentLevel=levelList.get(position);
        TextView textViewName=listViewItem.findViewById(R.id.tv_level_name);
        textViewName.setText(currentLevel.getName());

        return listViewItem;
    }
}
