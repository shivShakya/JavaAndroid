package com.example.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonChanges extends JSONObject {


        JsonChanges(){
            Log.d("default","Hello I m default Constructor");
        }

        JsonChanges(String Name, String Email , String Password){
            Log.d("text1", Name+Email+Password);
        }

        JsonChanges(String Name,int Price,String Category , String image_link ){
            Log.d("text2", Name+Price+Category+image_link);
        }

        public void putProductData(String Name, int Price ,String Category, String image_link){
            try {
                put("name", Name);
                put("price", Price);
                put("category",Category);
                put("image_link",image_link);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       //method overloading allowed in java
        public void putUserData(String Name, String Email , String Password){

            try {
                put("name", Name);
                put("email", Email);
                put("password",Password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

       public void putUserData(String Email , String Password){
        try {

            put("email", Email);
            put("password",Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




