package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btn;
    private TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.log_email);
        password = (EditText)findViewById(R.id.log_password);
        signIn = (TextView) findViewById(R.id.signuptext);
        btn = (Button)findViewById(R.id.button);

        // signIn implementation
        /*
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,MainActivity2.class);
                startActivity(intent);
            }
        });
       */

        // button implementation
      

    }




}