package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CoreIssueLocation {
    protected static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chrome/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test()
    public void loginSuccessfully() throws Exception {
        System.out.println("Core [Test] -> loginSuccessfully");
        driver.get("http://localhost:4200/");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("kasun");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("app-login-button")).click();
        driver.manage().window().maximize();
    }

    @Test(dependsOnMethods={"loginSuccessfully"})
    public void gotoIssueLocation() throws Exception {
        System.out.println("Core [Test] -> gotoIssueLocation");
        pause(5);
        driver.findElement(By.id("500")).click();
        driver.findElement(By.id("500_150")).click();
        driver.findElement(By.id("500_150_250")).click();
    }

    @Test(dependsOnMethods={"gotoIssueLocation"})
    public void addIssueLocation() throws Exception {
        System.out.println("Core [Test] -> addIssueLocation");
        driver.findElement(By.name("grid-add-button")).click();
        driver.findElement(By.id("m14Name")).click();
        driver.findElement(By.id("m14Name")).clear();
        driver.findElement(By.id("m14Name")).sendKeys("Gampaha");
        driver.findElement(By.id("m14NameLang")).click();
        driver.findElement(By.id("m14NameLang")).clear();
        driver.findElement(By.id("m14NameLang")).sendKeys("Gampaha");
        driver.findElement(By.id("dropdown-m14CountryIdM05-390")).click();
        driver.findElement(By.id("m14CountryIdM05-261")).click();
        driver.findElement(By.name("dyn-tab-btm-btn-save")).click();
        driver.findElement(By.name("msg-box-btn-ok")).click();
        pause(2);
        driver.findElement(By.name("dyn-tab-btm-btn-close")).click();
    }

    @Test(dependsOnMethods={"addIssueLocation"})
    public void filterRecord() throws Exception {
        System.out.println("Core [Test] -> filterRecord");
        driver.findElement(By.id("issue_locations-grid-search-button")).click();
        driver.findElement(By.id("filter-m14Name")).click();
        driver.findElement(By.id("filter-m14Name")).clear();
        driver.findElement(By.id("filter-m14Name")).sendKeys("Gampaha");
        driver.findElement(By.id("issue_locations-grid-refresh-button")).click();
        pause(2);
    }

    @Test(dependsOnMethods={"filterRecord"})
    public void approveIssueLocation() throws Exception {
        System.out.println("Core [Test] -> approveIssueLocation");
        driver.findElement(By.id("Gampaha")).click();
        driver.findElement(By.id("filter-m14Name")).click();
        driver.findElement(By.id("filter-m14Name")).clear();
        driver.findElement(By.id("filter-m14Name")).sendKeys("Gampaha");
        driver.findElement(By.id("issue_locations-grid-refresh-button")).click();
        pause(2);
        approveRecord("Gampaha");
    }

    @Test(dependsOnMethods={"approveIssueLocation"})
    public void editIssueLocation() throws Exception {
        System.out.println("Core [Test] -> editIssueLocation");
        WebElement element = driver.findElement(By.id("Gampaha"));
        Actions actions = new Actions(driver).contextClick(element);
        actions.build().perform();
        driver.findElement(By.id("Edit")).click();
        driver.findElement(By.id("m14NameLang")).click();
        driver.findElement(By.id("m14NameLang")).clear();
        driver.findElement(By.id("m14NameLang")).sendKeys("Gampaha Lang");
        driver.findElement(By.name("dyn-tab-btm-btn-save")).click();
        driver.findElement(By.name("msg-box-btn-ok")).click();
        pause(2);
        driver.findElement(By.name("dyn-tab-btm-btn-close")).click();
        approveRecord("Gampaha");
    }

    public void approveRecord(String elementId) throws InterruptedException {
        System.out.println("Core [Common] -> approveRecord");
        WebElement element = driver.findElement(By.id(elementId));
        Actions actions = new Actions(driver).contextClick(element);
        actions.build().perform();
        driver.findElement(By.id("Approve")).click();
        driver.findElement(By.name("msg-box-btn-ok")).click();
        pause(2);
    }

    public void pause(long timeout) throws InterruptedException {
        System.out.println("Core [Common] -> pause");
        TimeUnit.SECONDS.sleep(timeout);
    }
}
