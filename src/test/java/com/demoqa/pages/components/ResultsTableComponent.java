package com.demoqa.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private SelenideElement table = $(".table-responsive") ;

    public void verifyRowContainValue(String rowName, String value) {
        table.$(byText(rowName)).parent()
                .shouldHave(Condition.text(value));
    }

    public void verifyTableIsAppeared() {
        table.shouldBe(Condition.appear);
    }

    public void verifyTableIsNotAppeared() {
        table.shouldNotBe(Condition.appear);
    }
}
