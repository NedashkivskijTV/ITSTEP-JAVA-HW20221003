package edu.itstep.hw20221003;

public class User {
    @AllowCopy
    private String firstName;
    @AllowCopy
    private String lastName;
    @AllowCopy(false)
    private int age;
    private String phone;
    private String mail;

    public User(String firstName, String lastName, int age, String phone, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}

