package com.danielyan.mycourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielyan.mycourses.Model.Cart;

public class CoursePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);



        ConstraintLayout CoursePage =findViewById(R.id.CoursePage);
        ImageView CoursePageImage = findViewById(R.id.CoursePageImage);
        TextView CoursePageTitle = findViewById(R.id.CoursePageTitle);
        CardView CoursePageCV = findViewById(R.id.CoursePageCV);
        TextView CoursePageDate = findViewById(R.id.CoursePageDate);
        TextView CoursePagelevel = findViewById(R.id.CoursePagelevel);
        TextView CoursePagePrice = findViewById(R.id.CoursePagePrice);
        TextView CoursePageText = findViewById(R.id.CoursePageText);
        ImageButton AddToCart = findViewById(R.id.AddToCart);

        CoursePage.setBackgroundColor(getIntent().getIntExtra("CourseBg",0));
        CoursePageImage.setImageResource(getIntent().getIntExtra("CourseImage",0));
        CoursePageTitle.setText(getIntent().getStringExtra("courseTitle"));
        CoursePageDate.setText(getIntent().getStringExtra("courseDate"));
        CoursePagelevel.setText(getIntent().getStringExtra("courselevel"));
        CoursePagePrice.setText(getIntent().getStringExtra("coursePrice"));
        CoursePageText.setText(getIntent().getStringExtra("courseText"));






    }
    public void openSettings(View view){

        Intent settings =new Intent(this,SettingsActivity.class);
        startActivity(settings);

    }
    public void openAbout(View view){

        Intent About =new Intent(this,AboutActivity.class);
        startActivity(About);

    }
    public void openContacts(View view){

        Intent home =new Intent(this,ContactsActivity.class);
        startActivity(home);

    }
    public void openHome(View view){

        Intent home =new Intent(this,HomeActivity.class);
        startActivity(home);

    }

    public void addToCart(View view){
        int item_id= getIntent().getIntExtra("courseId",0);
        Cart.items_id.add(item_id);
        Toast.makeText(this, "Успешно добавлено", Toast.LENGTH_SHORT).show();

    }

}