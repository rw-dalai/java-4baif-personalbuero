package at.spengergasse.domain;

import java.util.Comparator;

// Comparator is used when you want to define multiple different comparison strategies for a class.

// We have to implement the `compareTo` method from the `Comparable` Interface.
// The `compareTo` method has to return 3 values:

//  <0  o1 ist kleiner als o2
// == 0 o1 ist gleich als o2
//  > 0 o1 ist größer als o2

public class MitarbeiterNamenComparator implements Comparator<Mitarbeiter> {

    // Namen aufsteigend
    @Override
    public int compare(Mitarbeiter o1, Mitarbeiter o2) {
        return o1.getName().compareTo(o2.getName()); // aufsteigend
        // return o1.getName().compareTo(o2.getName()); // absteigend
    }
}
