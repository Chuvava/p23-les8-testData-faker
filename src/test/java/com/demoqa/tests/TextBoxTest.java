package com.demoqa.tests;

import com.demoqa.pages.TextBoxPage;
import org.junit.jupiter.api.Test;

public class TextBoxTest extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    String fullName = faker.name().fullName(),
            email = faker.internet().emailAddress(),
            currentAddress = faker.address().streetAddress(),
            permanentAddress = faker.address().streetAddress();

    @Test
    public void successfulSubmittingForm() {

        //Open page, fill all fields and submit
        textBoxPage.openPage()
                .killBanners()
                .setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit();

        //Verify values
        textBoxPage.verifyOutputValue("Name", fullName)
                .verifyOutputValue("Email", email)
                .verifyOutputValue("Current Address", currentAddress)
                .verifyOutputValue("Permananet Address", permanentAddress);
    }
}
