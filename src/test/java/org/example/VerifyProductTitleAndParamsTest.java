package org.example;

import org.junit.jupiter.api.Test;

public class VerifyProductTitleAndParamsTest extends SelenideTestsConfig {
    private final CartBuilder cartBuilder;

    VerifyProductTitleAndParamsTest() {
        cartBuilder = new CartBuilder();
    }

    @Test
    public void f() {
        cartBuilder.addProductsToCart();
    }
}
