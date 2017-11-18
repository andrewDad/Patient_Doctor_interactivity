package com.example.andy.patient_doctor_interactivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andy.patient_doctor_interactivity.models.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.Buffer;

public class All_users extends AppCompatActivity {
private RecyclerView recyclerView;
private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
       recyclerView=findViewById(R.id.users);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

       databaseReference= FirebaseDatabase.getInstance().getReference().child("chat_users");

    }

    @Override
    protected void onStart() {
        FirebaseRecyclerAdapter<User,UserHolder> adapter=new FirebaseRecyclerAdapter<User, UserHolder>(
                User.class,R.layout.sigleruser,UserHolder.class,databaseReference
        )
        //recyclerView.setAdapter(adapter);

        {
            @Override
            protected void populateViewHolder(UserHolder viewHolder, User model, int position) {
                viewHolder.setUserName(model.getName());
            }
        };
        recyclerView.setAdapter(adapter);
        super.onStart();
        //recyclerView.setAdapter(adapter);
    }

    public static class UserHolder extends RecyclerView.ViewHolder{
View mView;
    public UserHolder(View itemView) {
        super(itemView);
        mView=itemView;
    }
    public void setUserName(String userName){
        TextView textView=mView.findViewById(R.id.username);
        textView.setText(userName);
    }
}
}
