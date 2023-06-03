package com.example.sqldbms;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sqldbms.data.handler;
import com.example.sqldbms.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.listview);
        handler db = new handler(MainActivity.this);

        Contact harry  = new Contact();
        harry.setId(5);
        harry.setPhone("9041114392");
        harry.setEmail("shivdu2000@gmail.com");
        harry.setQuery("I love  GST");
        harry.setAnswer("rama");

        db.addContact(harry);

       // Log.d("dbhary","Query: " + harry.getQuery());
/*
        for(int i=0 ; i<=50; i++){
            db.deleteContact(i);
        }
*/

        ArrayList<String> contacts = new ArrayList<>();
        List<Contact> allContacts =db.getAllContact();
        for(Contact contact: allContacts){
            Log.d("dbhary","\nId: " + contact.getId() + "\n" +
                    "Phone: " + contact.getPhone() + "\n" +
                    "Email: " + contact.getEmail() + "\n" +
                    "Query: " + contact.getQuery() + "\n" +
                    "Answer: " + contact.getAnswer() + "\n");
           if (contact.getAnswer() != null){
               contacts.add(contact.getQuery());
           }

        }

        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        l.setAdapter(arrayAdapter);
    }
}