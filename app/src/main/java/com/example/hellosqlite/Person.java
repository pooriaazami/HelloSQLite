package com.example.hellosqlite;

public class Person {

    private int databaseKey;
    private String name;
    private String family;
    private int age;

    public Person(int databaseKey, String name, String family, int age) {
        this.databaseKey = databaseKey;
        this.name = name;
        this.family = family;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDatabaseKey() {
        return databaseKey;
    }
}
