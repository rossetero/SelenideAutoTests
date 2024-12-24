package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class CartBuilder {
    private final String addToCartButtonXPath = "//button[@data-selector=\"add-to-cart-btn\"]";

    //private final String addToCartButtonXPath = "/html/body/main/div[2]/div[1]/div[5]/form/div[2]/button";
    private final String incrementItemCounterXPath = "//button[@data-selector=\"quantity-group-plus\"]";
    //private final String incrementItemCounterXPath = "/html/body/main/div[2]/div[1]/div[5]/form/div[2]/div/div/div/button[2]";
    private final String goToCartButtonXPath = "//a[@data-selector=\"go-to-cart\"]";

    //private final String goToCartButtonXPath = "/html/body/main/div[2]/div[1]/div[5]/form/div[2]/div/a";

    private final String togglerXPath = "//a[@data-selector=\"header-rubrics-toggler-desktop\"]";
    private final String suitsCategoryXPathFromCatalogToggler = "//a[@class=\"mega-burger-content-menu__title\"][contains(@href, \"kost\")]";
    private final String suitsCategoryXPathFromCatalogPage = "//a[@class=\"link\"][contains(@href, \"muzh\")]/ancestor::li[@class=\"category\"]//a[@class=\"link\"][contains(@href, \"kostyumy\")]";
    private final String suitCardXPath = "//div[@class=\"product-listing-card\"][2]/div[@class=\"product-listing-card__preview\"]/a[@class=\"product-listing-card__preview-link\"]";
    //private final String suitsCategoryXPathFromCatalogToggler = "/html/body/div[3]/div/div/div[1]/div[1]/div/div[1]/div[2]/a";
    //private final String suitsCategoryXPathFromCatalogPage = "//a[@class=\"link\"][contains(@href, \"muzh\")]/ancestor::li[@class=\"category\"]//a[@class=\"link\"][contains(@href, \"kostyumy\")]"
    //private final String suitsCategoryXPathFromCatalogPage = "/html/body/div[3]/div/section/div/ul/li[1]/div/ul/li[6]/a";
    //private final String suitCardXPath = "/html/body/main/div[2]/div[3]/div[3]/div/div[1]/div[1]/a";
    //private final String togglerXPath = "/html/body/header/div/div[4]/div/a";

    //добавить проверку и логгирование таймаута

    //private final ArrayList<String> suitCategoryXPaths =

    public void addProductsToCart() {
        open("/");
        Selenide.sleep(1500);
        $x(togglerXPath).click();
        Selenide.sleep(1000);
        SelenideElement l = $x(suitsCategoryXPathFromCatalogToggler); //костюмы кнопка
        if (l.is(visible)) {
            l.click();
        } else {
            System.out.println("WHAT?!");
            open("/catalog");
            $x(suitsCategoryXPathFromCatalogPage).click();//костюмы станица
        }
        Selenide.sleep(2000);
        $x(suitCardXPath).click();
        Selenide.sleep(2000);
        $x(addToCartButtonXPath).click();
        Selenide.sleep(2000);
        $x(incrementItemCounterXPath).click();
        Selenide.sleep(2000);
        $x(incrementItemCounterXPath).click();
        Selenide.sleep(2000);
        $x(goToCartButtonXPath).click();
        Selenide.sleep(5000);

    }

}
