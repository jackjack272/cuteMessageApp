package com.example.cutemessageapp.Message.Entity;

import com.example.cutemessageapp.Person.Adapter.PersonAdapter;

import lombok.Getter;


@Getter
public class Message {

    public static enum Type{cute, loving, sweet, caring, encouraging, futureOriented}


    private Integer id;
    private String message;
    private Type type;

    public Message(Integer id,  String message, Type type ){
        this.id=id;
        this.message= message;
        this.type=type;
    }


    public Message(String message, Type type ){
        this.message= message;
        this.type=type;
    }

    public String getMessage(){
        return  this.message;
    }
    public Type getType(){
        return this.type;
    }

    public Integer getId(){return  this.id;}



}
