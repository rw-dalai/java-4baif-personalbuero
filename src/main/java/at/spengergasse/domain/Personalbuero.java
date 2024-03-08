package at.spengergasse.domain;

import java.util.Comparator;
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

    public void add(Mitarbeiter mitarbeiter) {
        mitarbeiters.add(mitarbeiter);
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
        return "Personalbuero{" +
                "mitarbeiters=" + mitarbeiters +
                '}';
    }
}
