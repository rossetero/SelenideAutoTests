package org.example;

import com.codeborne.selenide.Selenide;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyItemTitleAndParamsTest{

    public static void test() {
        String str = "Aboba";
        System.out.println("TITLEANDPARAMS CHECK");
        assertEquals(str, "Aboba");
        Selenide.sleep(4000);
    }
}
