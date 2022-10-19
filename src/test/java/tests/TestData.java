package tests;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();

    static String authCookieName = "NOPCOMMERCE.AUTH";
    static String firstName = faker.name().firstName();
    static String lastName = faker.name().lastName();
    static String gender = "F";
    static String email = faker.internet().emailAddress();
    static String password = faker.internet().password();
    static String newFirstName = faker.name().firstName();
    static String newLastName = faker.name().lastName();
    static String newGender = "M";
    static String requestVerificationToken = "__RequestVerificationToken";
    static String requestVerificationTokenValue = "kAg988MFIr0kGKo5mOEF-MZxAnL0mjaEyMkAyTTZHTZNg4Hmg3_" +
            "FLmtILgD7Q3AHtRZi6i1VfaBGfP2dYsht8yPHH4pBABZUKkAKnH2rn2c1";
    static String requestVerificationTokenData = "MuLsCQszK15cUiJ_JtGufdDtmiuncc8kjMf1rqJwYZrejgzArfrlFBUCx" +
            "R56lTZn62WaIDHSNfUuzSNCrM6npYcE8UUuZ0cMB1AJcFNgHeM1";
}
