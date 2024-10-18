package fr.istic.vv;

public class Person {
    private int Age;
    private String Name;
    private String Surname;
    public int Taille;
    
    public String getName() { return Name; }

    public int getTaille(){ return Taille; }

    public boolean isAdult() {
        return Age > 17;
    }
}
