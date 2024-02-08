package com.example.cutemessageapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cutemessageapp.Database.Entities.Message;
import com.example.cutemessageapp.Database.Entities.Person;

import java.util.List;

public class Db extends SQLiteOpenHelper
    implements  PersonCRUD, MessageCRUD

{

    final static Integer dbVersion=1;
    final static String dbName="cuteMessages";

    public Db(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonCRUD.makeUserTable);
        db.execSQL(MessageCRUD.makeMessageTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PersonCRUD.dropTablePerson);
        db.execSQL(MessageCRUD.dropTableMessage);
        onCreate(db);
    }




//Message Section
    @Override
    public void messageSave(Message message) {

    }

    @Override
    public List<Message> readAllMessages() {
        return null;
    }

    @Override
    public Message readOneMessage(Integer id) {
        return null;
    }

    @Override
    public void deleteOneMessage(Integer id) {

    }


//Person Section
    @Override
    public void savePeron(Person person) {

    }

    @Override
    public Person readOnePerson(Integer id) {
        return null;
    }

    @Override
    public List<Person> readAll() {
        return null;
    }

    @Override
    public void deletePerson(Integer id) {

    }




}
