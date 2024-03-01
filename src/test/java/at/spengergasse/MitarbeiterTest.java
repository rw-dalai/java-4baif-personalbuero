package at.spengergasse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class MitarbeiterTest {


    // Fixture -----------------------------

    private TestMitarbeiter mitarbeiter;

    class TestMitarbeiter extends Mitarbeiter {
        @Override
        public double berechneGehalt() {
            return 0;
        }
    }

    @BeforeEach
    public void setup() {
        mitarbeiter = new TestMitarbeiter();
    }

    @Test
    public void berechneDienstalter_shouldReturn_CorrectSum() {

        // GIVEN
        Year eintrittsJahr = Year.of(2020);
        Year aktJahr = Year.of(2024);
        mitarbeiter.setEintrJahr(eintrittsJahr);

        // WHEN
        int actualDienstalter = mitarbeiter.berechneDienstalter(aktJahr);

        // THEN
        // expected vs actual
        int expectedDienstalter = aktJahr.getValue() - eintrittsJahr.getValue();
        assertEquals(expectedDienstalter, actualDienstalter);
    }
}