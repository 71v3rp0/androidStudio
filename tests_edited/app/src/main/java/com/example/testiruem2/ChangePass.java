package com.example.testiruem2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChangePass extends AppCompatActivity {
    EditText oldPassword;
    EditText newPassword;
    EditText logChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_table);

        logChange = findViewById(R.id.curlogin);
        oldPassword = findViewById(R.id.oldpass);
        newPassword = findViewById(R.id.newpass);


        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        Button change = (Button) findViewById(R.id.change_pass_bttn);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accName = logChange.getText().toString();
                String oldPass = oldPassword.getText().toString();
                String newPass = newPassword.getText().toString();
                User user = new User(accName, oldPass);

                if(!dbHelper.checkusernamepassword(user)){
                    Toast.makeText(ChangePass.this, "Ошибка. Неправильный пароль.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (oldPass.equals("") || newPass.equals(""))
                {
                    Toast.makeText(ChangePass.this, "Заполните поля изменения пароля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbHelper.checkusernamepassword(user))
                {
                    User userC = new User(accName, oldPass, newPass);

                    if(dbHelper.changePassword(userC))
                        Toast.makeText(ChangePass.this, "Пароль успішно змінено", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ChangePass.this, "Пароль не соответствует требованиям безопсности", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
    });
    }
}