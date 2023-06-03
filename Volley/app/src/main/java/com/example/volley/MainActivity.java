package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private Button btn;
    private TextView textView;
    private RequestQueue requestQueue;
     private  SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        btn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        requestQueue = Volley.newRequestQueue(this);
        String url = "https://androidapi-shivshakya.vercel.app/postUsers";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = name.getText().toString();
                StringRequest request = new StringRequest(Request.Method.POST, url,
                        response -> {
                            // Handle the response here

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String insertedId = jsonObject.optString("inserted_id");
                                // shared perferance
                                editor.putString("id", insertedId);
                                editor.apply();
                                textView.setText(" id:" + insertedId);
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "successfully added", Toast.LENGTH_SHORT).show();
                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                            Log.d("POST Response", response);
                        },
                        error -> {
                            // Handle errors here
                            Toast.makeText(MainActivity.this, "Erorr in the code", Toast.LENGTH_SHORT).show();
                            Log.e("POST Error", error.toString());
                        }) {
                    @Override
                    public byte[] getBody() {
                        String requestBody = "{\"name\":\"" + user + "\"}";
                        return requestBody.getBytes();
                    }
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                requestQueue.add(request);
            }
        });

    }
}