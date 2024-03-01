package at.spengergasse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ManagerTest {

    // Fixture
    private Manager manager;

    @BeforeEach
    public void setup() {
        manager = new Manager();
    }


    @Test
    public void berechneGehalt_shouldReturn_CorrectValue() {

        // WHEN
        double actualGehalt = manager.berechneGehalt();

        // THEN
        // expected vs actual
        double expectedGehalt = manager.getFixum();
        assertEquals(expectedGehalt, actualGehalt);
    }
}
