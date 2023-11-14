package com.demoqa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsTableComponent resultsTableComponent = new ResultsTableComponent();
    public SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genderArea = $("#genterWrapper"),
            mobileNumber = $("#userNumber"),
            calendar = $("#dateOfBirthInput"),
            subject = $("#subjectsContainer input"),
            hobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateCityWrapper = $("#stateCity-wrapper"),
            selectState = stateCityWrapper.$(byText("Select State")),
            selectCity = stateCityWrapper.$(byText("Select City")),
            submit = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage killBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    public RegistrationPage setGenderArea(String value) {
        genderArea.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setMobileNumber(String value) {
        mobileNumber.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendar.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String... values) {
        for(int i = 0; i < values.length; i++) {
            subject.setValue(values[i]).pressEnter();
        }

        return this;
    }

    public RegistrationPage setHobbies(String... values) {
        for(int i = 0; i < values.length; i++) {
            hobbies.$(byText(values[i])).click();
        }

        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        selectState.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        selectCity.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage submit() {
        submit.click();
        return this;
    }

    public RegistrationPage verifyInfoInFinalTable(String rowName, String value) {
        resultsTableComponent.verifyRowContainValue(rowName, value);
        return this;
    }

    public RegistrationPage verifyTableIsAppeared() {
        resultsTableComponent.verifyTableIsAppeared();
        return this;
    }

    public RegistrationPage verifyTableIsNotAppeared() {
        resultsTableComponent.verifyTableIsNotAppeared();
        return this;
    }

    public RegistrationPage verifyMobileNumberFieldIsRed() {
        mobileNumber.shouldHave(Condition.cssValue("border-color", "rgb(220, 53, 69)"));
        return this;
    }

    public RegistrationPage verifyGenderOptionsAreRed() {
        genderArea.$("label")
                .shouldHave(Condition.cssValue("color", "rgba(220, 53, 69, 1)"));
        return this;
    }
}
