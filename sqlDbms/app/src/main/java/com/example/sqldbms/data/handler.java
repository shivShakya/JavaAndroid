package com.example.sqldbms.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.text.PrecomputedText;
import android.util.Log;

import com.example.sqldbms.MainActivity;
import com.example.sqldbms.model.Contact;
import com.example.sqldbms.params.params;

import java.util.ArrayList;
import java.util.List;

public class handler extends SQLiteOpenHelper {
    public handler(Context context) {
        super(context,params.DB_NAME,null,params.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE " + params.TABLE_NAME + "("
                + params.KEY_ID + " INTEGER PRIMARY KEY, " + params.KEY_PHONE
                + " TEXT," + params.KEY_EMAIL + " TEXT," + params.KEY_QUERY
                + " TEXT," + params.Key_ANSWER + " TEXT" + ")";

        Log.d("dbhary","Query being run is"+ create);
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_ID,contact.getId());
        values.put(params.KEY_PHONE,contact.getPhone());
        values.put(params.KEY_EMAIL,contact.getEmail());
        values.put(params.KEY_QUERY,contact.getQuery());
        values.put(params.Key_ANSWER,contact.getAnswer());
        db.insert(params.TABLE_NAME, null,values);
        Log.d("dbhary","Successfully inserted");
        db.close();

    }

    public List<Contact> getAllContact(){
        List<Contact>contactList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        //generate the query to read from the database

        String select = "SELECT * FROM " + params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setPhone(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setQuery(cursor.getString(3));
                contact.setAnswer(cursor.getString(4));

                contactList.add(contact);

            }while (cursor.moveToNext());
        }
        return contactList;
    }


    public int updateContact(Contact contact){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(params.KEY_PHONE,contact.getPhone());
         values.put(params.KEY_EMAIL,contact.getEmail());
         values.put(params.KEY_QUERY,contact.getQuery());
         values.put(params.Key_ANSWER,contact.getAnswer());


         return db.update(params.TABLE_NAME,values,params.KEY_ID+ "=?",
                 new String[]{String.valueOf(contact.getId())});


    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.TABLE_NAME, params.KEY_ID+ "=? ",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
