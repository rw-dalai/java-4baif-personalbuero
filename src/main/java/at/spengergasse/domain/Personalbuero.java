package at.spengergasse.domain;

import java.time.Year;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Personalbuero {

    // left side abstract types / right side concrete types
    private List<Mitarbeiter> mitarbeiters; // GOOD
    // private LinkedList<Mitarbeiter> mitarbeiters; // BAD


    // ctor -------------------------------

    public Personalbuero() {
        this.mitarbeiters = new LinkedList<>();
    }


    // methods -------------------------------

    // liefert als Prozentsatz (ganzzahlig), wieviele der Mitarbeiter Frauen sind (division durch 0!)
    public int frauenQuote() {
        if (!mitarbeiters.isEmpty()) {
            int frauen = 0;

            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                if (mitarbeiter.getGeschlecht() == 'w') {
                    frauen++;
                }
            }
            return (frauen * 100) / mitarbeiters.size();
        }

        return 0;
    }


    // fügt einen Mitarbeiter hinzu, wenn er
    // - nicht null ist,
    // - die Anzahl der Mitarbeiter nicht 100 übersteigt,
    // - das Alter des Mitarbeiters nicht 60 Jahre übersteigt
    // - die Frauenquote nicht unter 40% sinkt
    public void add(Mitarbeiter mitarbeiter) throws MitarbeiterException {
        if (mitarbeiter != null)
            if (mitarbeiters.size() < 100)
                if (mitarbeiter.berechneAlter() < 60)
                    if (mitarbeiters.isEmpty() || mitarbeiter.getGeschlecht() == 'w' || frauenQuote() >= 40)
                        mitarbeiters.add(mitarbeiter);
                    else
                        throw new MitarbeiterException("Frauenquote darf nicht unter 40% sinken.");
                else
                    throw new MitarbeiterException("Mitarbeiter darf nicht älter als 60 Jahre sein.");
            else
                throw new MitarbeiterException("Maximale Anzahl an Mitarbeitern erreicht.");
        else
            throw new MitarbeiterException("Mitarbeiter darf nicht null sein.");
    }


    // berechnet die Gesamt-Summe der Monatsgehälter aller Mitarbeiter
    public double berechneGesamtGehalt() {
        double summe = 0;

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            summe += mitarbeiter.berechneGehalt();
        }

        return summe;
    }


    // zaehleMitarbeiter() gibt die Anzahl der Mitarbeiter zurück.
    public int zaehleMitarbeiter() {
        return mitarbeiters.size();
    }


    // zaehleAlter(alter) gibt die Anzahl der Mitarbeiter zurück, die älter als der übergebene Wert sind.
    public int zaehleAlter(int alter) throws MitarbeiterException {
        if (alter >= 1) {
            int anzahl = 0;

            //  No Counting Loop
            //  for (int i = 0; i < mitarbeiters.size(); i++) {
            //      Mitarbeiter mitarbeiter = mitarbeiters.get(i);
            //  }

            // For Each
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                if (mitarbeiter.berechneAlter() > alter) {
                    anzahl++;
                }
            }

            return anzahl;
        } else {
            throw new MitarbeiterException("Alter muss größer gleich 1 sein");
        }
    }


    // zaehleAngestellte() gibt die Anzahl der Manager zurück.
    public int zaehleManager() {
        int anzahl = 0;

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            if (mitarbeiter instanceof Manager) {
                anzahl++;
            }
        }

        return anzahl;
    }


    // berechneAvgGehaltManager() berechnet das Durchschnittsgehalt aller Manager.
    public double berechneAvgGehaltManager() {
        double summe = 0;
        int anzahl = 0;

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            if (mitarbeiter instanceof Manager) {
                summe += mitarbeiter.berechneGehalt();
                anzahl++;
            }
        }

        // ternary operator
        // boolean expression ? code_when_true : code_when_false;
        return anzahl > 0 ? summe / anzahl : 0;

//        if (anzahl > 0) {
//            return summe / anzahl;
//        } else {
//            return 0;
//        }

    }


    // kuendigen(pos) entfernt den an der Stelle pos befindlichen Mitarbeiter
    public boolean kuendigen(int pos) {
        if (pos >= 0 && pos < mitarbeiters.size()) {
            mitarbeiters.remove(pos);
            return true;
        }

        return false;
    }


    // kuendigen(ma) entfernt diesen Mitarbeiter
    public boolean kuendigen(Mitarbeiter mitarbeiter) throws MitarbeiterException {
        if (mitarbeiter != null) {
            return mitarbeiters.remove(mitarbeiter);
        } else {
            throw new MitarbeiterException("Mitarbeiter ist null");
        }
    }


    // kuendigen(name) entfernt den ersten Mitarbeiter mit diesen Namen (mit Iterator)
    public Mitarbeiter kuendigen(String name) {

        Iterator<Mitarbeiter> iterator = mitarbeiters.iterator();

        while (iterator.hasNext()) {
            Mitarbeiter mitarbeiter = iterator.next();

            if (mitarbeiter.getName().equals(name)) {
                iterator.remove();
                return mitarbeiter;
            }
        }

        return null;
    }


    // topVerdiener() gibt in einem String (mit erklärendem Text!) den Namen des Mitarbeiters mit dem höchsten Gehalt zurück.
    public String topVerdiener() {
        if (!mitarbeiters.isEmpty()) {
            Mitarbeiter top = mitarbeiters.getFirst();
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                if (mitarbeiter.berechneGehalt() > top.berechneGehalt()) {
                    top = mitarbeiter;
                }
            }

            return "Top-Verdiener: " + top.getName();
        }

        return "Keine Mitarbeiter vorhanden.";
    }


    // topVerdiener(gehalt) gibt in einem String alle Mitarbeiter zurück, deren Gehalt >= dem Parameterwert ist.
    public String topVerdiener(double gehalt) {
        StringBuilder sb = new StringBuilder();
//        String sb = "";

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            if (mitarbeiter.berechneGehalt() >= gehalt) {
                sb.append(mitarbeiter.getName()).append(" ");
//                sb += mitarbeiter.getName() + " ";
            }
        }

        return "Top-Verdiener: " + sb.toString();
    }


    // praemienListe(jahr) gibt eine Liste ausschliesslich jener Mitarbeiter zurück, die im Parameter-Jahr ein Jubiläum haben.
    public String praemienListe(Year year) {
        StringBuilder sb = new StringBuilder();

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            double praemie = mitarbeiter.berechnePraemie(year);
            if (praemie > 0) {
                sb.append(String.format("%s, %d, %.2f€",
                    mitarbeiter.getName(), mitarbeiter.getEintrJahr().getValue(), praemie));
            }
        }

        return sb.toString();
    }


    // erhoeheManagerFixum(proz) Das Fixum aller Manager wird um proz Prozent erhöht.
    public void erhoeheManagerFixum(int proz) throws MitarbeiterException {
        if (proz >= 1) {
            for (Mitarbeiter mitarbeiter : mitarbeiters) {
                if (mitarbeiter instanceof Manager manager) {
                    manager.setFixum(manager.getFixum() * (1 + proz / 100.));
                }
            }
        } else {
            throw new MitarbeiterException("Prozentsatz muss größer gleich 1 sein.");
        }
    }


    // sort -------------------------------

    public void sortiereMitarbeiter() {
        // NOTE: Sorts the mitarbeiter list using the Comparable interface implemented in the `Mitarbeiter` class.

        // Collections.sort(mitarbeiters);
        mitarbeiters.sort(null);
    }

    public void sortiereNamen() {
        // NOTE: Sorts the mitarbeiter list using Comparator interface

        // 1. Separate Class implementing Comparator interface
        // mitarbeiters.sort(new MitarbeiterNamenComparator());

        // 2. Anonymous Inner Class implementing Comparator interface
        // mitarbeiters.sort(new Comparator<Mitarbeiter>() {
        //    @Override
        //    public int compare(Mitarbeiter o1, Mitarbeiter o2) {
        //        return o1.getName().compareTo(o2.getName());
        //    }
        // });

        // 3. Lambda / anonymous function implementing Comparator interface
        Comparator<Mitarbeiter> mitarbeiterComparator =
                (Mitarbeiter mitarbeiter1, Mitarbeiter mitarbeiter2) ->
                        mitarbeiter1.getName().compareTo(mitarbeiter2.getName());

        // 4. Comparator.comparing with Lambda
        Comparator<Mitarbeiter> mitarbeiterComparator2 =
                Comparator.comparing((mitarbeiter -> mitarbeiter.getName()));

        // 5. Comparator.comparing with Method Referencing
        Comparator<Mitarbeiter> mitarbeiterComparator3 =
                Comparator.comparing((Mitarbeiter::getName));


        // Using JDK's sort functions which is calling our `Comparator` to figure out the order of the elements.
        // JDK Sort -> Comparator -> to compare 2 Mitarbeiter
        mitarbeiters.sort(mitarbeiterComparator);
    }

    public void sortiereAlterAufsteigend() {
        mitarbeiters.sort(new MitarbeiterAlterComparator());
    }

    public void sortiereAlterAbsteigend() {
        mitarbeiters.sort(new MitarbeiterAlterComparator().reversed());
    }


    // toString -------------------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            sb.append(mitarbeiter).append("\n");
        }

        return sb.toString();
    }
}
