package org.example;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        ContactManagement admin = new ContactManagement();

        // Create new contacts
        try {
            admin.addContact("Ismail", "Al Agele", "068860866874");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        try {
            admin.addContact("Anna", "Müller", "012345678910");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        // This will throw an exception
        try {
            admin.addContact("Ismail", "Al Agele", "068860866874");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        // This will throw an exception
        try {
            admin.addContact("Adam", "Eve", "2");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

        Collection<Contact> contacts = admin.getAllContact();

        for(Contact c : contacts){
            System.out.println("First name: " + c.getFirstName());
            System.out.println("Last name: " + c.getLastName());
            System.out.println("Phone number: " + c.getPhoneNumber());
        }
    }
}