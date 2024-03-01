package at.spengergasse;

import java.time.Year;


// Natürliche Ordnung
// 0, 1, 2, 3, 4
// "a" "b" "c"

// Comparable<Mitarbeiter> interface
// stellt die natürliche (natural order) der Mitarbeiter her.
// zB M2("Ana"), M1("Rene"), M3("Wilhelm")

public abstract class Mitarbeiter {

    private String name;

    private Year gebJahr; // 2000
    private Year eintrJahr; // 2018, 2025

    private char geschlecht;


    // ctor -------------------------------

    // zum Testen
    public Mitarbeiter() {
        setName("Ana");
        setGebJahr(Year.of(2001));
        setEintrJahr(Year.now());
        setGeschlecht('w');
    }

    // zum Verwenden
    public Mitarbeiter(String name, Year gebJahr, Year entrJahr, char geschlecht) {
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
    public void setName(String name) {
        if (name != null) {
            if (name.length() >= 2) {
                this.name = name;
            } else {
                System.out.println("Fehler: Falscher Name");
            }
        } else {
            System.out.println("Fehler: Name null");
        }
    }

    public void setGebJahr(Year gebJahr) {
        Year aktYear = Year.now();

        if (gebJahr != null) {
            if (
                    gebJahr.isAfter(aktYear.minusYears(100)) &&
                            !gebJahr.isAfter(aktYear)
            ) {
                this.gebJahr = gebJahr;
            } else {
                System.out.println("Fehler: Gebjahr ungültig");
            }
        } else {
            System.out.println("Fehler: GebJahr null");
        }
    }

    public void setEintrJahr(Year eintrJahr) {
        Year aktYear = Year.now();

        if (eintrJahr != null) {
            if (berechneAlter() >= 18 && !eintrJahr.isAfter(aktYear)) {
                this.eintrJahr = eintrJahr;
            } else {
                System.out.println("Fehler: Eintritts ungültig");
            }
        } else {
            System.out.println("Fehler: Eintrittsjahr null");
        }
    }

    public void setGeschlecht(char geschlecht) {
        if (geschlecht == 'w' || geschlecht == 'm') {
            this.geschlecht = geschlecht;
        } else {
            System.out.println("Fehler: Geschlecht ungültig");
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
    public double berechnePraemie(Year year) {
        if (year == null) {
            year = Year.now();
        }

        int betriebz = berechneDienstalter(year);

        if (betriebz == 15) {
            return berechneGehalt();
        } else if (betriebz == 25) {
            return berechneGehalt() * 2;
        } else if (betriebz == 35) {
            return berechneGehalt() * 3;
        } else if (betriebz == 40) {
            return berechneGehalt() * 4;
        } else if (betriebz == 50) {
            return berechneGehalt() * 6;
        } else {
            return 0;
        }
    }

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
}
