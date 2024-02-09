package com.example.cutemessageapp.Database;

import com.example.cutemessageapp.Database.Entities.Person;

import java.util.List;

public interface PersonCRUD {

    String personTableName="person";
    String person_id="id";
    String personName="name";
    String personPhoneNum="phoneNum";


    final static String dropTablePerson="DROP TABLE IF EXISTS "+personTableName;

    //table code here.
    String makeUserTable = String.format("CREATE TABLE %s " +
                    "(" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define person_id as primary key
                    "%s TEXT, " + // Define personName as TEXT
                    "%s TEXT" +  // Define personPhoneNum as TEXT
                    ")",
            personTableName, "person_id", "personName", "personPhoneNum");



//create
    public void person_save(Person person);

//read
    public Person person_readOne(Integer id);

//read All
    public List<Person> person_readAll();

//delete
    public void person_delete(Integer id);


}