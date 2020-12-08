package com.example.beststudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends ArrayAdapter<CourseDetail> {
    private Context CourseContext;
    private List<CourseDetail> courseInfo = new ArrayList();

    private static class ViewHolder {
        TextView hold;
    }

    public CourseAdapter(@NonNull Context context, ArrayList<CourseDetail> stuff) {
        super(context, 0, stuff);
        CourseContext = context;
        courseInfo = stuff;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View courseitem = convertView;
        if(courseitem == null) {
            courseitem = LayoutInflater.from(CourseContext).inflate(R.layout.course_item, parent, false);
        }
        CourseDetail course = courseInfo.get(position);
        TextView name = (TextView)courseitem.findViewById(R.id.textView_courseName);
        name.setText(course.getClassName());

        TextView day = (TextView) courseitem.findViewById(R.id.textView_courseDays);
        day.setText(course.getClassDay());

        TextView start = (TextView) courseitem.findViewById(R.id.textView_courseStart);
        start.setText(course.getClassStart());

        TextView end = (TextView) courseitem.findViewById(R.id.textView_courseEnd);
        end.setText(course.getClassEnd());

        TextView teacher = (TextView) courseitem.findViewById(R.id.textView_ProfName);
        teacher.setText(course.getClassProf());

        TextView link = (TextView) courseitem.findViewById(R.id.textView_courseLink);
        link.setText(course.getClassLink());

        return courseitem;
    }

}