package com.example.cutemessageapp.Database.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    public static enum Type{cute, loving, sweet, caring, encouraging, futureOriented}


    private Integer id;
    private String message;
    private Type type;

    public Message(String message, Type type ){
        this.message= message;
        this.type=type;
    }

    public String getMessage(){
        return  this.message;
    }


}
