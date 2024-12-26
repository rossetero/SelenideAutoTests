package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CartBuilder {
    private static final Logger logger = LoggerFactory.getLogger(CartBuilder.class);
    private static List<String> itemsInfoFromCards;

    private final String addToCartButtonXPath = "//button[@data-selector=\"add-to-cart-btn\"]";
    private final String incrementItemCounterXPath = "//button[@data-selector=\"quantity-group-plus\"]";
    private final String goToCartButtonXPath = "//a[@data-selector=\"go-to-cart\"]";
    private final String togglerXPath = "//a[@data-selector=\"header-rubrics-toggler-desktop\"]";
    private final Set<String> categories = Set.of("kostyumy", "bele", "bryuki", "tolstovki");

    CartBuilder() {
        itemsInfoFromCards = new ArrayList<>();
    }

    public static List<String> getItemsInfoFromCards() {
        return itemsInfoFromCards;
    }

    public void fillCart() {
        Random r = new Random();
        for (String c : categories) {
            int amount = r.nextInt(5) + 1;
            addProductToCart(amount, c);
        }
        $x(goToCartButtonXPath).shouldBe(clickable).click();
    }

    private void collectInfo() {
        StringBuilder itemInfo = new StringBuilder($x("//h1[@id=\"name\"]").getOwnText());
        itemInfo.append(" ").append($x("//div[@class=\"price__regular\"]").getOwnText());
        SelenideElement color = $x("//span[contains(text(), \"Цвет:\")]/following-sibling::*[1]");
        if (color.is(visible)) {
            itemInfo.append(color.getOwnText());
        } else if (itemInfo.toString().contains("Цвет:")) {
            String colorValue = itemInfo.substring(
                    itemInfo.toString().indexOf(":") + 2,
                    itemInfo.toString().indexOf(";"));
            itemInfo.append(colorValue);
        }
        SelenideElement size = $x("//span[contains(text(), \"Размер:\")]/following-sibling::*[1]");
        if (size.is(visible)) {
            itemInfo.append(" ").append(size.getOwnText());
        }
        itemsInfoFromCards.add(itemInfo.toString().replaceAll("[^a-zA-Zа-яА-Я0-9]", ""));
    }

    private void addProductToCart(int amount, String category) {
        open("/");
        $x(togglerXPath).click();
        SelenideElement linkFromToggler = $x(getLinkXPathFromCatalogTogglerFor(category));
        try {
            linkFromToggler.shouldBe(visible);
            linkFromToggler.click();
            logger.info("Menu toggler used");
        } catch (ElementShould e) {
            logger.warn("Menu Toggler didn't work. Going to catalog page to continue preparations");
            open("/catalog");
            $x(getLinkXPathFromCatalogPageFor(category)).click();
        }
        $x(getRandomisedItemCardXPath()).click();
        collectInfo();
        $x(addToCartButtonXPath).click();
        for (int i = 0; i < amount - 1; i++) {
            $x("//legend/span[contains(text(),\"Размер\")]").scrollTo();
            $x(incrementItemCounterXPath).click();
            Selenide.sleep(1200);
        }
    }

    private String getRandomisedItemCardXPath() {
        Random r = new Random();
        int n = r.nextInt(10) + 1;
        logger.info("Card number {} clicked", n);
        return String.format("//div[@class=\"product-listing-card\"][%d]/div[@class=\"product-listing-card__preview\"]/a[@class=\"product-listing-card__preview-link\"]", n);
    }

    private String getLinkXPathFromCatalogTogglerFor(String category) {
        return String.format("//a[@class=\"mega-burger-content-menu__title\"][contains(@href, \"%s\")]", category);
    }

    private String getLinkXPathFromCatalogPageFor(String category) {
        return String.format("//a[@class=\"link\"][contains(@href, \"muzh\")]/ancestor::li[@class=\"category\"]//a[@class=\"link\"][contains(@href, \"%s\")]", category);
    }

}
