package com.example.testiruem2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Object;
import java.lang.String;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    String baseLogin = "admin";
    String basePass = "12345678";
    EditText entLogin;
    EditText entPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_window);

        Button button1 = findViewById(R.id.button1);
        TextView textMain = findViewById(R.id.textview1);
        TextView error = findViewById(R.id.textview2);
        entLogin = findViewById(R.id.edit_user);
        entPass = findViewById(R.id.edit_password);
        Intent intent = new Intent(this, SecActivity.class);

        entPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            if ((entLogin.getText().toString().compareTo(baseLogin) == 0) && (entPass.getText().toString().compareTo(basePass) == 0))
            {
                    textMain.setText("Усшешно");
                //  setContentView(R.layout.activity_main);

                startActivity(intent);
            }
            else
            {
                    error.setText("Логин или пароль введены неверно!");
            }

            }
        });
    }

}