package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    private TextView heading;
    private EditText name;
    private EditText email;
    private EditText password;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        heading = (TextView) findViewById(R.id.textView);
        name = (EditText)findViewById(R.id.name);
        email =  (EditText)findViewById(R.id.email);
        password =  (EditText)findViewById(R.id.password);
        btn = (Button)findViewById(R.id.button);


       //json Object and paste the data into json
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
                JsonObjectRequest jsonObjectRequest = sendRequest();
                requestQueue.add(jsonObjectRequest);

                // Intent
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private JsonObjectRequest sendRequest() {
         //get input value
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        Log.d("Name",Name);

        // convert input value in json
        JsonChanges data = new JsonChanges();
        data.putUserData(Name,Email,Password);

        // get required url for api call
        ApiUrl apiUrl = new ApiUrl();
        String url = apiUrl.getRegisterUrl();

        //jsonObject Request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle successful response
                        heading.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        return jsonObjectRequest;
    }
}