package com.example.cutemessageapp.Database;

import com.example.cutemessageapp.Database.Entities.Message;

import java.util.List;

public interface MessageCRUD {

    String msgTableName="cuteMessages";

    String dropTableMessage="DROP TABLE IF EXISTS "+msgTableName;

    String id="id";
    String name="name";
    String type="type";

    //need to have a relationship of th
    String makeMessageTable = String.format("CREATE TABLE %s " +
                    "(" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define id as primary key
                    "%s TEXT, " + // Define name as TEXT
                    "%s TEXT" +  // Define type as TEXT
                    ")",
            msgTableName, id, name, type);


    //create
    void messageSave(Message message);

    //read one
    List<Message> readAllMessages();

    //read all
    Message readOneMessage(Integer id);


    //delete
    void deleteOneMessage(Integer id);

}
