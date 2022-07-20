package saudi;

import core.CoreIssueLocation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SaudiIssueLocation extends CoreIssueLocation {
    @Test(dependsOnMethods={"approveIssueLocation"})
    public void editIssueLocation() throws Exception {
        System.out.println("Saudi [Test] -> editIssueLocation");
        WebElement element = driver.findElement(By.id("Gampaha"));
        Actions actions = new Actions(driver).contextClick(element);
        actions.build().perform();
        driver.findElement(By.id("Edit")).click();
        driver.findElement(By.id("m14NameLang")).click();
        driver.findElement(By.id("m14NameLang")).clear();
        driver.findElement(By.id("m14NameLang")).sendKeys("جامباها");
        driver.findElement(By.name("dyn-tab-btm-btn-save")).click();
        driver.findElement(By.name("msg-box-btn-ok")).click();
        pause(2);
        driver.findElement(By.name("dyn-tab-btm-btn-close")).click();
        approveRecord("Gampaha");
    }
}
