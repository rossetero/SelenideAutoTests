package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyItemTitleAndParamsTest {
    private static final Logger logger = LoggerFactory.getLogger(VerifyItemTitleAndParamsTest.class);

    private static List<String> itemsInfoFromBasket = new ArrayList<>();


    private static void collectInfo() {
        ElementsCollection itemCards = $$x("//div[@class=\"cart-item-default group-items__item js-item \"]");
        for (SelenideElement itemCard : itemCards) {
            StringBuilder itemInfo = new StringBuilder();
            itemInfo.append(itemCard.find(byXpath(".//a[@class='cart-item-default__title']")).getText());
            SelenideElement color = itemCard.find(byXpath(".//li[contains(text(), \"Цвет:\")]"));
            String colorValue = "";
            if (color.is(visible)) {
                itemInfo.append(" ").append(color.getText()).append(";");
                colorValue = color.getText().substring(6);
            }
            SelenideElement size = itemCard.find(byXpath(".//li[contains(text(), \"Размер:\")]"));
            String sizeValue = "";
            if (size.is(visible)) {
                itemInfo.append(" ").append(size.getText());
                sizeValue = size.getText().substring(8);
            }
            String price = itemCard.find(byXpath(".//div[contains(@class,\"cart-item-default__price\")]")).getText();
            itemInfo.append(" ").append(price, 0, price.length() - 1);
            itemInfo.append(colorValue).append(" ").append(sizeValue);
            itemsInfoFromBasket.add(itemInfo.toString().replaceAll("[^a-zA-Zа-яА-Я0-9]", ""));
        }
    }

    public static void test() {
        collectInfo();
        Collections.sort(CartBuilder.getItemsInfoFromCards());
        Collections.sort(itemsInfoFromBasket);
        logger.info("Title and Params Test");
        logger.info("Items info from cards: {}", CartBuilder.getItemsInfoFromCards().toString());
        logger.info("Items info from basket: {}", itemsInfoFromBasket.toString());
        assertEquals(CartBuilder.getItemsInfoFromCards(), itemsInfoFromBasket);
    }
}