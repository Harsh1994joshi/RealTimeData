package com.example.realtime;

import android.widget.EditText;

public class Model {

    String ID,name,email,contact;
    public Model(){

    }
    public Model(String ID, String name, String email, String contact) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
