package com.example.data;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String urls = "http://10.0.2.2:3001/products";

        ApiUrl apiUrl = new ApiUrl();
        String url =  apiUrl.getProductUrl();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urls, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle API response
                        Log.d("API Response", response.toString());
                        cardDesign(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle API error
                Log.e("API Error", error.getMessage());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void cardDesign(JSONArray response) {

        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        for (int i = 0; i < 10; i++) {

            try {

                JSONObject jsonObject = response.getJSONObject(i);

                // get data from json to variable
                String name = jsonObject.getString("name");
                int price = jsonObject.getInt("price");
                String category = jsonObject.getString("category");
                String image = jsonObject.getString("image_link");

                // Views in linear layout
                TextView textView = new TextView(MainActivity.this);
                ImageView imageView = new ImageView(MainActivity.this);

                //for setting image Glide Api is used
                String imageUrl = "https://"+image;
                Glide.with(MainActivity.this).load(imageUrl).into(imageView);

                // for textview
                textView.setText("Name :" +name + "\n" + " Price "+ price + "\n" + "Category" + category);
                textView.setBackgroundColor(Color.rgb(0,0,255));

                // setting widgets to linear layout
                linearLayout.addView(imageView);
                linearLayout.addView(textView);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}