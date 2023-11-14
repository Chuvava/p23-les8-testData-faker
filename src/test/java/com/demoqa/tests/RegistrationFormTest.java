package com.demoqa.tests;

import com.demoqa.pages.RegistrationPage;
import com.demoqa.utils.RandomUtils;
import org.junit.jupiter.api.Test;

public class RegistrationFormTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = RandomUtils.getRandomGender(),
            mobileNumber = faker.phoneNumber().subscriberNumber(10),
            subjects = faker.options().option("Accounting", "Arts", "Biology", "Chemistry", "Maths",
                    "Commerce", "Economics", "Physics", "English", "Hindi", "History", "Social Studies"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            pictureName = "branch.jpg",
            currentAddress = faker.address().streetAddress(),
            state = RandomUtils.getRandomState(),
            city = RandomUtils.getRandomCityAccordingToState(state),
            dayOfBirth = String.valueOf(faker.number().numberBetween(1, 28)),
            monthOfBirth = faker.options().option("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"),
            yearOfBirth = String.valueOf(faker.number().numberBetween(1980, 2005));

    @Test
    void successfulRegistrationTestWithFullData() {

        //Open page, fill in form and submit
        registrationPage.openPage()
                .killBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGenderArea(gender)
                .setMobileNumber(mobileNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subjects)
                .setHobbies(hobbies)
                .uploadPicture(pictureName)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit();

        //Verify values
        registrationPage.verifyTableIsAppeared()
                .verifyInfoInFinalTable("Student Name", String.format("%s %s", firstName, lastName))
                .verifyInfoInFinalTable("Student Email", email)
                .verifyInfoInFinalTable("Gender", gender)
                .verifyInfoInFinalTable("Mobile", mobileNumber)
                .verifyInfoInFinalTable("Date of Birth",
                        String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth))
                .verifyInfoInFinalTable("Subjects", subjects)
                .verifyInfoInFinalTable("Hobbies", hobbies)
                .verifyInfoInFinalTable("Picture", pictureName)
                .verifyInfoInFinalTable("Address", currentAddress)
                .verifyInfoInFinalTable("State and City", String.format("%s %s", state, city));
    }

    @Test
    void successfulRegistrationTestWithMinimalData() {

        //Open page, fill in form and submit
        registrationPage.openPage()
                .killBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGenderArea(gender)
                .setMobileNumber(mobileNumber)
                .submit();

        //Verify values
        registrationPage.verifyTableIsAppeared()
                .verifyInfoInFinalTable("Student Name", String.format("%s %s", firstName, lastName))
                .verifyInfoInFinalTable("Gender", gender)
                .verifyInfoInFinalTable("Mobile", mobileNumber);
    }

    @Test
    void tryingToRegisterWithoutSelectingGender() {

        //Open page, fill in form and submit
        registrationPage.openPage()
                .killBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMobileNumber(mobileNumber)
                .submit();

        //Verify table with data is not appeared and gender area borders became red
        registrationPage.verifyTableIsNotAppeared()
                .verifyGenderOptionsAreRed();
    }

    @Test
    void tryingToRegisterWithoutDefiningNumber() {

        //Open page, fill in form and submit
        registrationPage.openPage()
                .killBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGenderArea(gender)
                .submit();

        //Verify table with data is not appeared and mobile number field borders became red
        registrationPage.verifyTableIsNotAppeared()
                .verifyMobileNumberFieldIsRed();
    }
}
