package com.danielyan.mycourses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.danielyan.mycourses.Model.Cart;
import com.danielyan.mycourses.Model.Course;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {


    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        loadingBar = new ProgressDialog(this);


        ListView cart_list=findViewById(R.id.cartList);
        List<String> coursesTitle = new ArrayList<>();
        for (Course c: HomeActivity.fullCoursesList){
            if (Cart.items_id.contains(c.getId()))
                coursesTitle.add(c.getTitle());

        }
        cart_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,coursesTitle));

    }

    public void openSettings(View view){

        Intent settings =new Intent(this,SettingsActivity.class);
        startActivity(settings);

    }
    public void openAbout(View view){

        Intent About =new Intent(this,AboutActivity.class);
        startActivity(About);

    }
    public void openHome(View view){

        Intent home =new Intent(this,HomeActivity.class);
        startActivity(home);

    }
    public void openContacts(View view){

        Intent home =new Intent(this,ContactsActivity.class);
        startActivity(home);

    }

    public void addToOrder(View view){

        Toast.makeText(this, "Заказ оформлен ждите звонка оператора", Toast.LENGTH_LONG).show();

        }




    }

