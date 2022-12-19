package com.example.testiruem2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    Button btnsgnup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnsgnup = (Button) findViewById(R.id.btnsignup1);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnlogin.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        login();
                        btnlogin.post(new Runnable() {
                            @Override
                            public void run() {
                                btnlogin.setEnabled(true);
                            }
                        });
                    }
                }).start();

            }
        });
    }
    public void login() {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        User cUser = new User(user, pass);

        if(user.equals("")||pass.equals(""))
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
            });
        else{
            Boolean checkuserpass = DB.checkusernamepassword(cUser);
            if(checkuserpass==true){
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), SecActivity.class);
                        intent.putExtra("Lab5", user);
                        startActivity(intent);
                    }
                });
            }else{
                this.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }
               });

            }
        }

    }
    public void goSU(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}