package com.example.cutemessageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cutemessageapp.Database.Db;
import com.example.cutemessageapp.Database.Entities.Person;

public class AddNewPerson extends AppCompatActivity {

    EditText name, phoneNumber;
    Button save;

    ListView showPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);
        makeAssociations();


        //fill in the listview
        



        //once i click save i want to be sent back to previous page.
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db db= new Db(AddNewPerson.this);

                Person person= getPerson();
                if(person== null){
                    return;
                }
                db.savePeron(person);

                Toast.makeText(AddNewPerson.this, "person was saved successfully", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                startActivity(new Intent(AddNewPerson.this, MainActivity.class));
            }
        });

    }


    private void makeAssociations(){
        this.name= findViewById(R.id.name);
        this.phoneNumber=findViewById(R.id.phoneNumber);

        this.save= findViewById(R.id.savePerson);

        this.showPeople= findViewById(R.id.showPeople);
    }

    private Person getPerson() {
        String _name = name.getText().toString();
        String phoneNumStr = phoneNumber.getText().toString();

        if (_name.isEmpty()) {
            Toast.makeText(this, "Fill The Name Field", Toast.LENGTH_SHORT).show();
            return null;
        }

        // Regular expression pattern for Canadian phone numbers
        String phoneNumPattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

        if (!phoneNumStr.matches(phoneNumPattern)) {
            Toast.makeText(this, "Invalid Phone Number Format", Toast.LENGTH_SHORT).show();
            return null;
        }

        // Remove hyphens from the phone number string
        phoneNumStr = phoneNumStr.replaceAll("-", "");

        // Convert the phone number string to Long for comparison
        Long phoneNum = Long.parseLong(phoneNumStr);

        // Check if the phone number is within the valid range for Canada
        if (phoneNum < 2560000000L || phoneNum > 7789999999L) {
            Toast.makeText(this, "Phone Number is Out of Range", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new Person(_name, phoneNum);
    }

}