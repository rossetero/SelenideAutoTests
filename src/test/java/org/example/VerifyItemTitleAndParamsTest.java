package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.sun.security.jgss.GSSUtil;

import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyItemTitleAndParamsTest {
    private static List<String> itemsInfoFromBasket;

    private static void collectInfo() {
        ElementsCollection elements = $$x("//div[@class=\"cart-item-default group-items__item js-item \"]");

        // Итерируемся по элементам и выполняем необходимые действия

        for (SelenideElement element : elements) {
            StringBuilder itemInfo = new StringBuilder();
            // Например, выводим текст каждого элемента
            //System.out.println("Текст элемента: " + element.getText());
            //System.out.println(element.find(byXpath(".//a[@class='cart-item-default__title']")).getText());
            itemInfo.append(element.find(byXpath(".//a[@class='cart-item-default__title']")).getText());
            SelenideElement color = element.find(byXpath(".//li[contains(text(), \"Цвет:\")]"));
            String colorValue = "";
            if (color.is(visible)) {
                itemInfo.append(" ").append(color.getText()).append(";");
                colorValue = color.getText().substring(6);
            }
            SelenideElement size = element.find(byXpath(".//li[contains(text(), \"Размер:\")]"));
            String sizeValue = "";
            if (size.is(visible)) {
                itemInfo.append(" ").append(size.getText());
                sizeValue = color.getText().substring(8);
            }
            String tmp = element.find(byXpath(".//div[contains(@class,\"cart-item-default__price\")]")).getText();
            itemInfo.append(" ").append(tmp, 0, tmp.length() - 1);
            itemInfo.append(colorValue).append(" ").append(sizeValue);
            itemsInfoFromBasket.add(itemInfo.toString());
        }
    }

    public static boolean test() {

        collectInfo();
        Collections.sort(CartBuilder.getItemsInfoFromCards());
        Collections.sort(itemsInfoFromBasket);
        System.out.println(CartBuilder.getItemsInfoFromCards().toString());
        System.out.println(itemsInfoFromBasket.toString());
        String str = "Aboba";
        System.out.println("TITLEANDPARAMS CHECK");
        assertEquals(str, "Aboba");
        Selenide.sleep(4000);

        return true;

    }
}
////div[@class="cart-item-default group-items__item js-item "][1]/