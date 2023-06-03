package com.example.sqliteapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.util.Log;

import com.example.sqliteapp.model.Contact;
import com.example.sqliteapp.params.params;

import java.util.ArrayList;
import java.util.List;

public class handler extends SQLiteOpenHelper {

    public handler(Context context) {
        super(context, params.DB_NAME,null,params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String create = "CREATE TABLE " + params.TABLE_NAME + "("
                 + params.KEY_PHONE + "  TEXT PRIMARY KEY," + "\n"
                 + params.KEY_EMAIL + "  TEXT, " + "\n"
                 + params.KEY_QUERY + "  TEXT, " + "\n"
                 + params.KEY_ANSWER + "  TEXT" + ")";

        Log.d("dbh","Query being run is: "+ create);
        sqLiteDatabase.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(params.KEY_PHONE,contact.getPhone());
        values.put(params.KEY_EMAIL,contact.getEmail());
        values.put(params.KEY_QUERY,contact.getQuery());
        values.put(params.KEY_ANSWER,contact.getAnswer());

        db.insert(params.TABLE_NAME,null,values);
        Log.d("dbh","Successfully inserted");
        db.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String select = "SELECT * FROM " + params.TABLE_NAME;
        Cursor cursor =db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setPhone(cursor.getString(0));
                contact.setEmail(cursor.getString(1));
                contact.setQuery(cursor.getString(2));
                contact.setAnswer(cursor.getString(3));

                contactList.add(contact);

            }while(cursor.moveToNext());
        }
        return contactList;
    }


    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_PHONE,contact.getPhone());
        values.put(params.KEY_EMAIL,contact.getEmail());
        values.put(params.KEY_QUERY,contact.getQuery());
        values.put(params.KEY_ANSWER,contact.getAnswer());


        return db.update(params.TABLE_NAME,values,params.KEY_PHONE+ "=?",
                new String[]{String.valueOf(contact.getPhone())});
    }


    public void deleteContact(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.TABLE_NAME,params.KEY_PHONE + "=?", new String[]{String.valueOf(phone)});
    }
}
