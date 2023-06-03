package com.example.sqliteapp.model;

public class Contact {

    public String phone;
    public String email;
    public String query;
    public String answer;

    public Contact() {

    }

    public Contact(String phone, String email, String query, String answer) {
        this.phone = phone;
        this.email = email;
        this.query = query;
        this.answer = answer;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
