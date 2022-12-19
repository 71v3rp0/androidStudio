package com.example.testiruem2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }


    public Boolean insertData(User user){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", user.getLogin());
        contentValues.put("password", user.getPass());
        long result = MyDB.insert("users", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(User user) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{user.getLogin()});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(User user){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {user.getLogin(), user.getPass()});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public ArrayList<String> getDateList(){

        ArrayList<String> dateList = new ArrayList();
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor userCursor;


        userCursor = myDB.rawQuery("select " + "*" + " from " + "users", null);//Получаем все данные из таблицы TABLE(users)

        if (userCursor.moveToFirst()) {
            while (!userCursor.isAfterLast()) {
                @SuppressLint("Range") String username = userCursor.getString(userCursor.getColumnIndex("username"));
                @SuppressLint("Range") String password = userCursor.getString(userCursor.getColumnIndex("password"));
                dateList.add(username);
                dateList.add(password);
                // обрабатываем data
                // двигаемся к следующему значению
                userCursor.moveToNext();
            }
        }

// не забывайте закрыть курсор
        userCursor.close();
        return dateList;
    }
    public boolean deleteUser(User user){
        SQLiteDatabase myDB = this.getWritableDatabase();
        return myDB.delete("users", "username = ?", new String[]{user.getLogin()}) > 0;
    }
    public Boolean changePassword(User user){
        int count;
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getLogin());
        contentValues.put("password", user.getNewPass());
        Cursor cursor = myDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {user.getLogin(), user.getPass()});
        count = cursor.getCount();
        if(count == 1){
            myDB.update("users", contentValues, "username = ? and password = ?", new String[]{user.getLogin(), user.getPass()});
            myDB.close();
            return true;
        }
        else {
            myDB.close();
            return false;
        }


    }



}