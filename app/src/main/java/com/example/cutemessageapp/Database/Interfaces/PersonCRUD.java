package com.example.cutemessageapp.Database.Interfaces;

import com.example.cutemessageapp.Person.Entity.Person;

import java.util.List;

public interface PersonCRUD {

    String TABLE_NAME ="person";
    String ID ="id";
    String NAME ="name";
    String PHONE_NUMBER ="phoneNum";


    final static String dropTablePerson="DROP TABLE IF EXISTS "+ TABLE_NAME;

    //table code here.
    String makeUserTable = String.format("CREATE TABLE %s " +
                    "(" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define person_id as primary key
                    "%s TEXT, " + // Define personName as TEXT
                    "%s TEXT" +  // Define personPhoneNum as TEXT
                    ")",
            TABLE_NAME,ID ,NAME,PHONE_NUMBER);



//create
    public void person_save(Person person);

//read
    public Person person_readOne(Integer id);

//read All
    public List<Person> person_readAll();

//delete
    public void person_delete(Integer id);


}