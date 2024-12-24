package org.example;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class SelenideTestsConfig {

    @BeforeEach
    public void setUp(){
        Configuration.browser="chrome";
        Configuration.browserSize="1920x1080";
        Configuration.headless=false;
        Configuration.baseUrl = "https://navisale.ru";
    }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
