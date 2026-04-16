package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class PurchaseHistoryPage extends BasePage{

    private By newestOrderCode = By.xpath("//tbody/tr[1]/td[1]/a");

    public String getNewestOrderCode() {
        return WebUI.getElementText(newestOrderCode);
    }
}
