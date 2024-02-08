package com.example.cutemessageapp.Database.Entities;

import org.intellij.lang.annotations.Identifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private Integer id;

    private String name;
    private Long phoneNumber;

    public Person(String name, Long phoneNumber){
        this.name=name;
        this.phoneNumber =phoneNumber;
    }

}
