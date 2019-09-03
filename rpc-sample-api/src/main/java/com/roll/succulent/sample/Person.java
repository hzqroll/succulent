package com.roll.succulent.sample;

/**
 * @author roll
 * created on 2019-09-03 14:18
 */
public class Person {
    private String firstName;

    private String laseName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLaseName() {
        return laseName;
    }

    public void setLaseName(String laseName) {
        this.laseName = laseName;
    }

    public Person(String firstName, String laseName) {
        this.firstName = firstName;
        this.laseName = laseName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", laseName='" + laseName + '\'' +
                '}';
    }
}
