package com.example.cutemessageapp.Person.Entity;

import org.intellij.lang.annotations.Identifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Person {
    private Integer id;

    private String name;
    private Long phoneNumber;

    public Person(Integer id, String name, Long phoneNumber){
        this.id=id;
        this.name=name;
        this.phoneNumber =phoneNumber;
    }

    public Person(String name, Long phoneNumber){
        this.name=name;
        this.phoneNumber =phoneNumber;
    }

    //the lombok dosent work >:(
    public String getName(){return  this.name;}
    public Long getPhoneNumber(){return this.phoneNumber;}

    public Integer getId(){return this.id;}


}
