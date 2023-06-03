package com.example.sqliteapp;

import com.example.sqliteapp.model.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Database {
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

    public Database()
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
