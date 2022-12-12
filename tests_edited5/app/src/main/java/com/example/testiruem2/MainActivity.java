package com.example.testiruem2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;



public class MainActivity extends Activity {
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_window);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                User userEx = new User(user, pass);

                if (userEx.getPass() == "qwerty")
                    Toast.makeText(MainActivity.this, "Пароль не соответствует требованиямбезопасности", Toast.LENGTH_SHORT).show();
                else {
                    if (user.equals("") || pass.equals("") || repass.equals(""))
                        Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else {
                        if (pass.equals(repass)) {
                            Boolean checkuser = DB.checkusername(userEx);
                            if (checkuser == false) {
                                Boolean insert = DB.insertData(userEx);
                                if (insert == true) {
                                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("AppLogger", "Переопределение onStop у MainActivity");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("AppLogger", "Переопределение onStart у MainActivity");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("AppLogger", "Переопределение onPause у MainActivity");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("AppLogger", "Переопределение onResume у MainActivity");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("AppLogger", "Переопределение onRestart у MainActivity");
    }
//
}