package at.spengergasse.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalbueroTest {

    private Personalbuero personalbuero;
    private Mitarbeiter mitarbeiter;

    @BeforeEach
    public void setup() throws MitarbeiterException {
        personalbuero = new Personalbuero();
        mitarbeiter = new Manager();
    }

    @Test
    // SUT_ShouldXXX_WhenXXX
    public void add_shouldAddMitarbeiter_whenValidMitarbeiter() throws MitarbeiterException {
//        try {
            personalbuero.add(mitarbeiter);
            assertEquals(1, personalbuero.zaehleMitarbeiter());
//        } catch (MitarbeiterException ex) {
//            fail();
//        }
    }

    @Test
    public void add_shouldThrowException_whenMitarbeiterIsNull() {
        var ex = assertThrows(MitarbeiterException.class, () -> personalbuero.add(null));
        assertEquals("Mitarbeiter darf nicht null sein.", ex.getMessage());

//        try {
//            personalbuero.add(null);
//            fail();
//        } catch (MitarbeiterException ex) {
            // assertEquals("Mitarbeiter darf nicht null sein.", e.getMessage());
//        }
    }

    @Test
    public void berechneGesamtGehalt_shouldReturnZero_whenNoMitarbeiter() {
        assertEquals(0, personalbuero.berechneGesamtGehalt());
    }

    @Test
    public void berechneGesamtGehalt_shouldReturnGehalt_whenOneMitarbeiter() throws MitarbeiterException {
        personalbuero.add(mitarbeiter);
        assertEquals(mitarbeiter.berechneGehalt(), personalbuero.berechneGesamtGehalt());
    }

    @Test
    public void kuendigen_shouldReturnTrue_whenMitarbeiterExists() throws MitarbeiterException {
        personalbuero.add(mitarbeiter);
        assertTrue(personalbuero.kuendigen(mitarbeiter));
    }

    @Test
    public void kuendigen_shouldReturnFalse_whenMitarbeiterDoesNotExist() throws MitarbeiterException {
        assertFalse(personalbuero.kuendigen(mitarbeiter));
    }


    @Test
    public void kuendigen_shouldReturnMitarbeiter_whenNameExists() throws MitarbeiterException {
        // GIVEN
        mitarbeiter.setName("Ana");
        personalbuero.add(mitarbeiter);

        // WHEN
        var firedMitarbeiter = personalbuero.kuendigen("Ana");

        // THEN
        assertEquals(mitarbeiter, firedMitarbeiter);
    }

    @Test
    public void sortiereMitarbeiter_shouldSortMitarbeiter() throws MitarbeiterException {
        // TODO
    }
}