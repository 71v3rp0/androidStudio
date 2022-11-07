package com.example.testiruem2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Object;
import java.lang.String;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    String baseLogin = "admin";
    String basePass = "12345678";
    EditText entLogin;
    EditText entPass;
    TextView textMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_window);

        Button button1 = findViewById(R.id.button1);

        TextView error = findViewById(R.id.textview2);
        entLogin = findViewById(R.id.edit_user);
        entPass = findViewById(R.id.edit_password);
        Intent intent = new Intent(this, SecActivity.class);

        entPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        Toast.makeText(MainActivity.this,
                "Переопределение onCreate у MainActivity", Toast.LENGTH_SHORT).show();
        Log.i("AppLogger", "Переопределение onCreate у MainActivity");

        final FrameLayout frameLayout;
        frameLayout = findViewById(R.id.FL1);

        textMain = findViewById(R.id.textview1);


        Button buttonG = findViewById(R.id.colorGreen);
        Button buttonB = findViewById(R.id.colorBlue);
        Button buttonP = findViewById(R.id.colorPurple);
        Button buttonO = findViewById(R.id.colorOrange);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            if ((entLogin.getText().toString().compareTo(baseLogin) == 0) && (entPass.getText().toString().compareTo(basePass) == 0))
            {
                    //textMain.setText("Усшешно");
                    Toast.makeText(getApplicationContext(), "Вход выполнен успешно!",Toast.LENGTH_SHORT).show();
                    String intentMessage = entLogin.getText().toString();
                    intent.putExtra("Lab3", intentMessage);
                    intent.putExtra("color", textMain.getText().toString());
                //  setContentView(R.layout.activity_main);

                startActivity(intent);
            }
            else
            {
                    error.setText("Логин или пароль введены неверно!");
            }

            }
        });

        buttonG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                frameLayout.setBackgroundResource(R.color.green);
                textMain.setText("Green");
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                frameLayout.setBackgroundResource(R.color.blue);
                textMain.setText("Blue");
            }
        });
        buttonP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                frameLayout.setBackgroundResource(R.color.purple);
                textMain.setText("Purple");
            }
        });
        buttonO.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                frameLayout.setBackgroundResource(R.color.orange);
                textMain.setText("Orange");
            }
        });
    }

    @Override
    protected void onStop(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("text", textMain.getText().toString());
        editor.apply();

        super.onStop();
        Log.i("AppLogger", "Переопределение onStop у MainActivity");
    }
    @Override
    protected void onStart(){
        SharedPreferences mSharedPreference1 = this.getPreferences(Context.MODE_PRIVATE);
        textMain = findViewById(R.id.textview1);
        textMain.setText(mSharedPreference1.getString("text", null));

        final FrameLayout frameLayout;
        frameLayout = findViewById(R.id.FL1);

        if (textMain.getText().toString().compareTo("Purple") == 0)
            frameLayout.setBackgroundResource(R.color.purple);
        if (textMain.getText().toString().compareTo("Green") == 0)
            frameLayout.setBackgroundResource(R.color.green);
        if (textMain.getText().toString().compareTo("Blue") == 0)
            frameLayout.setBackgroundResource(R.color.blue);
        if (textMain.getText().toString().compareTo("Orange") == 0)
            frameLayout.setBackgroundResource(R.color.orange);

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

}