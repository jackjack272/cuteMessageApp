package com.example.cutemessageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner number, msgType;
    EditText numText, interval;
    Button schedule, addNewMessage, addNewPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//------

        makeAssociations();

        appNavigation();






    }

    private void makeAssociations(){
        this.number= findViewById(R.id.spinner_number);
        this.msgType= findViewById(R.id.spinner_msgType);

        this.numText= findViewById(R.id.numTexts);
        this.interval= findViewById(R.id.msgInterval);


        this.schedule= findViewById(R.id.scheduleText);
        this.addNewMessage=findViewById(R.id.addNewMessage);
        this.addNewPerson=findViewById(R.id.addNewPerson);
    }

    private void appNavigation(){

        addNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNewPerson.class));
            }
        });
        addNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNewMessage.class));
            }
        });

    }



}