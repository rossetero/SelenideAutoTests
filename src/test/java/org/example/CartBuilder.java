package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

// TODO удалить комментарии и сделать репоизиторий публичным перед отсправкой
public class CartBuilder {
    private static List<String> itemsInfoFromCards;

    private final String addToCartButtonXPath = "//button[@data-selector=\"add-to-cart-btn\"]";
    private final String incrementItemCounterXPath = "//button[@data-selector=\"quantity-group-plus\"]";
    private final String goToCartButtonXPath = "//a[@data-selector=\"go-to-cart\"]";
    private final String togglerXPath = "//a[@data-selector=\"header-rubrics-toggler-desktop\"]";
    private Set<String> categories = Set.of("kostyumy", "bele");
//"kostyumy","bryuki","tolstovki",

    CartBuilder() {
        itemsInfoFromCards = new ArrayList<>();
    }


    // TODO добавить проверку и логгирование таймаута

    public static List<String> getItemsInfoFromCards() {
        return itemsInfoFromCards;
    }

    public void fillCart() {
        Random r = new Random();
        for (String c : categories) {
            int amount = r.nextInt(4) + 1;
            addProductToCart(amount, c);
        }
        $x(goToCartButtonXPath).click();
        Selenide.sleep(2000);

    }

    private void collectInfo() {
        StringBuilder itemInfo = new StringBuilder($x("//h1[@id=\"name\"]").getOwnText());
        itemInfo.append(" ").append($x("//div[@class=\"price__regular\"]").getOwnText());
        SelenideElement color = $x("//span[contains(text(), \"Цвет:\")]/following-sibling::*[1]");
        if (color.is(visible)) {
            itemInfo.append(color.getOwnText());
        }
        SelenideElement size = $x("//span[contains(text(), \"Размер:\")]/following-sibling::*[1]");
        if (size.is(visible)) {
            itemInfo.append(" ").append(size.getOwnText());
        }
        itemsInfoFromCards.add(itemInfo.toString());
    }

    private void addProductToCart(int amount, String category) {
        open("/");
        //Selenide.sleep(1500);
        $x(togglerXPath).click();
        Selenide.sleep(1500);
        // TODO спросить как поступить с каталогом
        SelenideElement l = $x(getCategoryXPathFromCatalogTogglerFor(category)); //костюмы кнопка
        if (l.is(visible)) {
            l.click();
        } else {
            //
            System.out.println("WHAT?!");
            open("/catalog");
            $x(getCategoryXPathFromCatalogPageFor(category)).click();//костюмы станица
        }
        //Selenide.sleep(2000);
        $x(getRandomisedItemCardXPath()).click();
        Selenide.sleep(2000);
        collectInfo();
        $x(addToCartButtonXPath).click();
        //Selenide.sleep(2000);
        //$x(incrementItemCounterXPath).click();
        //Selenide.sleep(2000);
        for (int i = 0; i < amount - 1; i++) {
            $x(incrementItemCounterXPath).click();
            Selenide.sleep(1500);
        }
    }

    private String getRandomisedItemCardXPath() {
        //Random r = new Random();
        //int n = r.nextInt(3) + 1;
        //TODO
        int n = 4;
        return String.format("//div[@class=\"product-listing-card\"][%d]/div[@class=\"product-listing-card__preview\"]/a[@class=\"product-listing-card__preview-link\"]", n);
    }

    private String getCategoryXPathFromCatalogTogglerFor(String category) {
        return String.format("//a[@class=\"mega-burger-content-menu__title\"][contains(@href, \"%s\")]", category);
    }

    private String getCategoryXPathFromCatalogPageFor(String category) {
        return String.format("//a[@class=\"link\"][contains(@href, \"muzh\")]/ancestor::li[@class=\"category\"]//a[@class=\"link\"][contains(@href, \"%s\")]", category);
    }

}
