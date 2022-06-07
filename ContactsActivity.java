package com.danielyan.mycourses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


    }
    public void openSettings(View view){

        Intent settings =new Intent(this,SettingsActivity.class);
        startActivity(settings);

    }
    public void openHome(View view){

        Intent home =new Intent(this,HomeActivity.class);
        startActivity(home);


    }
    public void openAbout(View view){

        Intent home =new Intent(this,AboutActivity.class);
        startActivity(home);

    }
}
