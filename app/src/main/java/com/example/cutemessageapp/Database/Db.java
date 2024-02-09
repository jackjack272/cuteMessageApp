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
    public void message_save(Message message) {

    }

    @Override
    public List<Message> message_readAll() {
        return null;
    }

    @Override
    public Message message_readOne(Integer id) {
        return null;
    }

    @Override
    public Message message_readRandomOne(Message.Type type) {
        return null;
    }

    @Override
    public void message_delete(Integer id) {

    }


//Person Section
    @Override
    public void person_save(Person person) {

    }

    @Override
    public Person person_readOne(Integer id) {
        return null;
    }

    @Override
    public List<Person> person_readAll() {
        return null;
    }

    @Override
    public void person_delete(Integer id) {

    }




}
