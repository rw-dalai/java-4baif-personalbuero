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

        // Given
        Year eintritt = Year.of(2020);
        Year akt = Year.of(2024);
        mitarbeiter.setEintrJahr(eintritt);

        // When
        int actualDienstalter = mitarbeiter.berechneDienstalter(akt);

        // Then
        // expected vs actual
        int expectedDienstalter = akt.getValue() - eintritt.getValue();
        assertEquals(expectedDienstalter, actualDienstalter);
    }
}