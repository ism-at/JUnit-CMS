package org.example;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    //-------------------------------------------Getter-------------------------------------------//
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    //-------------------------------------------Setter-------------------------------------------//
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //-------------------------------------validationMethods---------------------------------------//
    public void validFirstName(){
        if(this.firstName == null){
            throw new RuntimeException("First name must not be null !");
        }
        if(this.firstName.equals("")){
            throw new RuntimeException("First name must not be empty !");
        }
    }

    public void validLastName() {
        if (this.lastName == null){
            throw new RuntimeException("Last name must not be null/empty !");
        }
        if (this.lastName.equals("")){
            throw new RuntimeException("Last name must not be empty !");
        }
    }

    public void validPhoneNumber(){
        if(this.phoneNumber.length() <= 10){
            throw new RuntimeException("Phone number must be bigger than 10 digits !");
        }
        if(!this.phoneNumber.matches("\\d+")){
            throw new RuntimeException("Phone number must have only digits !");
        }
        if(!this.phoneNumber.startsWith("0")){
            throw new RuntimeException("Phone number must start with 0 !");
        }
    }

}
