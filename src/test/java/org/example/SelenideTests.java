package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelenideTests extends SelenideTestsConfig {

    @Test
    public void verifyItemTitleAndParamsTest() {
        assertEquals(CartBuilder.getItemsInfoFromCards(), VerifyItemTitleAndParamsTest.test());
    }

    @Test
    public void verifyTotalAmountTest() {
        VerifyTotalAmountTest.test();
    }
}
