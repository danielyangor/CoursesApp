package com.danielyan.mycourses.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.danielyan.mycourses.CoursePageActivity;
import com.danielyan.mycourses.Model.Course;
import com.danielyan.mycourses.R;

import java.util.List;

public class CourseAdapter extends  RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View coursesItems= LayoutInflater.from(context).inflate(R.layout.courses_item,parent,false);
        return new CourseAdapter.CourseViewHolder(coursesItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        holder.CourseBg.setCardBackgroundColor(Color.parseColor(courses.get(position).getColor()));

        int imageId = context.getResources().getIdentifier("ic_"+courses.get(position).getImg(),"drawable",context.getPackageName());
        holder.CourseImage.setImageResource(imageId);

        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseDate.setText(courses.get(position).getDate());
        holder.courselevel.setText(courses.get(position).getLevel());
        holder.coursePrice.setText(courses.get(position).getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, CoursePageActivity.class);

                intent.putExtra("CourseBg",Color.parseColor(courses.get(position).getColor()));
                intent.putExtra("CourseImage",imageId);
                intent.putExtra("courseTitle",courses.get(position).getTitle());
                intent.putExtra("courseDate",courses.get(position).getDate());
                intent.putExtra("courselevel",courses.get(position).getLevel());
                intent.putExtra("coursePrice",courses.get(position).getPrice());
                intent.putExtra("courseText",courses.get(position).getText());
                intent.putExtra("courseId",courses.get(position).getId());


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        CardView CourseBg;
        ImageView CourseImage;
        TextView courseTitle,courseDate,courselevel,coursePrice;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            CourseBg=itemView.findViewById(R.id.CourseBg);
            CourseImage=itemView.findViewById(R.id.courseImage);
            courseTitle=itemView.findViewById(R.id.courseTitle);
            courseDate=itemView.findViewById(R.id.courseDate);
            courselevel=itemView.findViewById(R.id.courselevel);
            coursePrice=itemView.findViewById(R.id.coursePrice);

        }


    }

}
