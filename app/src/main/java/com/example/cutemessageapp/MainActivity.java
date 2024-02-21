package com.example.cutemessageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cutemessageapp.Database.Db;
import com.example.cutemessageapp.Message.Entity.Message;
import com.example.cutemessageapp.Message.AddNewMessage;
import com.example.cutemessageapp.Person.Entity.Person;
import com.example.cutemessageapp.Person.AddNewPerson;

import java.util.List;

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

    //populate the fields
        populateThePhoneNumberSpinner();
        populateTheMessageTypeSpinner();

    //quantity of texts? //interval
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=getTheNumOfTexts();
                int interval= getTheInterval();

//                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
//                ==
//                PackageManager.PERMISSION_GRANTED
//                ){



                    long phoneNum=getPhoneNumber();
                    Message.Type messageType= getMessageTypeSelected();
                    try {
                        setTheTexts(phoneNum,messageType,  num, interval);
                    } catch (InterruptedException e) {
                        Toast.makeText(MainActivity.this, "Failed to schedulate texts", Toast.LENGTH_SHORT).show();
                    }
//                }

            }
        });


        appNavigation();
    }

    private Message.Type getMessageTypeSelected(){
        return  (Message.Type) msgType.getSelectedItem();
    }


    private Long getPhoneNumber(){
        return Long.valueOf( number.getSelectedItem().toString());
    }


    private  void setTheTexts(long phoneNum,Message.Type messageType , Integer numTexts, Integer intervalBetweenTexts) throws InterruptedException {

        SmsManager smsManager= SmsManager.getDefault();
        for (int x =0; x<numTexts; x++){
            Thread.sleep(intervalBetweenTexts*3600000);
            //sleep for x amount of hours

            Db db= new Db(MainActivity.this);
            Message msg=db.message_readRandomOne(messageType);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                smsManager.sendTextMessage(String.valueOf(phoneNum),null, msg.getMessage(),null,null);
            }

        }
    }

    private Integer getTheInterval(){
        String _interval=interval.getText().toString();

        if(_interval.isEmpty()){
            Toast.makeText(this, "The Interval Is invalid", Toast.LENGTH_SHORT).show();
            return null;
        }
        int interval=Integer.valueOf(_interval);
        if(interval <=0){
            Toast.makeText(this, "The interval cant be <=0", Toast.LENGTH_SHORT).show();
            return null;
        }

        return interval;
    }

    private Integer getTheNumOfTexts(){

        String numTexts= numText.getText().toString();
        if (numTexts.isEmpty()){
            Toast.makeText(this, "the interval cant be empty", Toast.LENGTH_SHORT).show();
            return null;
        }
        int numT=Integer.valueOf(numTexts);
        if (numT<=0){
            Toast.makeText(this, "the interval cant be <=0", Toast.LENGTH_SHORT).show();
            return  null;
        }

        return numT;
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

    private void populateThePhoneNumberSpinner(){
        Db db= new Db(MainActivity.this);

        List<Person> people= db.person_readAll();

        if (people==null || people.size()==0){
            number.setAdapter(
                    new ArrayAdapter<>(this,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            new String[]{"Please add a phone number"}
                    )
            );
        }else{

            String[] phoneNums=new String[people.size()];

            for (int x=0; x<people.size(); x++){
                phoneNums[x]=String.valueOf(people.get(x).getName()+": "+people.get(x).getPhoneNumber());
            }
            number.setAdapter(
                    new ArrayAdapter<>(this,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            phoneNums
                    )
            );
        }
    }

    private void populateTheMessageTypeSpinner(){
        msgType.setAdapter(
                new ArrayAdapter<>(this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        Message.Type.values())
        );
    }

}