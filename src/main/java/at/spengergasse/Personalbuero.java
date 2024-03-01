package at.spengergasse;

import java.util.LinkedList;
import java.util.List;

public class Personalbuero {

    private List<Mitarbeiter> mitarbeiters;

    public Personalbuero() {
        // left side abstract types = right side concrete types
        this.mitarbeiters = new LinkedList<>();
    }


}
