package org.example;

import com.codeborne.selenide.Selenide;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyTotalAmountTest {
    public static void test() {
        int a = 2+2;
        System.out.println("AMOUNT CHECK");
        assertEquals(4,a);
        Selenide.sleep(4000);
    }
}
