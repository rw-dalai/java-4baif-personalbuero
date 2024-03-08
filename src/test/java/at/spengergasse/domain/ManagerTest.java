package at.spengergasse.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ManagerTest {

    // Fixture
    private Manager manager;

    @BeforeEach
    public void setup() throws MitarbeiterException {
        manager = new Manager();
    }


    @Test
    public void berechneGehalt_shouldReturnFixum() {

        // WHEN
        double actualGehalt = manager.berechneGehalt();

        // THEN
        // expected vs actual
        double expectedGehalt = manager.getFixum();
        assertEquals(expectedGehalt, actualGehalt);
    }

    @Test
    public void compareTo_shouldReturnZero_whenSalariesAreEqual() throws MitarbeiterException {

        // GIVEN
        Mitarbeiter manager2 = new Manager();

        // WHEN
        int expected = manager.compareTo(manager2);

        // THEN
        assertEquals(expected, 0);
    }

    @Test
    public void compareTo_shouldReturnNegative_whenFirstSalaryIsLower() throws MitarbeiterException {

        // GIVEN
        Manager manager2 = new Manager();
        manager2.setFixum(manager.getFixum() - 1);

        // WHEN
        int expected = manager2.compareTo(manager);

        // THEN
        assertTrue(expected < 0);
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
