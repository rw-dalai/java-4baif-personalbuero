package at.spengergasse.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MitarbeiterTest {


    // Fixture -----------------------------

    private TestMitarbeiter mitarbeiter;

    class TestMitarbeiter extends Mitarbeiter {
        public TestMitarbeiter() throws MitarbeiterException {
        }

        @Override
        public double berechneGehalt() {
            return 0;
        }
    }


    class SetNameExecutable implements Executable {
        @Override
        public void execute() throws Throwable {
            mitarbeiter.setName(null);
        }
    }

    @BeforeEach
    public void setup() throws MitarbeiterException {
        mitarbeiter = new TestMitarbeiter();
    }

//    @Test
//    public void setName_shouldThrowMitarbeiterException_whenNameIsNull() {
//        try {
//            mitarbeiter.setName(null);
//
//            // We should not reach this line here
//            fail();
//
//        } catch (MitarbeiterException e) {
//
//            // We should come here then our test is GREEN
//        }
//    }


//    @Test
//    public void berechneDienstalter_shouldReturnCorrectYears_whenGivenSpecificEntryYear() {
//
//        try {
//            // GIVEN
//            Year eintrittsJahr = Year.of(2020);
//            Year aktJahr = Year.of(2024);
//            mitarbeiter.setEintrJahr(eintrittsJahr);
//
//            // WHEN
//            int actualDienstalter = mitarbeiter.berechneDienstalter(aktJahr);
//
//            // THEN
//            // expected vs actual
//            int expectedDienstalter = aktJahr.getValue() - eintrittsJahr.getValue();
//            assertEquals(expectedDienstalter, actualDienstalter);
//
//        } catch (MitarbeiterException e) {
//            fail();
//        }
//    }


    @Test
    public void setName_shouldThrowMitarbeiterException_whenNameIsNull() {

        // NOTE:
        //   This test should throw Mitarbeiter exception,
        //   hence we use `assertThrows` to verify behaviour.

        // 1. Separate Class implementing Executable interface
        // assertThrows(MitarbeiterException.class, new SetNameExecutable());

        // 2. Anonymous Inner Class implementing Executable interface
        // assertThrows(MitarbeiterException.class, new Executable() {
        //     @Override
        //     public void execute() throws Throwable {
        //         mitarbeiter.setName(null);
        //     }
        // });

        // 3. Lambda / anonymous function implementing Executable interface
        assertThrows(MitarbeiterException.class, () -> mitarbeiter.setName(null));
    }


    @Test
    public void berechneDienstalter_shouldReturnCorrectYears_whenGivenSpecificEntryYear() throws MitarbeiterException {

        // NOTE:
        //   This test should not throw ANY exception,
        //   hence we add `throws MitarbeiterException` to the signature

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


/*

    EXAMPLES of: SUT_shouldDoAction_whenCondition

    calculateInterest_shouldReturnZero_whenBalanceIsNegative
    loginUser_shouldGrantAccess_whenCredentialsAreValid
    saveRecord_shouldThrowDatabaseException_whenConnectionIsLost
    updateProfile_shouldReflectChanges_whenNewInformationIsProvided
    processOrder_shouldDecreaseInventory_whenOrderIsPlaced
    sendEmail_shouldQueueEmailForDelivery_whenNetworkIsAvailable
    fetchUserDetails_shouldReturnEmptyOptional_whenUserDoesNotExist
    validatePassword_shouldFail_whenPasswordIsTooShort
    encodeString_shouldReturnEncodedString_whenSpecialCharactersArePresent
    parseDate_shouldThrowParseException_whenFormatIsIncorrect
    connectToServer_shouldTimeout_whenServerDoesNotRespond
    generateReport_shouldCreateNonEmptyFile_whenDataExists
    resizeImage_shouldMaintainAspectRatio_whenDimensionsAreSpecified
    addProductToCart_shouldIncreaseCartSize_whenProductIsInStock
    deleteUser_shouldReleaseResources_whenUserIsDeleted
    cacheResponse_shouldRetrieveFromCache_whenCacheIsValid
    startEngine_shouldConsumeFuel_whenEngineIsCold
    loadPage_shouldDisplayError_whenPageNotFound
    registerUser_shouldSendVerificationEmail_whenRegistrationIsSuccessful
    submitForm_shouldReturnSuccessMessage_whenAllFieldsAreValid

 */