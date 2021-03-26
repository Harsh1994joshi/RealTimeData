package com.example.realtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleData extends AppCompatActivity {

    TextView ID,NAME,EMAIL,CONTACT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_data);
        ID=findViewById(R.id.sid);
        NAME=findViewById(R.id.sname);
        EMAIL=findViewById(R.id.semail);
        CONTACT=findViewById(R.id.scontact);

        Intent intent=getIntent();

        String id=intent.getStringExtra("key_id");
        ID.setText(id);
        String name=intent.getStringExtra("key_name");
        NAME.setText(name);
        String email=intent.getStringExtra("key_email");
        EMAIL.setText(email);
        String contact=intent.getStringExtra("key_contact");
        CONTACT.setText(contact);


    }
}