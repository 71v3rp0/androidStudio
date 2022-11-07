package com.example.testiruem2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Collections;


public class SecActivity extends AppCompatActivity {

        ArrayList<String> users = new ArrayList<String>();
        ArrayList<String> selectedUsers = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        ListView usersList;
        TextView textMain;
        TextView textColor;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);


            // добавляем начальные элементы
            //Collections.addAll(users, "Tom", "Bob", "Sam", "Alice");
            // получаем элемент ListView

            Log.i("AppLogger", "Welcome!");
            String accountName = getIntent().getExtras().getString("Lab3");



            Collections.addAll(users, accountName);

            usersList = findViewById(R.id.usersList);
            Button sall = findViewById(R.id.selectall);
            Button uall = findViewById(R.id.unselectall);
            // создаем адаптер
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, users);
            // устанавливаем для списка адаптер
            usersList.setAdapter(adapter);

            TextView textview1 = findViewById(R.id.textview1);
            textview1.setText(String.valueOf(accountName));

            // обработка установки и снятия отметки в списке
            usersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id)
                {
                    // получаем нажатый элемент
                    String user = adapter.getItem(position);

                    if(usersList.isItemChecked(position))
                        selectedUsers.add(user);
                    else
                        selectedUsers.remove(user);

                }
            });
            sall.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i=0; i < usersList.getChildCount(); i++) {

                        usersList.setItemChecked(i, true);
                        String user = adapter.getItem(i);
                        selectedUsers.add(user);

                    }
                    adapter.notifyDataSetChanged();

                }
            });
            uall.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i=0; i < usersList.getChildCount(); i++) {

                        usersList.setItemChecked(i, false);
                        String user = adapter.getItem(i);
                        selectedUsers.remove(user);

                    }

                    adapter.notifyDataSetChanged();

                }
            });
        }
        @Override
        protected void onStop(){

            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            /* users is an array */
            editor.putInt("Status_size", users.size());

            for(int i=0;i<users.size();i++)
            {
                editor.remove("Status_" + i);
                editor.putString("Status_" + i, users.get(i));
            }
            editor.apply();

            super.onStop();
            Log.i("AppLogger", "Переопределение onStop у TableActivity");
        }
        @Override
        protected void onStart(){
            SharedPreferences mSharedPreference1 = this.getPreferences(Context.MODE_PRIVATE);
            users.clear();
            int size = mSharedPreference1.getInt("Status_size", 0);

            for(int i=0;i<size;i++)
            {
                users.add(mSharedPreference1.getString("Status_" + i, null));
            }

            textMain = findViewById(R.id.textview1);
            textColor = findViewById(R.id.color);
            textColor.setText(getIntent().getExtras().getString("color"));
            final ConstraintLayout cLayout;
            cLayout = findViewById(R.id.FL2);

            if (textColor.getText().toString().compareTo("Purple") == 0)
                cLayout.setBackgroundResource(R.color.purple);
            if (textColor.getText().toString().compareTo("Green") == 0)
                cLayout.setBackgroundResource(R.color.green);
            if (textColor.getText().toString().compareTo("Blue") == 0)
                cLayout.setBackgroundResource(R.color.blue);
            if (textColor.getText().toString().compareTo("Orange") == 0)
                cLayout.setBackgroundResource(R.color.orange);
            textColor.setText("");
            super.onStart();
            Log.i("AppLogger", "Переопределение onStart у TableActivity");
        }
        @Override
        protected void onPause(){
            super.onPause();
            Log.i("AppLogger", "Переопределение onPause у TableActivity");
        }
        @Override
        protected void onResume(){
            super.onResume();
            Log.i("AppLogger", "Переопределение onResume у TableActivity");
        }

        @Override
        protected void onRestart(){
            super.onRestart();
            Log.i("AppLogger", "Переопределение onRestart у TableActivity");
        }

        public void add(View view){

            EditText userName = findViewById(R.id.userName);
            String user = userName.getText().toString();
            if(!user.isEmpty()){
                adapter.add(user);
                userName.setText("");
                adapter.notifyDataSetChanged();
            }
        }
        public void remove(View view){
            // получаем и удаляем выделенные элементы
            for(int i=0; i< selectedUsers.size();i++){
                adapter.remove(selectedUsers.get(i));
            }
            // снимаем все ранее установленные отметки
            usersList.clearChoices();
            // очищаем массив выбраных объектов
            selectedUsers.clear();

            adapter.notifyDataSetChanged();
        }




    }

