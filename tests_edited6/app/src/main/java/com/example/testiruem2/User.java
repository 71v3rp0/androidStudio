package com.example.testiruem2;

public class User {

    int _id;
    String _login;
    String _pass;
    String _newPass;

    public User(){
    }
    public User(String login){
        this._login = login;
    }
    public User(String login, String pass){
        this._login = login;
        if (pass.length() > 3) {
            this._pass = pass;
        }
        else
        {
            this._pass = "qwerty";
        }
    }
    public User(String login, String pass, String newPass){
        this._login = login;
        if (pass.length() > 3 || newPass.length() > 3) {
            this._pass = pass;
            this._newPass = newPass;
        }
        else
        {
            this._pass = "qwerty";
            this._newPass = "qwerty";
        }
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getLogin(){
        return this._login;
    }
    public void setLogin(String login){
        this._login = login;
    }

    public String getPass(){
        return this._pass;
    }
    public void setPass(String pass){
        this._pass = pass;
    }

    public String getNewPass(){
        return this._newPass;
    }

}

