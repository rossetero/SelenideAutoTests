package org.example;

import org.junit.jupiter.api.Test;

public class SelenideTests extends SelenideTestsConfig {

    @Test
    public void verifyItemTitleAndParamsTest(){
        VerifyItemTitleAndParamsTest.test();
    }

    @Test
    public void verifyTotalAmountTest(){
        VerifyTotalAmountTest.test();
    }
}
