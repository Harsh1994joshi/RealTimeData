package com.example.realtime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdatper extends ArrayAdapter<Model> {

    Activity activity;
    List<Model> list;

    public MyAdatper( Activity activity, List<Model> list) {
        super(activity,R.layout.listdata,list);
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView=layoutInflater.inflate(R.layout.listdata,null,true);
       TextView ID= convertView.findViewById(R.id.id);
        TextView NAME=convertView.findViewById(R.id.fetchname);
        TextView EMAIL=convertView.findViewById(R.id.fetchemail);
        TextView CONTACT=convertView.findViewById(R.id.fetchcontact);

        Model model=list.get(position);

        ID.setText(model.getID());
        NAME.setText(model.getName());
        EMAIL.setText(model.getEmail());
        CONTACT.setText(model.getContact());

        return convertView;

    }
}
