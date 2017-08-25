package fr.dwaps.testretrofit;

/**
 * Created by Michael on 25/08/2017.
 */

public class Person {
    private String nom;
    private String email;
    private int age;

    public Person(String nom, String email, int age) {
        this.nom = nom;
        this.email = email;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
