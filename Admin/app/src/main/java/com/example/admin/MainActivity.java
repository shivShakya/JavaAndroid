package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Admin Page");

        View paymentInclude = findViewById(R.id.button);
        View menu = findViewById(R.id.button2);
        View feedback = findViewById(R.id.button3);
        paymentInclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,paymentCollection.class);
                Toast.makeText(MainActivity.this, "Payment collection clicked", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Menu Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "feedback clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }


}