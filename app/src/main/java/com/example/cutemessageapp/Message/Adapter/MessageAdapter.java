package com.example.cutemessageapp.Message.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutemessageapp.Database.Db;
import com.example.cutemessageapp.Message.Entity.Message;
import com.example.cutemessageapp.Person.Adapter.PersonAdapter;
import com.example.cutemessageapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.InternalClass> {
    Db db;
    List<Message> messages;

    public MessageAdapter(Context context){
        this.db = new Db(context);
        messages=db.message_readAll();
    }

    public class InternalClass extends RecyclerView.ViewHolder {
        TextView type, message;
        Button deleteMe;
        public InternalClass(@NonNull View itemView) {
            super(itemView);
            this.type= itemView.findViewById(R.id.personCard_name);
            this.message=itemView.findViewById(R.id.personCard_phoneNum);
            this.deleteMe=itemView.findViewById(R.id.personCard_deleteme);
        }
    }

    @NonNull
    @Override
    public MessageAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, null, false);
        MessageAdapter.InternalClass internalClass= new InternalClass(v);
        return internalClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.InternalClass holder, int position) {
        holder.type.setText( String.valueOf( messages.get(position).getType()));
        holder.message.setText(String.valueOf(messages.get(position).getMessage()));

        holder.deleteMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.message_delete(messages.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
