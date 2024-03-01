package at.spengergasse;

import java.time.Year;

public class Manager extends Mitarbeiter {

    private double fixum;

    // ctor -------------------------------
    // zum Testen
    public Manager() {
        setFixum(1000);
    }

    // zum Verwenden

    public Manager(String name, Year gebJahr, Year entrJahr, char geschlecht, double fixum) {
        super(name, gebJahr, entrJahr, geschlecht);
        setFixum(fixum);
    }

    // getter -------------------------------
    public double getFixum() {
        return fixum;
    }

    // setter -------------------------------
    public void setFixum(double fixum) {
        if (fixum >= 0) {
            this.fixum = fixum;
        } else {
            System.out.println("Fehler: Ungültiges Fixum");
        }
    }

    @Override
    public double berechneGehalt() {
        return fixum;
    }

    @Override
    public String toString() {
        return "Manager - " + super.toString() + " " + berechneAlter();
//        return super.toString() + " " + berechneAlter();
    }
}
