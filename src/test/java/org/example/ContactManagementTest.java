package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagementTest {

    private ContactManagement management;

    @BeforeAll
    public static void setUp(){
        System.out.println("The tests will be started");
    }

    @BeforeEach
    public void setUpAll() {
        management = new ContactManagement();
        //System.out.println("Create an Object to be used in all Test methods and implement DRY");
    }

    @Test
    @DisplayName("Create a new contact and check it's not empty and only 1 input by adding")
    //@EnabledOnOs(value = OS.MAC, disabledReason = "This app is only for mac")
    //@RepeatedTest(value = 3, name = "Repeating Contact Creation {currentRepetition} of {totalRepetitions}")
    public void createNewContact(){
        management.addContact("Sarah", "Schneider", "012345678910");
        assertFalse(management.getAllContact().isEmpty());
        assertEquals(1, management.getAllContact().size());
    }

    @Test
    @DisplayName("The test of create a new contact will run on DEV environment")
    public void shouldRunOnDevEnv(){
        System.out.println("ENV property: " + System.getProperty("ENV"));
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        management.addContact("Sarah", "Schneider", "012345678910");
        assertFalse(management.getAllContact().isEmpty());
        assertEquals(1, management.getAllContact().size());
    }

    @Test
    @DisplayName("Firstname mustn't be null")
    public void firstNameAcceptNoNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            management.addContact(null, "Schneider", "012345678910");
        });
    }

    @Test
    @DisplayName("Firstname mustn't be empty")
    public void firstNameShouldNotBeEmpty(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            management.addContact("", "Schneider", "012345678910");
        });
    }

    @Test
    @DisplayName("Lastname mustn't be null")
    public void lastNameAcceptNoNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            management.addContact("Sarah", null, "012345678910");
        });
    }

    @Test
    @DisplayName("Lastname mustn't be empty")
    public void lastNameShouldNotBeEmpty(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            management.addContact("Sarah", "", "012345678910");
        });
    }

    @Test
    @DisplayName("Phone number, should be more than 10 digits")
    public void phoneNumberContainMoreThan10Digits(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            management.addContact("Sarah", "Schneider", "0123");
        });
    }

    @Test
    @DisplayName("Phone number, accept only digits")
    public void phoneNumberAcceptOnlyDigits(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            management.addContact("Sarah", "Schneider", "ABC");
        });
    }

    @Nested
    class ParameterizedTests{
        @ParameterizedTest
        @DisplayName("Phone number should start with 0")
        @CsvFileSource(resources = "/data.csv")
        public void phoneNumberShouldStartWithZeroCSV(String phoneNumber) {
            Assertions.assertThrows(RuntimeException.class, () -> {
                management.addContact("Sarah", "Schneider", phoneNumber);
            });
        }

        @ParameterizedTest
        @DisplayName("Phone number should start with 0")
        @ValueSource(strings = {"+11112345678999", "1112111098765432"}) // We can change the inputs as we want
        public void phoneNumberShouldStartWithZeroValueSource(String phoneNumber) {
            Assertions.assertThrows(RuntimeException.class, () -> {
                management.addContact("Sarah", "Schneider", phoneNumber);
            });
        }

        @ParameterizedTest
        @DisplayName("Phone number should start with 0")
        @MethodSource("MethodSourcePhoneNumberList")
        public void phoneNumberShouldStartWithZeroMethodSource(String phoneNumber) {
            Assertions.assertThrows(RuntimeException.class, () -> {
                management.addContact("Sarah", "Schneider", phoneNumber);
            });
        }
        private static List<String> MethodSourcePhoneNumberList() {
            return Arrays.asList("a143322355551456789", "+12345555567890", "@123477765456789");
        }
    }

    @AfterEach
    public void afterEveryMethod() {
        //System.out.println("Test completed");
    }

    @AfterAll
    public static void completed() {
        System.out.println("Done !");
    }

}