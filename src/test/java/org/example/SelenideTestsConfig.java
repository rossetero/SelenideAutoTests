package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SelenideTestsConfig {
    private static final Logger logger = LoggerFactory.getLogger(SelenideTestsConfig.class);

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.baseUrl = "https://navisale.ru";
        new CartBuilder().fillCart();
        logger.info("Preparation for tests completed");
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
