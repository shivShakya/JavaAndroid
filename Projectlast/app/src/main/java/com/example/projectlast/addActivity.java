package com.example.projectlast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addActivity extends AppCompatActivity {

    EditText title , author , pages ;
    Button button ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        pages = findViewById(R.id.pages);

        button = findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(addActivity.this);
                myDB.addBook(title.getText().toString().trim(),author.getText().toString().trim(),Integer.valueOf(pages.getText().toString()));
            }
        });

    }
}