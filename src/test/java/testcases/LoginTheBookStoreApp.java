package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelDataDriven;

import java.io.IOException;
import java.time.Duration;

public class LoginTheBookStoreApp {

    String filePath = System.getProperty("user.dir") + "//src//test//java//resources//excelData.xlsx";

    @DataProvider(name = "mytestdata")
    public Object[][] getMydata() throws IOException {
        ExcelDataDriven reader = new ExcelDataDriven();
        return reader.getExcelData(filePath);
    }

    @Test(dataProvider = "mytestdata")
    public void practiceFormWithExcelData(String userName, String firstName, String email, String password, String confirmPassword) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://139.59.27.246:3000/login");
        WebElement homeText = driver.findElement(By.xpath("//h1[text()='Sign In']"));
        Assert.assertTrue(homeText.isDisplayed());

        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys(userName);
        driver.findElement(By.id("firstName")).click();
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("(//input[@id='password'])[2]")).click();
        driver.findElement(By.xpath("(//input[@id='password'])[2]")).sendKeys(confirmPassword);
        driver.findElement(By.xpath("//button[text()='Register']")).submit();

        WebElement errorMessage = driver.findElement(By.cssSelector(".fade.alert.alert-danger.show"));
        Assert.assertTrue(errorMessage.isDisplayed());
        driver.quit();
    }
}
