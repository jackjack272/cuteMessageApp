package com.example.cutemessageapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cutemessageapp.Message.Entity.Message;
import com.example.cutemessageapp.Person.Entity.Person;
import com.example.cutemessageapp.Database.Interfaces.MessageCRUD;
import com.example.cutemessageapp.Database.Interfaces.PersonCRUD;

import java.util.ArrayList;
import java.util.List;

public class Db extends SQLiteOpenHelper
    implements PersonCRUD, MessageCRUD

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
        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv= new ContentValues();
            cv.put(MessageCRUD.message,message.getMessage());
            cv.put(MessageCRUD.type, message.getType().toString());

        db.insert(MessageCRUD.msg_tableName,null,cv);
    }

    @Override
    public List<Message> message_readAll() {
        String sqlQuery=String.format("SELECT * FROM %s",MessageCRUD.msg_tableName);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        List<Message> myMessages= new ArrayList<>();

        while (cursor.moveToNext()){
            myMessages.add(
                    new Message(
                            cursor.getInt(cursor.getColumnIndexOrThrow(MessageCRUD.id)),
                            cursor.getString(cursor.getColumnIndexOrThrow(MessageCRUD.message)),
                            Message.Type.valueOf(
                                    cursor.getString(cursor.getColumnIndexOrThrow(MessageCRUD.type))
                            )
                    )
            );
        }

        return myMessages;
    }

    public List<Message> message_readAll(Message.Type type) {
        String sqlQuery=String.format("SELECT * FROM %s WHERE %s == %s",MessageCRUD.msg_tableName, MessageCRUD.type,type);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        List<Message> myMessages= new ArrayList<>();

        while (cursor.moveToNext()){
            myMessages.add(
                    new Message(
                            cursor.getInt(cursor.getColumnIndexOrThrow(MessageCRUD.id)),
                            cursor.getString(cursor.getColumnIndexOrThrow(MessageCRUD.message)),
                            Message.Type.valueOf(
                                    cursor.getString(cursor.getColumnIndexOrThrow(MessageCRUD.type))
                            )
                    )
            );
        }

        return myMessages;
    }

    @Override
    public Message message_readOne(Integer id) {

        String sqlQuert=String.format("SELECT * FROM %s WHERE %s == %d",MessageCRUD.msg_tableName, MessageCRUD.id, id);

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sqlQuert, null);
        if(cursor.getCount() ==0){
            return null;
        }

        return new Message(
                cursor.getInt(cursor.getColumnIndexOrThrow(MessageCRUD.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MessageCRUD.message)),
                Message.Type.valueOf(
                        cursor.getString(cursor.getColumnIndexOrThrow(MessageCRUD.type))
                )
        );

    }

    @Override
    public Message message_readRandomOne(Message.Type type) {
        List<Message> opts= this.message_readAll(type);
        Integer available= opts.size();

        Integer position= (int) (Math.random()*available) %available;

        return opts.get(position);
    }

    @Override
    public void message_delete(Integer id) {
        getWritableDatabase().delete(
                MessageCRUD.msg_tableName,
                MessageCRUD.id+"==?",
                new String[]{String.valueOf(id)}
        );
    }


//Person Section
    @Override
    public void person_save(Person person) {

        ContentValues cv= new ContentValues();
        cv.put(PersonCRUD.NAME,person.getName());
        cv.put(PersonCRUD.PHONE_NUMBER, person.getPhoneNumber());


        SQLiteDatabase db= getWritableDatabase();
        db.insert(PersonCRUD.TABLE_NAME,null,cv);
    }



    @Override
    public Person person_readOne(Integer id) {
        String sqlQuery=String.format("SELECT * FROM %s WHERE %s ==%d",
                PersonCRUD.TABLE_NAME, PersonCRUD.ID, id);

        Cursor cursor= getReadableDatabase().rawQuery(sqlQuery, null);
        if(cursor.getCount()==0){
            return null;
        }

        return new Person(
            cursor.getInt(cursor.getColumnIndexOrThrow(PersonCRUD.ID)),
            cursor.getString(cursor.getColumnIndexOrThrow(PersonCRUD.NAME)),
            cursor.getLong(cursor.getColumnIndexOrThrow(PersonCRUD.PHONE_NUMBER))
        );
    }

    @Override
    public List<Person> person_readAll() {

        String sqlQuery= String.format("SELECT * FROM %s ",PersonCRUD.TABLE_NAME);
        Cursor cursor= getReadableDatabase().rawQuery(sqlQuery,null);
        cursor.moveToFirst();

        List<Person> people=new ArrayList<>();
        do{
            people.add(
                    new Person(
                            cursor.getInt(cursor.getColumnIndexOrThrow(PersonCRUD.ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(PersonCRUD.NAME)),
                            cursor.getLong(cursor.getColumnIndexOrThrow(PersonCRUD.PHONE_NUMBER))
                    )
            );
        }while(cursor.moveToNext());

        return people;
    }

    @Override
    public void person_delete(Integer id) {
        getWritableDatabase().delete(
                PersonCRUD.TABLE_NAME,
                PersonCRUD.ID +"==?",
                new String[]{String.valueOf(id)}
        );
    }
}
