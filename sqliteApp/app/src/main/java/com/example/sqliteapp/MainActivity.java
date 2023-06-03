package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqliteapp.data.handler;
import com.example.sqliteapp.model.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import com.example.sqliteapp.model.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;




class Databas {
    int id = 6;
    String email = "poku@gmail.com";
    String que= "kya";
    String answer = " skdls";

    int phone = 58908;
    private Connection connection;
    private final String host = "10.0.2.2";

    private final String database = "django";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "doremon#22";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private final String query = "insert into web_contact values  (?,?,?,?,2022-10-07 15:26:54.947303+05:30,2022-10-07 19:57:56.555464+05:30,?);";
    private boolean status;

    public Databas()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();

        System.out.println("connection status:" + status);
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    PreparedStatement st = connection.prepareStatement(query);
                    st.setInt(1,id);
                    st.setString(2,email);
                    st.setString(3,que);
                    st.setString(4,answer);
                    st.setInt(7,phone);

                    int count = st.executeUpdate();


                    System.out.println(count + "row/s affected");
                    st.close();
                    connection.close();
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}



public class MainActivity extends AppCompatActivity {
    private EditText phn , eml , qry ;
    private Button btn;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler db = new handler(MainActivity.this);
        phn = findViewById(R.id.phone);
        eml = findViewById(R.id.email);
        qry = findViewById(R.id.query);
        btn = findViewById(R.id.button);
        list = findViewById(R.id.listView);

        ArrayList<String>arrayList = new ArrayList<>();

       Databas dbs = new Databas();
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String phone = phn.getText().toString();
               String email = eml.getText().toString();
               String query = qry.getText().toString();

               Contact harry = new Contact(phone,email,query,null);


               Toast.makeText(MainActivity.this, "added", Toast.LENGTH_SHORT).show();
               finish();
               startActivity(getIntent());


           }
       });



        //db.deleteContact("8854");

        List<Contact> allContacts = db.getAllContacts();
        for(Contact contact: allContacts){



                Log.d("dbh","Phone: " + contact.getPhone() + "\n"+
                        "Email: " + contact.getEmail() + "\n" +
                        "Query: " + contact.getQuery() + "\n" +
                        "Answer: " + contact.getAnswer() + "\n");
            if(contact.getAnswer() != null ){
                arrayList.add("Query: " + contact.getQuery() +"\nAnswer :" + contact.getAnswer());
            }


        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);

        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String select = list.getItemAtPosition(i).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Answer: ");
                builder.setMessage(select);
                builder.show();

            }
        });


    }
}