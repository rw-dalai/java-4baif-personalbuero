package at.spengergasse.domain;

import java.time.Year;

public class Manager extends Mitarbeiter {

    private double fixum;

    // ctor -------------------------------
    // zum Testen
    public Manager() throws MitarbeiterException {
        // super();
        setFixum(1000);
    }

    // zum Verwenden
    public Manager(String name, Year gebJahr, Year entrJahr, char geschlecht, double fixum) throws MitarbeiterException {
        super(name, gebJahr, entrJahr, geschlecht);
        setFixum(fixum);
    }

    // getter -------------------------------
    public double getFixum() {
        return fixum;
    }

    // setter -------------------------------
    public void setFixum(double fixum) throws MitarbeiterException {
        if (fixum >= 0) {
            this.fixum = fixum;
        } else {
            throw new MitarbeiterException("Fehler: Ungültiges Fixum");
        }
    }

    // methods ------------------------------
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
