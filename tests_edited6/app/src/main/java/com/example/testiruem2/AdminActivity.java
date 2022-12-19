package com.example.testiruem2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    ArrayList<String> entries = new ArrayList<String>();
    ArrayList<String> selectedEntries = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView entriesList;
    DBHelper dbHelper = new DBHelper(this);


    String accountName;

    private SharedPreferences sharedPref;
    private final String saveTableKey = "save_table";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);
        accountName = getIntent().getExtras().getString("Lab5");

        TextView curUN = findViewById(R.id.curUN);
        curUN.setText(accountName);
        // добавляем начальные элементы
        Collections.addAll(entries);

        Log.i("AppLogger", "Переопределение onCreate у TableActivity");

        // получаем элемент ListView
        entriesList = findViewById(R.id.acc_table);
        // создаем адаптер
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, entries);
        // устанавливаем для списка адаптер
        entriesList.setAdapter(adapter);

        // обработка установки и снятия отметки в списке
        entriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // получаем нажатый элемент
                String entrie = adapter.getItem(position);
                if(entriesList.isItemChecked(position))
                    selectedEntries.add(entrie);
                else
                    selectedEntries.remove(entrie);
            }
        });

        Button deleleUser = (Button) findViewById(R.id.adm_del);
        deleleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    deleleUser.setEnabled(false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            removeAccount();

                            deleleUser.post(new Runnable() {
                                @Override
                                public void run() {
                                 deleleUser.setEnabled(true);
                                 restoreTable();
                                }
                            });
                        }
                    }).start();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_change, menu);
        return true;
    }
    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override
    protected void onStart(){
        restoreTable();
        super.onStart();

    }
    @Override
    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onRestart(){
        super.onRestart();

    }
    public List<User> restoreTable()
    {
        ArrayList<String> list = dbHelper.getDateList();
        List<User> usersList = new ArrayList<User>();
        adapter.clear();
        entries.clear();

        for(int i=0; i < list.size(); i = i + 2)
        {
            User user = new User();
            entries.add(list.get(i) + " " + list.get(i+1));
            user.setID(i);
            user.setLogin(list.get(i));
            user.setPass(list.get(i+1));
            usersList.add(user);
        }
        return usersList;
    }

    public void removeAccount(){
        EditText accountET = findViewById(R.id.adm_acc_entrie);
        String login  = accountET.getText().toString();
        User user = new User(login);
        if(dbHelper.deleteUser(user))
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Пользователь " + user.getLogin() + " успешно удалён", Toast.LENGTH_SHORT).show();
                }
            });

        else
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Ошибка удаления", Toast.LENGTH_SHORT).show();
                }
            });


    }
    public void ChangePass(MenuItem menuItem)
    {
        Intent intent  = new Intent(getApplicationContext(), ChangePass.class);
        intent.putExtra("Lab5", accountName);
        startActivity(intent);
    }

}