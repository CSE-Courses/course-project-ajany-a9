package com.example.beststudy;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class AssignmentAdapter extends ArrayAdapter<AssignmentItem> {
    private static class ViewHolder{
        TextView name;
    }

    private ArrayList<AssignmentItem> mAssignments;
    private Context mContext;

    public AssignmentAdapter(Context context, ArrayList<AssignmentItem> objects) {
        super(context, 0, objects);
        mAssignments = objects;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AssignmentItem item = mAssignments.get(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.activity_mylist,parent,false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.activityDescription);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (item.mStatus.equals("UNSTARTED")) {
            convertView.setBackgroundColor(Color.WHITE);
        }
        else if (item.mStatus.equals("inProgress")) {
            convertView.setBackgroundColor(Color.rgb(255,255,102));
        }
        else if (item.mStatus.equals("completed")) {
            convertView.setBackgroundColor(Color.rgb(204,255,204));
        }
        else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        viewHolder.name.setText(item.mDescription);
        convertView.setMinimumHeight(150);
        return convertView;
    }

}
