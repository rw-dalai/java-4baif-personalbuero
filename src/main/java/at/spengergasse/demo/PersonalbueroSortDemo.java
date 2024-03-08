package at.spengergasse.demo;

import at.spengergasse.domain.Manager;
import at.spengergasse.domain.MitarbeiterException;
import at.spengergasse.domain.Personalbuero;

import java.time.Year;

public class PersonalbueroSortDemo {

    public static void main(String[] args) throws MitarbeiterException {

        Manager manager = new Manager();
        manager.setName("Rene");
        manager.setFixum(1000);
        manager.setGebJahr(Year.of(2000));

        Manager manager2 = new Manager();
        manager2.setFixum(800);
        manager2.setName("Anton");
        manager2.setGebJahr(Year.of(1990));

        Manager manager3 = new Manager();
        manager3.setFixum(900);
        manager3.setName("Bern");
        manager.setGebJahr(Year.of(2005));

        Personalbuero personalbuero = new Personalbuero();
        personalbuero.add(manager);
        personalbuero.add(manager2);
        personalbuero.add(manager3);

        System.out.println("VORHER:  " + personalbuero);
        personalbuero.sortiereAlterAufsteigend();
        System.out.println("NACHHER: " + personalbuero);
    }
}
