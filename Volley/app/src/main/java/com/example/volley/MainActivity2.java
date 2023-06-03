package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private ListView details;
    private  RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        details = findViewById(R.id.getDetails);
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("id", "");
        List<String> namesList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        String url = "https://android-shivshakya.vercel.app/getMess";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    Toast.makeText(MainActivity2.this, "successfully get the response", Toast.LENGTH_SHORT).show();
                    Log.d("API", response.toString());

                    for(int i = 0 ; i < response.length() ; i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        //String id  = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        Log.d("name",name);
                        namesList.add(name);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1, namesList);
                    details.setAdapter(adapter);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "error is there", Toast.LENGTH_SHORT).show();
            }
        });
       requestQueue.add(jsonArrayRequest);
       // id.setText(user_id);


    }
}