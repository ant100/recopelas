/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.model;

/**
 *
 * @author leo
 */
public class User {

    private String Name;
    private String LastnameFather;     
    private String LastnameMother; 
    private String Document;
    private String Email;
    private String Password;  
    private String Phone;
    private String Address;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastnameFather() {
        return LastnameFather;
    }

    public void setLastnameFather(String LastnameFather) {
        this.LastnameFather = LastnameFather;
    }

    public String getLastnameMother() {
        return LastnameMother;
    }

    public void setLastnameMother(String LastnameMother) {
        this.LastnameMother = LastnameMother;
    }

    
    
    public String getDocument() {
        return Document;
    }

    public void setDocument(String Document) {
        this.Document = Document;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
}
