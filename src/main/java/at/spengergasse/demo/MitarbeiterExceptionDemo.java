package at.spengergasse.demo;

import at.spengergasse.domain.Manager;
import at.spengergasse.domain.MitarbeiterException;

import java.time.Year;

public class MitarbeiterExceptionDemo {

    public static void main(String[] args) {

        try {
            System.out.println("anfang inside try/catch vorher");

            Manager m = new Manager(null, Year.of(2000), Year.of(2025), 'w', 1000);

            System.out.println("ende inside try/catch");

        } catch (MitarbeiterException ex) {
            System.out.println("inside catch");

            System.out.println(ex.getMessage());
//            ex.printStackTrace();
        }

        System.out.println("nachher try/catch");


    }
}
