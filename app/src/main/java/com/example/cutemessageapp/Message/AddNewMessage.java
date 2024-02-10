package com.example.cutemessageapp.Message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cutemessageapp.Database.Db;
import com.example.cutemessageapp.Message.Adapter.MessageAdapter;
import com.example.cutemessageapp.Message.Entity.Message;
import com.example.cutemessageapp.MainActivity;
import com.example.cutemessageapp.R;

public class AddNewMessage extends AppCompatActivity {

    Spinner spinner;
    EditText theMessage;
    Button save,back;

    RecyclerView myMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_message);

        makeAssociateions();

        setSpinner();
        setAdapter();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Db db= new Db(AddNewMessage.this);
                Message msg= getMsg();

                if(msg==null){
                    return;
                }

                db.message_save(msg);
                Toast.makeText(AddNewMessage.this, "Message was added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewMessage.this, MainActivity.class));
            }
        });
    }

    private void setAdapter(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        myMessages.setLayoutManager(linearLayoutManager);

        MessageAdapter messageAdapter= new MessageAdapter(this);
        myMessages.setAdapter( messageAdapter);
    }
    private void setSpinner(){

//      make the spinner
        spinner.setAdapter(
                new ArrayAdapter<>(this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        Message.Type.values())
        );

    }
    private void makeAssociateions(){
        this.spinner= findViewById(R.id.msgCategory);
        this.theMessage =findViewById(R.id.message);

        this.save=findViewById(R.id.saveMsg);
        this.back= findViewById(R.id.msgBack);
        this.myMessages=findViewById(R.id.availableMessages);
    }
    private Message getMsg(){
        String msg= theMessage.getText().toString();
        if(msg.isEmpty()){
            Toast.makeText(this, "Need to enter a message", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new Message(msg, (Message.Type) spinner.getSelectedItem() );
    }
}