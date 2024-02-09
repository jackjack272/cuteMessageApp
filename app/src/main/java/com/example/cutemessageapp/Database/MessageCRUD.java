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
    void message_save(Message message);

    //read one
    List<Message> message_readAll();

    //read all
    Message message_readOne(Integer id);

    //chose a randomOne
    Message message_readRandomOne(Message.Type type);

    //delete
    void message_delete(Integer id);

}
