package com.example.cutemessageapp.Person.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutemessageapp.Database.Db;
import com.example.cutemessageapp.Person.Entity.Person;
import com.example.cutemessageapp.R;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.InternalClass> {

    Db db;
    List<Person> people;
    public PersonAdapter(Context context){
        this.db= new Db(context);
        this.people=db.person_readAll();
    }

    @NonNull
    @Override
    public InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,null, false);
        InternalClass internalClass= new InternalClass(v);
        return internalClass;
    }

    @Override
    public void onBindViewHolder(@NonNull InternalClass holder, int position) {
        holder.name.setText( people.get(position).getName());
        holder.phoneNumber.setText(String.valueOf(people.get(position).getPhoneNumber()));

        holder.deleteMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.name.setVisibility(View.INVISIBLE);
                holder.phoneNumber.setVisibility(View.INVISIBLE);
                holder.deleteMe.setVisibility(View.INVISIBLE);

                db.person_delete(people.get(position).getId());

                Toast.makeText(holder.itemView.getContext(), "Person was deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public class InternalClass extends RecyclerView.ViewHolder{
        TextView name, phoneNumber;
        Button deleteMe;
        public  InternalClass(@NonNull View itemView){
            super(itemView);
            this.name=itemView.findViewById(R.id.personCard_name);
            this.phoneNumber= itemView.findViewById(R.id.personCard_phoneNum);
            this.deleteMe=itemView.findViewById(R.id.personCard_deleteme);
        }
    }
    @Override
    public int getItemCount() {
        return people.size();
//        return 5;
    }

}