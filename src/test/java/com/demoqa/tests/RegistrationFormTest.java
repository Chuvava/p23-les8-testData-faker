package com.demoqa.tests;

import com.demoqa.pages.RegistrationPage;
import com.demoqa.utils.ConvertUtils;
import com.demoqa.utils.RandomUtils;
import org.junit.jupiter.api.Test;

public class RegistrationFormTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = RandomUtils.getRandomGender(),
            mobileNumber = faker.phoneNumber().subscriberNumber(10),
            pictureName = "branch.jpg",
            currentAddress = faker.address().streetAddress(),
            state = "Haryana",
            city = "Panipat",
            dayOfBirth = "20",
            monthOfBirth = "September",
            yearOfBirth = "1994";
    String[] subjects = new String[]{"Hindi", "Computer Science", "Economics"},
            hobbies = new String[]{"Reading", "Music"};

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
                .verifyInfoInFinalTable("Subjects", ConvertUtils.arrayToStringWithoutBrackets(subjects))
                .verifyInfoInFinalTable("Hobbies", ConvertUtils.arrayToStringWithoutBrackets(hobbies))
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
