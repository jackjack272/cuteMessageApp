package com.example.cutemessageapp.Database.Interfaces;

import com.example.cutemessageapp.Message.Entity.Message;

import java.util.List;

public interface MessageCRUD {

    String msg_tableName ="cuteMessages";

    String dropTableMessage="DROP TABLE IF EXISTS "+ msg_tableName;

    String id="id";
    String message ="message";
    String type="type";

    //need to have a relationship of th
    String makeMessageTable = String.format("CREATE TABLE %s " +
                    "(" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define id as primary key
                    "%s TEXT, " + // Define name as TEXT
                    "%s TEXT" +  // Define type as TEXT
                    ")",
            msg_tableName, id, message, type);


    //create
    void message_save(Message message);

    //read one
    List<Message> message_readAll();

    List<Message> message_readAll(Message.Type type);


    //read all
    Message message_readOne(Integer id);

    //chose a randomOne
    Message message_readRandomOne(Message.Type type);

    //delete
    void message_delete(Integer id);

}
