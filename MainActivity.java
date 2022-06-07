package com.danielyan.mycourses.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.danielyan.mycourses.HomeActivity;
import com.danielyan.mycourses.LoginActivity;
import com.danielyan.mycourses.Model.Users;
import com.danielyan.mycourses.Prevalent.Prevalent;
import com.danielyan.mycourses.R;
import com.danielyan.mycourses.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button joinButton;
    private Button loginButton;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinButton =(Button) findViewById(R.id.main_join_btn);
        loginButton =(Button) findViewById(R.id.main_login_btn);
        Paper.init(this);
        loadingBar = new ProgressDialog(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        String UserPhoneKey= Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey= Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey !="" && UserPasswordKey!=""){

            if (!TextUtils.isEmpty(UserPhoneKey) &&!TextUtils.isEmpty(UserPasswordKey)){
                ValidateUser(UserPhoneKey,UserPasswordKey);
                loadingBar.setTitle("Вход в приложение");
                loadingBar.setMessage("Пожалуйста подождите....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();


            }
        }

    }
    private void ValidateUser(String phonenumber,final String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(phonenumber).exists())
                {
                    Users usersData=dataSnapshot.child("Users").child(phonenumber).getValue(Users.class);

                    if (usersData.getPhone().equals(phonenumber))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(homeIntent);
                        }
                        else {
                            loadingBar.dismiss();


                        }
                    }


                }
                else {
                    loadingBar.dismiss();
                    Toast.makeText(MainActivity.this, "Аккаунт с номером " + phonenumber + "не существует", Toast.LENGTH_SHORT).show();
                    Intent registrIntent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(registrIntent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    }
