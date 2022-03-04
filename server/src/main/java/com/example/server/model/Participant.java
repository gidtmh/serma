package com.example.server.model;

import org.springframework.data.relational.core.sql.ConstantCondition;

public class Participant {
    private long id;
    private String name;
    private String contact;
    private boolean attending;

    public Participant() {
    }
    
    public Participant(long id, String name, String contact, boolean attending) {
      this.id = id;
      this.name = name;
      this.contact = contact;
      this.attending = attending;
    }
    public Participant(String name, String contact, boolean attending) {
        this.name = name;
        this.contact = contact;
        this.attending = attending;
    }
    
    public void setId(long id) {
      this.id = id;
    }
    
    public long getId() {
      return id;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getContact() {
      return contact;
    }
    public void setContact(String contact) {
      this.contact = contact;
    }
    public boolean isAttending() {
      return attending;
    }
    public void setAttending(boolean isAttending) {
      this.attending = isAttending;
    }
    @Override
    public String toString() {
      return "Tutorial [id=" + id + ", name=" + name + ", contact" + contact + ", attending=" + attending + "]";
    }
  }
