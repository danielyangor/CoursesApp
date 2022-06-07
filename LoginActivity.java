package com.danielyan.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.danielyan.mycourses.Model.Users;
import com.danielyan.mycourses.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private Button loginbtn;
    private EditText login_password_input;
    private EditText login_phone_input;
    private ProgressDialog loadingBar;

    private String parentDBName = "Users";
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = (Button) findViewById(R.id.login_btn);
        login_password_input =(EditText) findViewById(R.id.login_password_input);
        login_phone_input =(EditText) findViewById(R.id.login_phone_input);
        checkBoxRememberMe =(CheckBox)findViewById(R.id.login_checkbox);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });


        };





    private void loginUser() {
        String phonenumber = login_phone_input.getText().toString();
        String password = login_password_input.getText().toString();


        if (TextUtils.isEmpty(phonenumber)){

            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password)){

            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Вход в приложение");
            loadingBar.setMessage("Пожалуйста подождите....");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateUser(phonenumber,password);
        }

    }
    private void ValidateUser( String phonenumber, String password) {

        if (checkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey,phonenumber);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDBName).child(phonenumber).exists())
                {
                    Users usersData=dataSnapshot.child(parentDBName).child(phonenumber).getValue(Users.class);
                    Prevalent.currentOnlineUser = usersData;


                    if (usersData.getPhone().equals(phonenumber))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            if (parentDBName.equals("Users")){
                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(homeIntent);
                            }
                            else if (parentDBName.equals("Admins")){
                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();


                        }
                    }


                }
                else {
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Аккаунт с номером " + phonenumber + "не существует", Toast.LENGTH_SHORT).show();
                    Intent registrIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(registrIntent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }}