package at.spengergasse.domain;

import java.time.Year;

// Natural Ordering of Numbers:
// 0, 1, 2, 3, 4

// Natural Ordering of Letters:
// "a" "b" "c"

// Natural Ordering with the Comparable Interface
// The Comparable interface is used to impose a natural ordering on the objects of the class that implements it.

public abstract class Mitarbeiter implements Comparable<Mitarbeiter> {

    private String name;

    private Year gebJahr; // max 100 years

    private Year eintrJahr; // min 18 years, max

    private char geschlecht;


    // ctor -------------------------------

    // zum Testen
    // The constructor throws an exception to the caller
    public Mitarbeiter() throws MitarbeiterException {
        setName("Ana");
        setGebJahr(Year.of(2001));
        setEintrJahr(Year.now());
        setGeschlecht('w');
    }

    // zum Verwenden
    // The constructor throws an exception to the caller
    public Mitarbeiter(String name, Year gebJahr, Year entrJahr, char geschlecht) throws MitarbeiterException {
        setName(name);
        setGebJahr(gebJahr);
        setEintrJahr(entrJahr);
        setGeschlecht(geschlecht);
    }

    // getter ------------------------------
    public String getName() {
        return name;
    }

    public Year getGebJahr() {
        return gebJahr;
    }

    public Year getEintrJahr() {
        return eintrJahr;
    }

    public char getGeschlecht() {
        return geschlecht;
    }


    // setter ------------------------------
    // The setters throw an exception to the caller
    public void setName(String name) throws MitarbeiterException {
        if (name != null) {
            if (name.length() >= 2) {
                this.name = name;
            } else {
                throw new MitarbeiterException("Falscher Name");
                // System.out.println("Fehler: Falscher Name");
            }
        } else {
            throw new MitarbeiterException("Name null");
            // System.out.println("Fehler: Name null");
        }
    }

    public void setGebJahr(Year gebJahr) throws MitarbeiterException {
        Year aktYear = Year.now();

        if (gebJahr != null) {
            if (gebJahr.isAfter(aktYear.minusYears(100)) && !gebJahr.isAfter(aktYear)
            ) {
                this.gebJahr = gebJahr;
            } else {
                throw new MitarbeiterException("Gebjahr ungültig");
            }
        } else {
            throw new MitarbeiterException("GebJahr null");
        }
    }

    public void setEintrJahr(Year eintrJahr) throws MitarbeiterException {
        Year aktYear = Year.now();

        if (eintrJahr != null) {
            if (berechneAlter() >= 18 && !eintrJahr.isAfter(aktYear)) {
                this.eintrJahr = eintrJahr;
            } else {
                throw new MitarbeiterException("EintrJahr ungültig");
            }
        } else {
            throw new MitarbeiterException("EintrJahr null");
        }
    }

    public void setGeschlecht(char geschlecht) throws MitarbeiterException {
        if (geschlecht == 'w' || geschlecht == 'm') {
            this.geschlecht = geschlecht;
        } else {
            throw new MitarbeiterException("Geschlecht ungültig");
        }
    }


    // methods -----------------------------
    public int berechneAlter() {
        return Year.now().getValue() - gebJahr.getValue();
    }

    // Year.now
    // Overloaded
    public int berechneDienstalter() {
        return berechneDienstalter(Year.now());
    }

    // Parameter
    // Overloaded
    public int berechneDienstalter(Year year) {
        return year.getValue() - eintrJahr.getValue();
    }

    // Wird von den Kind-Klassen implementiert
    public abstract double berechneGehalt();

    // 15 Jahre Betriebszugehörigkeit -> 1 Monat
//    public double berechnePraemie(Year year) {
//        if (year == null) {
//            year = Year.now();
//        }
//
//        int betriebz = berechneDienstalter(year);
//
//        if (betriebz == 15) {
//            return berechneGehalt();
//        } else if (betriebz == 25) {
//            return berechneGehalt() * 2;
//        } else if (betriebz == 35) {
//            return berechneGehalt() * 3;
//        } else if (betriebz == 40) {
//            return berechneGehalt() * 4;
//        } else if (betriebz == 50) {
//            return berechneGehalt() * 6;
//        } else {
//            return 0;
//        }
//    }

    public double berechnePraemieV2(Year year) {
        if (year == null) {
            year = Year.now();
        }

        // switch expression
        return switch (berechneDienstalter(year)) {
            case 15 -> berechneGehalt();
            case 25 -> berechneGehalt() * 2;
            case 35 -> berechneGehalt() * 3;
            case 40 -> berechneGehalt() * 4;
            case 50 -> berechneGehalt() * 6;
            default -> 0;
        };
    }


    // toString -------------------------------

    @Override
    public String toString() {
        // Mitarbeiter-Art name - gesch € Gehalt / ev. sonstige Info
         return name + " - " + geschlecht + " € " + berechneGehalt();
        // %s string | %d int,long | %c character | %.2f float,double auf 2 Nachkommastellen
//         return String.format("$s - %s - %c € %.2f", getClass().getSimpleName(), name, geschlecht, berechneGehalt());
    }


    // compareTo -----------------------------

    // We have to implement the `compareTo` method from the `Comparable` Interface.
    // The `compareTo` method has to return 3 values:

    //  <0  Ich (this) bin kleiner bin als der andere
    // == 0 Ich (this) bin gleich als der andere bin
    //  > 0 Ich (this) bin größer bin als der andere
    @Override
    public int compareTo(Mitarbeiter otherMitarbeiter) {
        // "string".compareTo(), Integer.compare(), Double.compare()
        return Double.compare(this.berechneGehalt(), otherMitarbeiter.berechneGehalt());
    }

}
