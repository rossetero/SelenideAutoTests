package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyTotalAmountTest {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTotalAmountTest.class);

    private static int getAmountFromSidebar() {
        String sidebarInfo = $x("//button[@class=\"basket-summary__btn-more\"]/span").shouldBe(visible).getOwnText();
        return Integer.parseInt(sidebarInfo.substring(0, sidebarInfo.indexOf(" ")));
    }

    private static int getTotalAmountFromCart() {
        ElementsCollection itemCards = $$x("//div[@class=\"cart-item-default group-items__item js-item \"]");
        int totalAmount = 0;
        for (SelenideElement itemCard : itemCards) {
            SelenideElement checkBox = itemCard.find(byXpath(".//input[@type=\"checkbox\"]"));
            if (checkBox.isSelected()) {
                SelenideElement quantityElement = itemCard.find(byXpath(".//div[@data-selector=\"quantity-group-number\"]"));
                totalAmount += Integer.parseInt(quantityElement.getText());
            }
        }
        return totalAmount;
    }

    public static void test() {
        logger.info("Amount test");
        int amountFromSidebar = getAmountFromSidebar();
        int totalAmountFromCart = getTotalAmountFromCart();
        logger.info("Cart={}, Sidebar={}", totalAmountFromCart, amountFromSidebar);
        assertEquals(totalAmountFromCart, amountFromSidebar);
    }
}
